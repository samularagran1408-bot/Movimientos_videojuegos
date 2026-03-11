package GestionVideojuegos.example.videojuegos.services;

import GestionVideojuegos.example.videojuegos.Entities.*;
import GestionVideojuegos.example.videojuegos.Repositories.*;
import GestionVideojuegos.example.videojuegos.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovementService {
    
    private final MovementRepository movementRepository;
    private final UsersRepository usersRepository;
    private final GamesRepository gamesRepository;
    
    // Crear movimiento
    public MovementResponseDTO createMovement(MovementRequestDTO request) {
        Users user = usersRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Games game = gamesRepository.findById(request.getGameId())
            .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
        
        Movements movement = new Movements();
        movement.setUser(user);
        movement.setGame(game);
        movement.setQuantity(request.getQuantity());
        movement.setTotalPrice(
        BigDecimal.valueOf(game.getPrice())  // Convertir Long a BigDecimal
            .multiply(BigDecimal.valueOf(request.getQuantity())));
        return convertToDTO(movementRepository.save(movement));
    }
    
    // Listar todos
    public List<MovementResponseDTO> getAllMovements() {
        return movementRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    // Buscar por ID
    public MovementResponseDTO getMovementById(Long id) {
        Movements movement = movementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        return convertToDTO(movement);
    }
    
    // Buscar por usuario
    public List<MovementResponseDTO> getMovementsByUser(Long userId) {
        return movementRepository.findByUserId(userId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    // Cancelar movimiento
    public MovementResponseDTO cancelMovement(Long id) {
        Movements movement = movementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movement.setStatus("CANCELADO");
        return convertToDTO(movementRepository.save(movement));
    }
    
    // Convertir Entity a DTO
    private MovementResponseDTO convertToDTO(Movements m) {
        MovementResponseDTO dto = new MovementResponseDTO();
        dto.setId(m.getId());
        dto.setUserId(m.getUser().getId());
        dto.setUserName(m.getUser().getName());
        dto.setGameId(m.getGame().getId());
        dto.setGameName(m.getGame().getName());
        dto.setQuantity(m.getQuantity());
        dto.setTotalPrice(m.getTotalPrice());
        dto.setMovementDate(m.getMovementDate());
        dto.setStatus(m.getStatus());
        return dto;
    }
}