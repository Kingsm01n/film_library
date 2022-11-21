package com.kingsm01n.kpicursova.repository;

import com.kingsm01n.kpicursova.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
