package GestionVideojuegos.example.videojuegos.controller;

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

