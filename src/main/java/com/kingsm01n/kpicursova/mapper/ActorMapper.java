package com.kingsm01n.kpicursova.mapper;

import com.kingsm01n.kpicursova.dto.ActorDto;
import com.kingsm01n.kpicursova.entity.Actor;
import org.mapstruct.Mapper;

@Mapper
public interface ActorMapper extends BaseMapper<ActorDto, Actor> {
}
