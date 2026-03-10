package GestionVideojuegos.example.videojuegos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MovementSummaryDTO {
    private Long userId;
    private String userName;
    private Long totalPurchases;
    private BigDecimal totalSpent;
}