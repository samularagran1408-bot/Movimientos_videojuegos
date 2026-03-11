package GestionVideojuegos.example.videojuegos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GestionVideojuegos.example.videojuegos.dto.GameRequestDTO;
import GestionVideojuegos.example.videojuegos.dto.GameResponseDTO;
import GestionVideojuegos.example.videojuegos.dto.MessageResponseDTO;
import GestionVideojuegos.example.videojuegos.services.GamesService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GamesController {

    private final GamesService gamesService;

    @PostMapping
    public ResponseEntity<GameResponseDTO> createGame(@RequestBody GameRequestDTO gameRequestDTO) {
        try {
            GameResponseDTO response = gamesService.createGame(gameRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GameResponseDTO>> getAllGames() {
        try {
            List<GameResponseDTO> games = gamesService.getAllGames();
            return ResponseEntity.status(HttpStatus.OK).body(games);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> getGameById(@PathVariable Long id) {
        try {
            GameResponseDTO game = gamesService.getGameById(id);
            return ResponseEntity.status(HttpStatus.OK).body(game);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteGame(@PathVariable Long id) {
        try {
            MessageResponseDTO response = gamesService.deleteGame(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> editGame(@PathVariable Long id, @RequestBody GameRequestDTO gameRequestDTO) {
        try {
            MessageResponseDTO response = gamesService.editGame(id, gameRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

