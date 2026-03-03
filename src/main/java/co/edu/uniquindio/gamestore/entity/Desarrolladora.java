package co.edu.uniquindio.gamestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Desarrolladora {

    //El id actúa como la PK y se incrementa automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //El nombre es obligatorio y único
    @Column(nullable = false, unique = true)
    private String nombre;

    private String sitioWeb;

    //Se define la relación OnetoMany, la cual quiere decir que una desarrolladora tiene mucho videojuegos
    //El JsonIgnore nos permite evitar ciclos infinitos
    @JsonIgnore
    @OneToMany(mappedBy = "desarrolladora", cascade = CascadeType.ALL)
    private List<VideoJuego> videojuegos;
}
