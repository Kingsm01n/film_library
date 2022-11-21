package com.kingsm01n.kpicursova.controller;

import com.kingsm01n.kpicursova.dto.EpisodeDto;
import com.kingsm01n.kpicursova.entity.Episode;
import com.kingsm01n.kpicursova.mapper.EpisodeMapper;
import com.kingsm01n.kpicursova.repository.EpisodeRepository;
import com.kingsm01n.kpicursova.repository.SeasonRepository;
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
@RequestMapping("/api/v1/episodes")
@RequiredArgsConstructor
public class EpisodeController {

    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;
    private final EpisodeMapper episodeMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<EpisodeDto> getAll() {
        return episodeRepository.findAll()
            .stream()
            .map(episodeMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public EpisodeDto getById(@PathVariable Long id) {
        Episode episode = episodeRepository.findById(id)
            .orElseThrow();

        return episodeMapper.entityToDto(episode);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public EpisodeDto create(@RequestBody @Valid EpisodeDto dto) {
        Episode episode = episodeMapper.dtoToEntity(dto);

        if (dto.getSeasonId() != null) {
            episode.setSeason(seasonRepository.findById(dto.getSeasonId()).orElseThrow());
        }

        episode = episodeRepository.saveAndFlush(episode);

        return episodeMapper.entityToDto(episode);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public EpisodeDto update(@RequestBody @Valid EpisodeDto dto) {
        Episode episode = episodeRepository.findById(dto.getId())
            .orElseThrow();

        episodeMapper.update(episode, dto);

        if (dto.getSeasonId() != null) {
            episode.setSeason(seasonRepository.findById(dto.getSeasonId()).orElseThrow());
        }

        episode = episodeRepository.saveAndFlush(episode);

        return episodeMapper.entityToDto(episode);
    }

}
