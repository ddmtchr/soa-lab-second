package com.ddmtchr.soalabsecond.client;

import com.ddmtchr.soalabsecond.dto.team.TeamListDto;
import com.ddmtchr.soalabsecond.dto.team.TeamRequestDto;
import com.ddmtchr.soalabsecond.dto.team.TeamResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class TeamClient extends CrudClient<TeamRequestDto, TeamResponseDto> {

    public TeamClient(MappingJackson2XmlHttpMessageConverter converter) {
        super("/teams", converter, TeamRequestDto.class, TeamResponseDto.class);
    }

    public TeamListDto findAll() {
        return restClient.get()
                .uri(apiPath)
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .onStatus(HttpStatusCode::isError, errorHandler())
                .body(TeamListDto.class);
    }
}
