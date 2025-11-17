package com.ddmtchr.soalabsecond.client;

import com.ddmtchr.soalabsecond.dto.api.ApiErrorResponse;
import com.ddmtchr.soalabsecond.exception.BadRequestException;
import com.ddmtchr.soalabsecond.exception.InternalServerErrorException;
import com.ddmtchr.soalabsecond.exception.NotFoundException;
import com.ddmtchr.soalabsecond.exception.UnprocessableEntityException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestClient;

public abstract class CrudClient<REQ, RESP> {

    protected RestClient restClient;
    protected final ObjectMapper objectMapper;
    protected final Class<REQ> requestDtoClass;
    protected final Class<RESP> responseDtoClass;
    protected final String apiPath;

    public CrudClient(String apiPath, MappingJackson2XmlHttpMessageConverter converter, Class<REQ> requestDtoClass, Class<RESP> responseDtoClass) {
        this.apiPath = apiPath;
        this.objectMapper = converter.getObjectMapper();
        this.requestDtoClass = requestDtoClass;
        this.responseDtoClass = responseDtoClass;
    }

    @Autowired
    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public RESP create(REQ dto) {
        return restClient.post()
                .uri(apiPath)
                .body(dto)
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .onStatus(HttpStatusCode::isError, errorHandler())
                .body(responseDtoClass);
    }

    public RESP getById(Long id) {
        return restClient.get()
                .uri(apiPath + "/{id}", id)
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .onStatus(HttpStatusCode::isError, errorHandler())
                .body(responseDtoClass);
    }

    public RESP update(Long id, REQ dto) {
        return restClient.put()
                .uri(apiPath + "/{id}", id)
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .body(dto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, errorHandler())
                .body(responseDtoClass);
    }

    public void delete(Long id) {
        restClient.delete()
                .uri(apiPath + "/{id}", id)
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .onStatus(HttpStatusCode::isError, errorHandler())
                .body(Void.class);
    }

    protected RestClient.ResponseSpec.ErrorHandler errorHandler() {
        return (request, response) -> {
            ApiErrorResponse errorResponse = objectMapper.readValue(response.getBody(), ApiErrorResponse.class);
            switch (errorResponse.getStatus()) {
                case BAD_REQUEST:
                    throw new BadRequestException(errorResponse.getMessages());
                case NOT_FOUND:
                    throw new NotFoundException(errorResponse.getMessages());
                case UNPROCESSABLE_ENTITY:
                    throw new UnprocessableEntityException(errorResponse.getMessages());
                case INTERNAL_SERVER_ERROR:
                    throw new InternalServerErrorException(errorResponse.getMessages());
                default:
                    throw new InternalServerErrorException(errorResponse.getMessages());
            }
        };
    }
}

