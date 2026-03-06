package GestionVideojuegos.example.videojuegos.dto;

import lombok.Data;

@Data
public class MovementRequestDTO {
    private Long userId;
    private Long gameId;
    private Integer quantity = 1;
}
