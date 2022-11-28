package com.kingsm01n.kpicursova.dto;

import com.kingsm01n.kpicursova.entity.Film;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * A DTO for the {@link Film} entity
 */
@Data
public class FilmDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final Double rating;
    private final List<Long> genreIds;
    private final List<Long> actorIds;
    private final byte[] trailer;
    private final byte[] video;
    private final byte[] image;
    private final List<Long> commentIds;
}