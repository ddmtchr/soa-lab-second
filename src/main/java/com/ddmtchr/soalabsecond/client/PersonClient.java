package com.ddmtchr.soalabsecond.client;

import com.ddmtchr.soalabsecond.dto.person.PersonListDto;
import com.ddmtchr.soalabsecond.dto.person.PersonRequestDto;
import com.ddmtchr.soalabsecond.dto.person.PersonResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class PersonClient extends CrudClient<PersonRequestDto, PersonResponseDto> {

    public PersonClient(MappingJackson2XmlHttpMessageConverter converter) {
        super("/persons", converter, PersonRequestDto.class, PersonResponseDto.class);
    }

    public PersonListDto findAll() {
        return restClient.get()
                .uri(apiPath)
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .onStatus(HttpStatusCode::isError, errorHandler())
                .body(PersonListDto.class);
    }
}
