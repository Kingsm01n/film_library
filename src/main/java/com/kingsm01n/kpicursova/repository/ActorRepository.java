package com.kingsm01n.kpicursova.repository;

import com.kingsm01n.kpicursova.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}