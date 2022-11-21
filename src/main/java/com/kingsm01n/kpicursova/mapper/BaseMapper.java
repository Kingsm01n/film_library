package com.kingsm01n.kpicursova.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface BaseMapper<Dto, Entity> {

    Dto entityToDto(Entity actor);

    Entity dtoToEntity(Dto dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Entity entity, Dto dto);

}
