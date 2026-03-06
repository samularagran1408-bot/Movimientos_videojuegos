package GestionVideojuegos.example.videojuegos.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;

    private String email;

    private Long age;
}
