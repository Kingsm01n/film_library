package com.kingsm01n.kpicursova.controller;

import com.kingsm01n.kpicursova.dto.FilmDto;
import com.kingsm01n.kpicursova.entity.Film;
import com.kingsm01n.kpicursova.mapper.FilmMapper;
import com.kingsm01n.kpicursova.repository.ActorRepository;
import com.kingsm01n.kpicursova.repository.FilmRepository;
import com.kingsm01n.kpicursova.repository.GenreRepository;
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
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final FilmMapper filmMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<FilmDto> getAll() {
        return filmRepository.findAll()
            .stream()
            .map(filmMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public FilmDto getById(@PathVariable Long id) {
        Film film = filmRepository.findById(id)
            .orElseThrow();

        return filmMapper.entityToDto(film);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public FilmDto create(@RequestBody @Valid FilmDto dto) {
        Film film = filmMapper.dtoToEntity(dto);

        if (dto.getGenreIds() != null && !dto.getGenreIds().isEmpty()) {
            film.setGenres(genreRepository.findAllById(dto.getGenreIds()));
        }

        if (dto.getActorIds() != null && !dto.getActorIds().isEmpty()) {
            film.setActors(actorRepository.findAllById(dto.getActorIds()));
        }

        film = filmRepository.saveAndFlush(film);

        return filmMapper.entityToDto(film);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public FilmDto update(@RequestBody @Valid FilmDto dto) {
        Film film = filmRepository.findById(dto.getId())
            .orElseThrow();

        filmMapper.update(film, dto);

        if (dto.getGenreIds() != null && !dto.getGenreIds().isEmpty()) {
            film.setGenres(genreRepository.findAllById(dto.getGenreIds()));
        }

        if (dto.getActorIds() != null && !dto.getActorIds().isEmpty()) {
            film.setActors(actorRepository.findAllById(dto.getActorIds()));
        }

        film = filmRepository.saveAndFlush(film);

        return filmMapper.entityToDto(film);
    }

}
