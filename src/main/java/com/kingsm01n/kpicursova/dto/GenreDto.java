package com.kingsm01n.kpicursova.dto;

import com.kingsm01n.kpicursova.entity.Genre;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * A DTO for the {@link Genre} entity
 */
@Data
public class GenreDto implements Serializable {
    private final Long id;
    private final String name;
    private final List<Long> filmIds;
}