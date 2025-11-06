package com.ddmtchr.soalabsecond.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TeamMapper.class})
public interface PersonMapper {

}
