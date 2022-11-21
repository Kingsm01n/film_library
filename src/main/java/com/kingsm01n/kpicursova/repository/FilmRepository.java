package com.kingsm01n.kpicursova.repository;

import com.kingsm01n.kpicursova.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}