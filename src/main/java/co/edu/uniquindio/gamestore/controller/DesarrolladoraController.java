package co.edu.uniquindio.gamestore.controller;

import co.edu.uniquindio.gamestore.entity.Desarrolladora;
import co.edu.uniquindio.gamestore.service.DesarrolladoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desarrolladoras")
public class DesarrolladoraController {

    private final DesarrolladoraService desarrolladoraService;

    public DesarrolladoraController(DesarrolladoraService desarrolladoraService){
        this.desarrolladoraService = desarrolladoraService;
    }

    //GET /api/desarrolladoras - Listar todas las desarrolladoras existentes
    @GetMapping
    public ResponseEntity<List<Desarrolladora>> listarDesarrolladoras() {
        return ResponseEntity.ok(desarrolladoraService.listarDesarrolladoras());
    }

    //POST /api/desarrolladoras - Crear una desarrolladora nueva
    @PostMapping
    public ResponseEntity<Desarrolladora> crearDesarrolladora(@RequestBody Desarrolladora desarrolladora) {
        Desarrolladora nueva = desarrolladoraService.crearDesarrolladora(desarrolladora);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }
}
