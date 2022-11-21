package com.kingsm01n.kpicursova.dto;

import com.kingsm01n.kpicursova.entity.Comment;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * A DTO for the {@link Comment} entity
 */
@Data
public class CommentDto implements Serializable {
    private final Long id;
    private final Long authorId;
    private final List<Long> responseIds;
    private final Long responseToId;
    private final String comment;
    private final Long filmId;
    private final Long episodeId;
}