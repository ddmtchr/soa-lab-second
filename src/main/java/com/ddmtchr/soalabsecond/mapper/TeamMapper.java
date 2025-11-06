package com.ddmtchr.soalabsecond.mapper;

import com.ddmtchr.soalabsecond.dto.team.TeamRequestDto;
import com.ddmtchr.soalabsecond.dto.team.TeamResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CaveMapper.class})
public interface TeamMapper {

    TeamRequestDto responseDtoToRequestDto(TeamResponseDto responseDto);
}
