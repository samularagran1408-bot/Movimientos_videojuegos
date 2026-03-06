package GestionVideojuegos.example.videojuegos.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "games")
public class Games {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "price")
    private Long price;
    
    @Column(name = "multijugador")
    private Boolean multijugador;
    
    @Column(name = "idiomas")
    private String idiomas;
    
    @Column(name = "on_line")
    private Boolean onLine;
    
    @Column(name = "mode")
    private String mode;
    
    // RELACIÓN: Muchos juegos pertenecen a UN usuario
    @ManyToOne
    @JoinColumn(name = "id_users", referencedColumnName = "id")
    private Users user;
}