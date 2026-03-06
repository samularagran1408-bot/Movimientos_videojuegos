package GestionVideojuegos.example.videojuegos.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionVideojuegos.example.videojuegos.Entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
}
