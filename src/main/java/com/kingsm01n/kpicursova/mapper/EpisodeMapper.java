package com.kingsm01n.kpicursova.mapper;

import com.kingsm01n.kpicursova.dto.EpisodeDto;
import com.kingsm01n.kpicursova.entity.Episode;
import org.mapstruct.Mapper;

@Mapper
public interface EpisodeMapper extends BaseMapper<EpisodeDto, Episode> {
}
