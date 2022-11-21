package com.kingsm01n.kpicursova.dto;

import com.kingsm01n.kpicursova.entity.enums.Role;
import com.kingsm01n.kpicursova.entity.User;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String email;
    private final String username;
    private final String password;
    private final Role role;
}