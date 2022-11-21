package com.kingsm01n.kpicursova.mapper;

import com.kingsm01n.kpicursova.dto.SeasonDto;
import com.kingsm01n.kpicursova.entity.Season;
import org.mapstruct.Mapper;

@Mapper
public interface SeasonMapper extends BaseMapper<SeasonDto, Season> {
}
