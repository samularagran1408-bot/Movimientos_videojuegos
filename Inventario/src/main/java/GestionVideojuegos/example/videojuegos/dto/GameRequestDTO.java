package GestionVideojuegos.example.videojuegos.dto;

import lombok.Data;

@Data
public class GameRequestDTO {
    private String name;
    private Long price;
    private Boolean multijugador;
    private String idiomas;
    private Boolean onLine;
    private String mode;
    private Long userId;  // Llave foránea
}
