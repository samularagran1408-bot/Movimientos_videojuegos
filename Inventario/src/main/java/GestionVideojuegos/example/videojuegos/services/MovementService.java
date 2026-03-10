package GestionVideojuegos.example.videojuegos.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import GestionVideojuegos.example.videojuegos.Entities.Games;
import GestionVideojuegos.example.videojuegos.Entities.Movements;
import GestionVideojuegos.example.videojuegos.Entities.Users;
import GestionVideojuegos.example.videojuegos.Repositories.GamesRepository;
import GestionVideojuegos.example.videojuegos.Repositories.MovementRepository;
import GestionVideojuegos.example.videojuegos.Repositories.UsersRepository;
import GestionVideojuegos.example.videojuegos.dto.MovementRequestDTO;
import GestionVideojuegos.example.videojuegos.dto.MovementResponseDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementService {

    private final MovementRepository movementRepository;
    private final UsersRepository usersRepository;
    private final GamesRepository gamesRepository;

    public MovementResponseDTO createMovement(MovementRequestDTO request) {
        Users user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con id " + request.getUserId()));

        Games game = gamesRepository.findById(request.getGameId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el juego con id " + request.getGameId()));

        Integer quantity = request.getQuantity() != null ? request.getQuantity() : 1;

        BigDecimal pricePerUnit = BigDecimal.valueOf(game.getPrice());
        BigDecimal totalPrice = pricePerUnit.multiply(BigDecimal.valueOf(quantity));

        Movements movement = new Movements();
        movement.setUser(user);
        movement.setGame(game);
        movement.setQuantity(quantity);
        movement.setTotalPrice(totalPrice);

        movementRepository.save(movement);

        return mapToResponse(movement);
    }

    public List<MovementResponseDTO> getMovementsByUser(Long userId) {
        List<Movements> movements = movementRepository.findByUserId(userId);
        return movements.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<MovementResponseDTO> getAllMovements() {
        return movementRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private MovementResponseDTO mapToResponse(Movements movement) {
        MovementResponseDTO response = new MovementResponseDTO();
        response.setId(movement.getId());
        response.setUserName(movement.getUser().getName());
        response.setGameName(movement.getGame().getName());
        response.setQuantity(movement.getQuantity());
        response.setTotalPrice(movement.getTotalPrice());
        response.setPurchaseDate(movement.getMovementDate());
        response.setStatus(movement.getStatus());
        return response;
    }
}

