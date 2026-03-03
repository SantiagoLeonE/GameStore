package co.edu.uniquindio.gamestore.controller;

import co.edu.uniquindio.gamestore.entity.VideoJuego;
import co.edu.uniquindio.gamestore.service.VideoJuegoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideoJuegoController {

    private final VideoJuegoService videoJuegoService;

    public VideoJuegoController(VideoJuegoService videoJuegoService) {
        this.videoJuegoService = videoJuegoService;
    }

    //GET /api/videojuegos - Listar todos los videojuegos existentes
    @GetMapping
    public ResponseEntity<List<VideoJuego>> listarVideoJuegos() {
        return ResponseEntity.ok(videoJuegoService.listarVideoJuegos());
    }

    //GET /api/videojuegos/{id} - Obtener un videojuego por su Id
    @GetMapping("/{id}")
    public ResponseEntity<VideoJuego> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(videoJuegoService.buscarPorId(id));
    }

    //POST /api/videojuegos - Crear un videojuego
    @PostMapping
    public ResponseEntity<VideoJuego> crearVideoJuego(@RequestBody VideoJuego videoJuego) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videoJuegoService.crearVideoJuego(videoJuego));
    }

    //PUT /api/videojuegos/{id} - Actualizar por completo un videojuego
    @PutMapping("/{id}")
    public ResponseEntity<VideoJuego> actualizarVideoJuego(@PathVariable Long id, @RequestBody VideoJuego videoJuego) {
        return ResponseEntity.ok(videoJuegoService.actualizarVideoJuego(id, videoJuego));
    }

    //DELETE /api/videojuegos/{id} - Eliminar un videojuego
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVideoJuego(@PathVariable Long id) {
        videoJuegoService.eliminarVideojuego(id);
        return ResponseEntity.noContent().build(); //204 No content
    }

    //GET /api/videojuegos/buscar?titulo=xyz - Buscar un videojuego por su título
    @GetMapping("/buscar")
    public ResponseEntity<List<VideoJuego>> buscarPorTitulo(@RequestParam String titulo){
        return ResponseEntity.ok(videoJuegoService.buscarPorTitulo(titulo));
    }

    //GET /api/videojuegos/rango-precio?min=10&max=60 - Buscar por un rango de precios
    @GetMapping("/rango-precio")
    public ResponseEntity<List<VideoJuego>> buscarPorRango(@RequestParam Double min, @RequestParam Double max){
        return ResponseEntity.ok(videoJuegoService.buscarPorRangoDePrecio(min, max));
    }

    //PATCH /api/videojuegos/{id}/descuento?porcentaje=10 - Aplicar descuento a un videojuego
    @PatchMapping("/{id}/descuento")
    public ResponseEntity<VideoJuego> aplicarDescuento(@PathVariable Long id, @RequestParam Double porcentaje) {
        return ResponseEntity.ok(videoJuegoService.aplciarDescuento(id, porcentaje));
    }


}
