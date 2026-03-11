package GestionVideojuegos.example.videojuegos.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovementResponseDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long gameId;
    private String gameName;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime movementDate;
    private String status;
}
