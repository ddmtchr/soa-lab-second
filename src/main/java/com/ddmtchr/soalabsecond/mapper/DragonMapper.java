package com.ddmtchr.soalabsecond.mapper;

import com.ddmtchr.soalabsecond.dto.dragon.DragonRequestDto;
import com.ddmtchr.soalabsecond.dto.dragon.DragonResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CoordinatesMapper.class, PersonMapper.class})
public interface DragonMapper {

    DragonRequestDto responseDtoToRequestDto(DragonResponseDto responseDto);
}
