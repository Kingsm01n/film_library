package com.kingsm01n.kpicursova.repository;

import com.kingsm01n.kpicursova.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}