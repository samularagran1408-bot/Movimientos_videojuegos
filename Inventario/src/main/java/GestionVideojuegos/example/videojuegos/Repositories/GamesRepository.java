package GestionVideojuegos.example.videojuegos.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionVideojuegos.example.videojuegos.Entities.Games;


// Repositorio para la entidad Users, extiende JpaRepository para obtener métodos CRUD automáticamente
@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {
    
}
