package com.kingsm01n.kpicursova.repository;

import com.kingsm01n.kpicursova.entity.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialRepository extends JpaRepository<Serial, Long> {
}