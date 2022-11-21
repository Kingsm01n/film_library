package com.kingsm01n.kpicursova.repository;

import com.kingsm01n.kpicursova.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
