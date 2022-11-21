package com.kingsm01n.kpicursova.controller;

import com.kingsm01n.kpicursova.dto.SerialDto;
import com.kingsm01n.kpicursova.entity.Serial;
import com.kingsm01n.kpicursova.mapper.SerialMapper;
import com.kingsm01n.kpicursova.repository.ActorRepository;
import com.kingsm01n.kpicursova.repository.GenreRepository;
import com.kingsm01n.kpicursova.repository.SeasonRepository;
import com.kingsm01n.kpicursova.repository.SerialRepository;
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
@RequestMapping("/api/v1/serials")
@RequiredArgsConstructor
public class SerialController {

    private final SerialRepository serialRepository;
    private final SeasonRepository seasonRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final SerialMapper serialMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SerialDto> getAll() {
        return serialRepository.findAll()
            .stream()
            .map(serialMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public SerialDto getById(@PathVariable Long id) {
        Serial serial = serialRepository.findById(id)
            .orElseThrow();

        return serialMapper.entityToDto(serial);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SerialDto create(@RequestBody @Valid SerialDto dto) {
        Serial serial = serialMapper.dtoToEntity(dto);

        if (dto.getSeasonIds() != null && !dto.getSeasonIds().isEmpty()) {
            serial.setSeasons(seasonRepository.findAllById(dto.getSeasonIds()));
        }

        if (dto.getGenreIds() != null && !dto.getGenreIds().isEmpty()) {
            serial.setGenres(genreRepository.findAllById(dto.getGenreIds()));
        }

        if (dto.getActorIds() != null && !dto.getActorIds().isEmpty()) {
            serial.setActors(actorRepository.findAllById(dto.getActorIds()));
        }

        serial = serialRepository.saveAndFlush(serial);

        return serialMapper.entityToDto(serial);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SerialDto update(@RequestBody @Valid SerialDto dto) {
        Serial serial = serialRepository.findById(dto.getId())
            .orElseThrow();

        serialMapper.update(serial, dto);

        if (dto.getSeasonIds() != null && !dto.getSeasonIds().isEmpty()) {
            serial.setSeasons(seasonRepository.findAllById(dto.getSeasonIds()));
        }

        if (dto.getGenreIds() != null && !dto.getGenreIds().isEmpty()) {
            serial.setGenres(genreRepository.findAllById(dto.getGenreIds()));
        }

        if (dto.getActorIds() != null && !dto.getActorIds().isEmpty()) {
            serial.setActors(actorRepository.findAllById(dto.getActorIds()));
        }

        serial = serialRepository.saveAndFlush(serial);

        return serialMapper.entityToDto(serial);
    }

}

