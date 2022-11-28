package com.kingsm01n.kpicursova.controller;

import com.kingsm01n.kpicursova.dto.FilmDto;
import com.kingsm01n.kpicursova.entity.Film;
import com.kingsm01n.kpicursova.mapper.FilmMapper;
import com.kingsm01n.kpicursova.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/upload")
@RequiredArgsConstructor
public class UploadController {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    @PutMapping("/films/{id}/video")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public FilmDto uploadVideoToFilm(@PathVariable Long id, @RequestPart MultipartFile file) throws IOException {
        Film film = filmRepository.findById(id)
                .orElseThrow();

        if (file != null) {
            film.setVideo(file.getBytes());
        }

        film = filmRepository.saveAndFlush(film);

        return filmMapper.entityToDto(film);
    }

    @PutMapping("/films/{id}/trailer")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public FilmDto uploadTrailerToFilm(@PathVariable Long id, @RequestPart MultipartFile file) throws IOException {
        Film film = filmRepository.findById(id)
                .orElseThrow();

        if (file != null) {
            film.setTrailer(file.getBytes());
        }

        film = filmRepository.saveAndFlush(film);

        return filmMapper.entityToDto(film);
    }

    @PutMapping("/films/{id}/image")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public FilmDto uploadImageToFilm(@PathVariable Long id, @RequestPart MultipartFile file) throws IOException {
        Film film = filmRepository.findById(id)
                .orElseThrow();

        if (file != null) {
            film.setImage(file.getBytes());
        }

        film = filmRepository.saveAndFlush(film);

        return filmMapper.entityToDto(film);
    }
}
