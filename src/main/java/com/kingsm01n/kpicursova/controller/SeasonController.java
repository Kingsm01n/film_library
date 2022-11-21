package com.kingsm01n.kpicursova.controller;

import com.kingsm01n.kpicursova.dto.SeasonDto;
import com.kingsm01n.kpicursova.entity.Season;
import com.kingsm01n.kpicursova.mapper.SeasonMapper;
import com.kingsm01n.kpicursova.repository.EpisodeRepository;
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
@RequestMapping("/api/v1/seasons")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonRepository seasonRepository;
    private final SerialRepository serialRepository;
    private final EpisodeRepository episodeRepository;
    private final SeasonMapper seasonMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SeasonDto> getAll() {
        return seasonRepository.findAll()
            .stream()
            .map(seasonMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public SeasonDto getById(@PathVariable Long id) {
        Season season = seasonRepository.findById(id)
            .orElseThrow();

        return seasonMapper.entityToDto(season);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SeasonDto create(@RequestBody @Valid SeasonDto dto) {
        Season season = seasonMapper.dtoToEntity(dto);

        if (dto.getEpisodeIds() != null && !dto.getEpisodeIds().isEmpty()) {
            season.setEpisodes(episodeRepository.findAllById(dto.getEpisodeIds()));
        }

        if (dto.getSerialId() != null) {
            season.setSerial(serialRepository.findById(dto.getSerialId()).orElseThrow());
        }

        season = seasonRepository.saveAndFlush(season);

        return seasonMapper.entityToDto(season);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SeasonDto update(@RequestBody @Valid SeasonDto dto) {
        Season season = seasonRepository.findById(dto.getId())
            .orElseThrow();

        seasonMapper.update(season, dto);

        if (dto.getEpisodeIds() != null && !dto.getEpisodeIds().isEmpty()) {
            season.setEpisodes(episodeRepository.findAllById(dto.getEpisodeIds()));
        }

        if (dto.getSerialId() != null) {
            season.setSerial(serialRepository.findById(dto.getSerialId()).orElseThrow());
        }

        season = seasonRepository.saveAndFlush(season);

        return seasonMapper.entityToDto(season);
    }

}

