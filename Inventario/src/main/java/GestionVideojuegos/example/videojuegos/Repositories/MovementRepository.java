package GestionVideojuegos.example.videojuegos.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import GestionVideojuegos.example.videojuegos.Entities.Movements;

// Lista para registrar los movimientos de los videojuegos, como compras, ventas, etc. Cada movimiento está asociado a un usuario específico.
@Repository
public interface MovementRepository extends  JpaRepository<Movements, Long> {
    List<Movements> findByUserId(Long userId);
}
