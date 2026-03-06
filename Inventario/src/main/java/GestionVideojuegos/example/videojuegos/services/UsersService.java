package GestionVideojuegos.example.videojuegos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import GestionVideojuegos.example.videojuegos.Entities.Users;
import GestionVideojuegos.example.videojuegos.Repositories.UsersRepository;
import GestionVideojuegos.example.videojuegos.dto.MessageResponseDTO;
import GestionVideojuegos.example.videojuegos.dto.UserRequestDTO;
import GestionVideojuegos.example.videojuegos.dto.UserResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        Users user = new Users();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setAge(userRequestDTO.getAge());

        usersRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setAge(user.getAge());
        return response;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        List<UserResponseDTO> listUsers = new ArrayList<>();
        for (Users user : users) {
            UserResponseDTO response = new UserResponseDTO();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setAge(user.getAge());
            listUsers.add(response);
        }
        return listUsers;
    }

    public UserResponseDTO getUserById(Long id) {
        Users user = usersRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con id " + id));

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setAge(user.getAge());
        return response;
    }

    // Eliminar usuario por id
    public MessageResponseDTO deleteUser(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();
        if(usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            response.setMessage("Usuario eliminado correctamente");
            return response;
        }

        response.setMessage("Este usuario no existe");
        return response;
    }

    // Editar usuario por id
    public MessageResponseDTO editUser(Long id, UserRequestDTO userRequestDTO) {
        MessageResponseDTO response = new MessageResponseDTO();
        if(usersRepository.existsById(id)) {
            Users user = usersRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con id " + id));

            user.setName(userRequestDTO.getName());
            user.setEmail(userRequestDTO.getEmail());
            user.setAge(userRequestDTO.getAge());

            usersRepository.save(user);
            response.setMessage("Usuario editado correctamente");
            return response;
        }

        response.setMessage("Este usuario no existe");
        return response;
    }
}
