package GestionVideojuegos.example.videojuegos.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovementResponseDTO {
    private Long id;
    private String userName;
    private String gameName;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime purchaseDate;
    private String status;    
}
