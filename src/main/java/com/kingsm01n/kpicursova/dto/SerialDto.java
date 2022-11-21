package com.kingsm01n.kpicursova.dto;

import com.kingsm01n.kpicursova.entity.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * A DTO for the {@link Serial} entity
 */
@Data
public class SerialDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final Double rating;
    private final List<Long> seasonIds;
    private final List<Long> genreIds;
    private final List<Long> actorIds;
    private final byte[] trailer;
}