package com.kingsm01n.kpicursova.mapper;

import com.kingsm01n.kpicursova.dto.FilmDto;
import com.kingsm01n.kpicursova.entity.Film;
import org.mapstruct.Mapper;

@Mapper
public interface FilmMapper extends BaseMapper<FilmDto, Film> {
}
