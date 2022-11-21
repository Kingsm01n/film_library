package com.kingsm01n.kpicursova.controller;

import com.kingsm01n.kpicursova.dto.GenreDto;
import com.kingsm01n.kpicursova.entity.Genre;
import com.kingsm01n.kpicursova.mapper.GenreMapper;
import com.kingsm01n.kpicursova.repository.GenreRepository;
import com.kingsm01n.kpicursova.repository.FilmRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepository;
    private final FilmRepository filmRepository;
    private final GenreMapper genreMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<GenreDto> getAll() {
        return genreRepository.findAll()
            .stream()
            .map(genreMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public GenreDto getById(@PathVariable Long id) {
        Genre genre = genreRepository.findById(id)
            .orElseThrow();

        return genreMapper.entityToDto(genre);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public GenreDto create(@RequestBody @Valid GenreDto dto) {
        Genre genre = genreMapper.dtoToEntity(dto);

        if (dto.getFilmIds() != null && !dto.getFilmIds().isEmpty()) {
            genre.setFilms(filmRepository.findAllById(dto.getFilmIds()));
        }

        genre = genreRepository.saveAndFlush(genre);

        return genreMapper.entityToDto(genre);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public GenreDto update(@RequestBody @Valid GenreDto dto) {
        Genre genre = genreRepository.findById(dto.getId())
            .orElseThrow();

        genreMapper.update(genre, dto);

        if (dto.getFilmIds() != null && !dto.getFilmIds().isEmpty()) {
            genre.setFilms(filmRepository.findAllById(dto.getFilmIds()));
        }

        genre = genreRepository.saveAndFlush(genre);

        return genreMapper.entityToDto(genre);
    }

}

