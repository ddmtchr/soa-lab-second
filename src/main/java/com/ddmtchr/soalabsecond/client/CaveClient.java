package com.ddmtchr.soalabsecond.client;

import com.ddmtchr.soalabsecond.dto.cave.CaveRequestDto;
import com.ddmtchr.soalabsecond.dto.cave.CaveResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class CaveClient extends CrudClient<CaveRequestDto, CaveResponseDto> {

    public CaveClient(MappingJackson2XmlHttpMessageConverter converter) {
        super("/caves", converter, CaveRequestDto.class, CaveResponseDto.class);
    }
}
