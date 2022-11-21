package com.kingsm01n.kpicursova.controller;

import com.kingsm01n.kpicursova.dto.ActorDto;
import com.kingsm01n.kpicursova.entity.Actor;
import com.kingsm01n.kpicursova.mapper.ActorMapper;
import com.kingsm01n.kpicursova.repository.ActorRepository;
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
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorRepository actorRepository;
    private final FilmRepository filmRepository;
    private final ActorMapper actorMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ActorDto> getAll() {
        return actorRepository.findAll()
            .stream()
            .map(actorMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ActorDto getById(@PathVariable Long id) {
        Actor actor = actorRepository.findById(id)
            .orElseThrow();

        return actorMapper.entityToDto(actor);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ActorDto create(@RequestBody @Valid ActorDto dto) {
        Actor actor = actorMapper.dtoToEntity(dto);

        if (dto.getFilmIds() != null && !dto.getFilmIds().isEmpty()) {
            actor.setFilms(filmRepository.findAllById(dto.getFilmIds()));
        }

        actor = actorRepository.saveAndFlush(actor);

        return actorMapper.entityToDto(actor);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ActorDto update(@RequestBody @Valid ActorDto dto) {
        Actor actor = actorRepository.findById(dto.getId())
            .orElseThrow();

        actorMapper.update(actor, dto);

        if (dto.getFilmIds() != null && !dto.getFilmIds().isEmpty()) {
            actor.setFilms(filmRepository.findAllById(dto.getFilmIds()));
        }

        actor = actorRepository.saveAndFlush(actor);

        return actorMapper.entityToDto(actor);
    }

}
