package GestionVideojuegos.example.videojuegos.controller;

import GestionVideojuegos.example.videojuegos.dto.*;
import GestionVideojuegos.example.videojuegos.services.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movements")
@RequiredArgsConstructor
public class MovementController {
    
    private final MovementService movementService;
    
    // POST /movements - Crear
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MovementRequestDTO request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(movementService.createMovement(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }
    
    // GET /movements - Listar todos
    @GetMapping
    public ResponseEntity<List<MovementResponseDTO>> getAll() {
        return ResponseEntity.ok(movementService.getAllMovements());
    }
    
    // GET /movements/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movementService.getMovementById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
        }
    }
    
    // GET /movements/user/{userId} - Por usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(movementService.getMovementsByUser(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
        }
    }
    
    // PUT /movements/{id}/cancel - Cancelar
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movementService.cancelMovement(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
        }
    }
}