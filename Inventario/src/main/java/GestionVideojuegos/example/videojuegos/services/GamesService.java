package GestionVideojuegos.example.videojuegos.services;


@Service
@RequiredArgsConstructor
public class GamesService {
   



    private final GamesRepository gamesRepository;
    private final UserRepository usersRepository;

    public GameResponseDTO createGame(GameRequestDTO gameRequestDTO) {
        Games game = new Games();
        game.setName(gameRequestDTO.getName());
        game.setPrice(gameRequestDTO.getPrice());
        game.setMultijugador(gameRequestDTO.getMultijugador());
        game.setIdiomas(gameRequestDTO.getIdiomas());
        game.setOnLine(gameRequestDTO.getOnLine());
        game.setMode(gameRequestDTO.getMode());

        if (gameRequestDTO.getUserId() != null) {
            Users user = usersRepository.findById(gameRequestDTO.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con id " + gameRequestDTO.getUserId()));
            game.setUser(user);
        } else {
            game.setUser(null);
        }

gamesRepository.save(game);

        GameResponseDTO response = new GameResponseDTO();
        response.setId(game.getId());
        response.setName(game.getName());
        response.setPrice(game.getPrice());
        response.setMultijugador(game.getMultijugador());
        response.setIdiomas(game.getIdiomas());
        response.setOnLine(game.getOnLine());
        response.setMode(game.getMode());
        response.setUserId(game.getUser() != null ? game.getUser().getId() : null);

        return response;
    }

    public List<GameResponseDTO> getAllGames() {
        List<Games> games = gamesRepository.findAll();
        List<GameResponseDTO> listGames = new ArrayList<>();

        for (Games game : games) {
            GameResponseDTO response = new GameResponseDTO();
            response.setId(game.getId());
            response.setName(game.getName());
            response.setPrice(game.getPrice());
            response.setMultijugador(game.getMultijugador());
            response.setIdiomas(game.getIdiomas());
            response.setOnLine(game.getOnLine());
            response.setMode(game.getMode());
            response.setUserId(game.getUser() != null ? game.getUser().getId() : null);
            listGames.add(response);
        }

        return listGames;
    }

    public GameResponseDTO getGameById(Long id) {
        Games game = gamesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el juego con id " + id));

        GameResponseDTO response = new GameResponseDTO();
        response.setId(game.getId());
        response.setName(game.getName());
        response.setPrice(game.getPrice());
        response.setMultijugador(game.getMultijugador());
        response.setIdiomas(game.getIdiomas());
        response.setOnLine(game.getOnLine());
        response.setMode(game.getMode());
        response.setUserId(game.getUser() != null ? game.getUser().getId() : null);

        return response;
    }

    public MessageResponseDTO deleteGame(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();
        if (gamesRepository.existsById(id)) {
            gamesRepository.deleteById(id);
            response.setMessage("Juego eliminado correctamente");
            return response;
        }

        response.setMessage("Este juego no existe");
        return response;
    }

    public MessageResponseDTO editGame(Long id, GameRequestDTO gameRequestDTO) {
        MessageResponseDTO response = new MessageResponseDTO();

        if (gamesRepository.existsById(id)) {
            Games game = gamesRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el juego con id " + id));

            game.setName(gameRequestDTO.getName());
            game.setPrice(gameRequestDTO.getPrice());
            game.setMultijugador(gameRequestDTO.getMultijugador());
            game.setIdiomas(gameRequestDTO.getIdiomas());
            game.setOnLine(gameRequestDTO.getOnLine());
            game.setMode(gameRequestDTO.getMode());

            if (gameRequestDTO.getUserId() != null) {
                Users user = usersRepository.findById(gameRequestDTO.getUserId())
                        .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con id " + gameRequestDTO.getUserId()));
                game.setUser(user);
            }

            gamesRepository.save(game);
            response.setMessage("Juego editado correctamente");
            return response;
        }

        response.setMessage("Este juego no existe");
        return response;
    }
}

