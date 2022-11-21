package com.kingsm01n.kpicursova.repository;

import com.kingsm01n.kpicursova.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}