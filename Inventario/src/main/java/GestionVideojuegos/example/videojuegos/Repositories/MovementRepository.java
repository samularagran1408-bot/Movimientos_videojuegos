package GestionVideojuegos.example.videojuegos.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import GestionVideojuegos.example.videojuegos.Entities.Movements;

// Lista para registrar los movimientos de los videojuegos, como compras, ventas, etc. Cada movimiento está asociado a un usuario específico.
@Repository
public interface MovementRepository extends  JpaRepository<Movements, Long> {
     // Buscar movimientos por usuario (usa la relación ManyToOne)
    List<Movements> findByUserId(Long userId);
    
    // Buscar movimientos por juego
    List<Movements> findByGameId(Long gameId);
    
    // Buscar movimientos por estado
    List<Movements> findByStatus(String status);
    
    // Buscar movimientos por usuario y estado
    List<Movements> findByUserIdAndStatus(Long userId, String status);
    
    // Contar movimientos de un usuario
    Long countByUserId(Long userId);
    
    // Sumar total de compras de un usuario
    @Query("SELECT SUM(m.totalPrice) FROM Movements m WHERE m.user.id = :userId AND m.status = 'COMPLETADO'")
    BigDecimal sumTotalPriceByUserId(@Param("userId") Long userId);
}
