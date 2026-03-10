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
import GestionVideojuegos.example.videojuegos.dto.MovementSummaryDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementService {

    private final MovementRepository movementRepository;
    private final UsersRepository usersRepository;
    private final GamesRepository gamesRepository;

     
    @Transactional
    public MovementResponseDTO createMovement(MovementRequestDTO request) {
        Users user = usersRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("Usuario con ID " + request.getUserId() + " no encontrado"));
        
        Games game = gamesRepository.findById(request.getGameId())
            .orElseThrow(() -> new RuntimeException("Juego con ID " + request.getGameId() + " no encontrado"));
        
        Movements movement = new Movements();
        movement.setUser(user);  
        movement.setGame(game); 
        movement.setQuantity(request.getQuantity());
        
        BigDecimal totalPrice = game.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));
        movement.setTotalPrice(totalPrice);
        
        Movements savedMovement = movementRepository.save(movement);
        
        return convertToDTO(savedMovement);
    }
    
    // Obtener todos los movimientos
    public List<MovementResponseDTO> getAllMovements() {
        return movementRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    // Obtener movimiento por ID
    public MovementResponseDTO getMovementById(Long id) {
        Movements movement = movementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Movimiento con ID " + id + " no encontrado"));
        return convertToDTO(movement);
    }
    
    // Obtener movimientos de un usuario específico
    public List<MovementResponseDTO> getMovementsByUser(Long userId) {
        // Verificar que el usuario existe
        if (!usersRepository.existsById(userId)) {
            throw new RuntimeException("Usuario con ID " + userId + " no encontrado");
        }
        
        return movementRepository.findByUserId(userId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    // Obtener movimientos por estado
    public List<MovementResponseDTO> getMovementsByStatus(String status) {
        return movementRepository.findByStatus(status)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    // Obtener movimientos de un usuario por estado
    public List<MovementResponseDTO> getMovementsByUserAndStatus(Long userId, String status) {
        return movementRepository.findByUserIdAndStatus(userId, status)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    // Cancelar un movimiento (cambiar estado a CANCELADO)
    @Transactional
    public MovementResponseDTO cancelMovement(Long id) {
        Movements movement = movementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Movimiento con ID " + id + " no encontrado"));
        
        movement.setStatus("CANCELADO");
        Movements cancelled = movementRepository.save(movement);
        return convertToDTO(cancelled);
    }
    
    // Contar movimientos de un usuario
    public Long countMovementsByUser(Long userId) {
        if (!usersRepository.existsById(userId)) {
            throw new RuntimeException("Usuario con ID " + userId + " no encontrado");
        }
        return movementRepository.countByUserId(userId);
    }
    
    // Sumar total gastado por un usuario
    public BigDecimal getTotalSpentByUser(Long userId) {
        if (!usersRepository.existsById(userId)) {
            throw new RuntimeException("Usuario con ID " + userId + " no encontrado");
        }
        BigDecimal total = movementRepository.sumTotalPriceByUserId(userId);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    // Obtener resumen de compras de todos los usuarios
    public List<MovementSummaryDTO> getPurchaseSummary() {
        List<Users> users = usersRepository.findAll();
        
        return users.stream()
            .map(user -> {
                Long count = movementRepository.countByUserId(user.getId());
                BigDecimal total = movementRepository.sumTotalPriceByUserId(user.getId());
                return new MovementSummaryDTO(
                    user.getId(),
                    user.getName(),
                    count,
                    total != null ? total : BigDecimal.ZERO
                );
            })
            .collect(Collectors.toList());
    }
    
    // Método privado para convertir Entity a DTO
    private MovementResponseDTO convertToDTO(Movements movement) {
        MovementResponseDTO dto = new MovementResponseDTO();
        dto.setId(movement.getId());
        dto.setUserId(movement.getUser().getId());
        dto.setUserName(movement.getUser().getName());
        dto.setGameId(movement.getGame().getId());
        dto.setGameName(movement.getGame().getName());
        dto.setQuantity(movement.getQuantity());
        dto.setTotalPrice(movement.getTotalPrice());
        dto.setMovementDate(movement.getMovementDate());
        dto.setStatus(movement.getStatus());
        return dto;
    }
}

