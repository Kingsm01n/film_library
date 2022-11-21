package com.kingsm01n.kpicursova.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * A DTO for the {@link com.kingsm01n.kpicursova.entity.Episode} entity
 */
@Data
public class EpisodeDto implements Serializable {
    private final Long id;
    private final Integer number;
    private final Long seasonId;
    private final List<Long> commentIds;
}