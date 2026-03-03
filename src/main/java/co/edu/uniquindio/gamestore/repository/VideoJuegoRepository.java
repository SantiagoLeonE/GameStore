package co.edu.uniquindio.gamestore.repository;

import co.edu.uniquindio.gamestore.entity.VideoJuego;
import co.edu.uniquindio.gamestore.enums.Genero;

import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoJuegoRepository extends JpaRepository<VideoJuego,Long> {

    //Se aplica el Derived Query. Buscar un videojuego por su género
    List<VideoJuego> findByGenero(Genero genero);

    //Se aplica el Derived Query. Buscar un videojuego por su titulo, en donde se ignoran las mayúsculas y las minúsculas
    List<VideoJuego> findByTituloContainingIgnoreCase(String titulo);

    //Consulta JPQL: Se buscan videojuegos que están dentro de un rango de precios
    @Query("SELECT v FROM VideoJuego v WHERE v.precio BETWEEN :min and :max")
    List<VideoJuego> buscarPorRangoDePrecio(@Param("min") Double min, @Param("max") Double max);

    //Consulta Nativa: Obtener los últimos 5 videojuegos registrados ordenados por la fechaCreacion de forma descendente
    @NativeQuery(value = "SELECT * FROM VideoJuego ORDER  BY fecha_creacion DESC LIMIT 5")
    List<VideoJuego> obtenerUltimos5();
}
