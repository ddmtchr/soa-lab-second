package com.ddmtchr.soalabsecond.client;

import com.ddmtchr.soalabsecond.dto.dragon.DragonRequestDto;
import com.ddmtchr.soalabsecond.dto.dragon.DragonResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class DragonClient extends CrudClient<DragonRequestDto, DragonResponseDto> {

    public DragonClient(MappingJackson2XmlHttpMessageConverter converter) {
        super("/dragons", converter, DragonRequestDto.class, DragonResponseDto.class);
    }
}
