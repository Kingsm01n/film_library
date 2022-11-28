package com.kingsm01n.kpicursova.mapper;

import com.kingsm01n.kpicursova.dto.FilmDto;
import com.kingsm01n.kpicursova.entity.Actor;
import com.kingsm01n.kpicursova.entity.Film;
import com.kingsm01n.kpicursova.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface FilmMapper extends BaseMapper<FilmDto, Film> {

    @Override
    @Mapping(target = "genreIds", source = "genres")
    FilmDto entityToDto(Film actor);

    default List<Long> mapGenres(List<Genre> value) {
        if (value != null && !value.isEmpty()) {
            return value.stream()
                    .map(Genre::getId)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    default List<Long> mapActors(List<Actor> value) {
        if (value != null && !value.isEmpty()) {
            return value.stream()
                    .map(Actor::getId)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

}
