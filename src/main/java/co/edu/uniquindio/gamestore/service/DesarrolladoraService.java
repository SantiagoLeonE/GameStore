package co.edu.uniquindio.gamestore.service;

import co.edu.uniquindio.gamestore.entity.Desarrolladora;
import co.edu.uniquindio.gamestore.exception.ResourceNotFoundException;
import co.edu.uniquindio.gamestore.repository.DesarrolladoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesarrolladoraService {

    //Se inyecta el repositorio automáticamente
    private final DesarrolladoraRepository desarrolladoraRepository;

    public DesarrolladoraService(DesarrolladoraRepository desarrolladoraRepository) {
        this.desarrolladoraRepository = desarrolladoraRepository;
    }

    //Listar todas las desarrolladoras existentes
    public List<Desarrolladora> listarDesarrolladoras() {
        return desarrolladoraRepository.findAll();
    }

    //Crear una desarrolladora nueva
    public Desarrolladora crear(Desarrolladora desarrolladora) {
        return desarrolladoraRepository.save(desarrolladora);
    }

    //Buscar una desarrolladora por su Id
    public Desarrolladora buscarPorId(Long id) {
        return desarrolladoraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Desarrolladora no encontrada con Id: " + id));
    }


}
