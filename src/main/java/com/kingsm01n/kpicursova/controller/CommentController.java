package com.kingsm01n.kpicursova.controller;

import com.kingsm01n.kpicursova.dto.CommentDto;
import com.kingsm01n.kpicursova.entity.Comment;
import com.kingsm01n.kpicursova.mapper.CommentMapper;
import com.kingsm01n.kpicursova.repository.CommentRepository;
import com.kingsm01n.kpicursova.repository.EpisodeRepository;
import com.kingsm01n.kpicursova.repository.FilmRepository;
import com.kingsm01n.kpicursova.repository.UserRepository;
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
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final FilmRepository filmRepository;
    private final EpisodeRepository episodeRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CommentDto> getAll() {
        return commentRepository.findAll()
            .stream()
            .map(commentMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public CommentDto getById(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow();

        return commentMapper.entityToDto(comment);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public CommentDto create(@RequestBody @Valid CommentDto dto) {
        Comment comment = commentMapper.dtoToEntity(dto);

        if (dto.getFilmId() != null) {
            comment.setFilm(filmRepository.findById(dto.getFilmId()).orElseThrow());
        }

        if (dto.getAuthorId() != null) {
            comment.setAuthor(userRepository.findById(dto.getAuthorId()).orElseThrow());
        }

        if (dto.getEpisodeId() != null) {
            comment.setEpisode(episodeRepository.findById(dto.getEpisodeId()).orElseThrow());
        }

        comment = commentRepository.saveAndFlush(comment);

        return commentMapper.entityToDto(comment);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public CommentDto update(@RequestBody @Valid CommentDto dto) {
        Comment comment = commentRepository.findById(dto.getId())
            .orElseThrow();

        commentMapper.update(comment, dto);

        if (dto.getFilmId() != null) {
            comment.setFilm(filmRepository.findById(dto.getFilmId()).orElseThrow());
        }

        if (dto.getAuthorId() != null) {
            comment.setAuthor(userRepository.findById(dto.getAuthorId()).orElseThrow());
        }

        if (dto.getEpisodeId() != null) {
            comment.setEpisode(episodeRepository.findById(dto.getEpisodeId()).orElseThrow());
        }

        comment = commentRepository.saveAndFlush(comment);

        return commentMapper.entityToDto(comment);
    }

}

