package com.kingsm01n.kpicursova.mapper;

import com.kingsm01n.kpicursova.dto.CommentDto;
import com.kingsm01n.kpicursova.entity.Comment;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<CommentDto, Comment> {
}
