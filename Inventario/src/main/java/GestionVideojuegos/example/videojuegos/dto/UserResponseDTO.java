package GestionVideojuegos.example.videojuegos.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;

    private String name;

    private String email;

    private Long age;
}