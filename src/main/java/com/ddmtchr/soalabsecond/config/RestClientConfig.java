package com.ddmtchr.soalabsecond.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestClient;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class RestClientConfig {

    @Value("${restclient.base-url}")
    private String baseUrl;

    @Value("${restclient.truststore.path}")
    private String truststorePath;

    @Value("${restclient.truststore.password}")
    private String truststorePassword;

    @Bean
    public RestClient restClient(RestClientBuilderConfigurer builderConfigurer, MappingJackson2XmlHttpMessageConverter xmlMessageConverter) throws Exception {
        KeyStore trustStore = KeyStore.getInstance("PKCS12");
        try (InputStream is = getClass().getResourceAsStream(truststorePath.replace("classpath:", "/"))) {
            trustStore.load(is, truststorePassword.toCharArray());
        }

        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(trustStore, (TrustStrategy) null)
                .build();

        var tlsStrategy = new DefaultClientTlsStrategy(sslContext);

        var connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setTlsSocketStrategy(tlsStrategy)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(TimeValue.ofMinutes(1))
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(Timeout.ofSeconds(10))
                        .setResponseTimeout(Timeout.ofSeconds(30))
                        .build())
                .build();

        var requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectTimeout(Duration.ofSeconds(10));
        requestFactory.setReadTimeout(Duration.ofSeconds(30));

        return builderConfigurer.configure(RestClient.builder())
                .requestFactory(requestFactory)
                .baseUrl(baseUrl)
                .messageConverters(converters -> {
                    converters.clear();

                    xmlMessageConverter.setSupportedMediaTypes(List.of(
                            MediaType.APPLICATION_XML,
                            MediaType.TEXT_XML,
                            MediaType.APPLICATION_XHTML_XML
                    ));
                    converters.add(xmlMessageConverter);
                })
                .build();
    }

    @Bean
    public MappingJackson2XmlHttpMessageConverter xmlMessageConverter() {
        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        xmlMapper.configOverride(LocalDate.class)
                .setFormat(JsonFormat.Value.forPattern("yyyy-MM-dd"));

        return new MappingJackson2XmlHttpMessageConverter(xmlMapper);
    }
}
