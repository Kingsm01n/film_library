package com.kingsm01n.kpicursova.dto;

import com.kingsm01n.kpicursova.entity.Season;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * A DTO for the {@link Season} entity
 */
@Data
public class SeasonDto implements Serializable {
    private final Long id;
    private final Integer number;
    private final Long serialId;
    private final List<Long> episodeIds;
}