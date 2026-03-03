package co.edu.uniquindio.gamestore.entity;

import co.edu.uniquindio.gamestore.enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class VideoJuego {

    //El id actúa como la PK y se incrementa automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //El título del videojuego se define como obligatorio y con un máximo de 100 caracteres
    @Column(nullable = false, length = 200)
    private String titulo;

    //El precio del videojuego se define como obligatorio
    @Column(nullable = false)
    private Double precio;

    //Se define el codigo del videojuego como obligatorio
    @Column(unique = true, nullable = false)
    private String codigoRegistro;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    //Se define la relación ManytoOne, la cual quiere decir que muchos videojuegos pertenecen a una desarrolladora
    @ManyToOne
    @JoinColumn(name = "desarrolladora_id")
    private Desarrolladora desarrolladora;

    //Fechas automatizadas - Se define la fechaCreacion y fechaActualizacion, pero la fechaCreacion tiene la condición de que no puede ser modificada después de la creación
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    //Este valor no existirá en la base de datos
    @Transient
    private Double precioConIva;

    //Nos permite gestionar ambas fechas de manera automáticamente justo antes de crear el videojuego
    @PrePersist
    public void antesdeGuardar() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    //Actualiza la fechaActualizacion de manera automática justo antes de realizar la actualización
    @PreUpdate
    public void antesdeActualizar() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
