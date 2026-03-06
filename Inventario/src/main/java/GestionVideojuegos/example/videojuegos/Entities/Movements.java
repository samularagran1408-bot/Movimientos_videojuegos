package GestionVideojuegos.example.videojuegos.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "movements")
public class Movements {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
    
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Games game;
    
    private Integer quantity = 1;
    
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    
    @Column(name = "movement_date")
    private LocalDateTime movementDate = LocalDateTime.now();
    
    private String status = "COMPLETADO";
}