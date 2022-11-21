package com.kingsm01n.kpicursova.repository;

import com.kingsm01n.kpicursova.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
}