package com.kingsm01n.kpicursova.mapper;

import com.kingsm01n.kpicursova.dto.GenreDto;
import com.kingsm01n.kpicursova.entity.Genre;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper extends BaseMapper<GenreDto, Genre> {
}
