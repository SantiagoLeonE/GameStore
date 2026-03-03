package co.edu.uniquindio.gamestore.service;

import co.edu.uniquindio.gamestore.entity.Desarrolladora;
import co.edu.uniquindio.gamestore.entity.VideoJuego;
import co.edu.uniquindio.gamestore.exception.ResourceNotFoundException;
import co.edu.uniquindio.gamestore.repository.VideoJuegoRepository;

import java.util.List;

public class VideoJuegoService {

    private final VideoJuegoRepository videoJuegoRepository;
    private final DesarrolladoraService desarrolladoraService;

    public VideoJuegoService(VideoJuegoRepository videoJuegoRepository, DesarrolladoraService desarrolladoraService) {
        this.videoJuegoRepository = videoJuegoRepository;
        this.desarrolladoraService = desarrolladoraService;
    }

    //Calcular el precio con el iva
    private void calcularIva(VideoJuego videoJuego) {
        videoJuego.setPrecioConIva(videoJuego.getPrecio() * 1.19);
    }

    private void calcularIvaLista(List<VideoJuego> lista) {
        lista.forEach(this::calcularIva);
    }

    //Validaciones que se deben realizar antes de crear un videojuego
    private void validar(VideoJuego videoJuego) {
        if(videoJuego.getTitulo() == null || videoJuego.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if(videoJuego.getPrecio() == null || videoJuego.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    //Listar todos los videojuegos existentes
    public List<VideoJuego> listarVideoJuegos() {
        List<VideoJuego> videoJuegos = videoJuegoRepository.findAll();
        calcularIvaLista(videoJuegos);
        return videoJuegos;
    }

    //Buscar un videojuego por su Id
    public VideoJuego buscarPorId(Long id) {
        VideoJuego videoJuego = videoJuegoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "VideoJuego no encontrado con Id: " + id));
        calcularIva(videoJuego);
        return videoJuego;
    }

    //Crear un videojuego nuevo
    public VideoJuego crearVideoJuego(VideoJuego videoJuego) {
        validar(videoJuego);

        //Verificar que la desarrolladora exista
        if(videoJuego.getDesarrolladora() != null && videoJuego.getDesarrolladora().getId() != null) {
            Desarrolladora desarrolladora = desarrolladoraService.buscarPorId(videoJuego.getDesarrolladora().getId());
            videoJuego.setDesarrolladora(desarrolladora);
        }

        VideoJuego guardado = videoJuegoRepository.save(videoJuego);
        calcularIva(guardado);
        return guardado;
    }

    //Actualizar videojuego completo
    public VideoJuego actualizarVideoJuego(Long id, VideoJuego datos) {
        //Verificamos que el videojuego exista
        VideoJuego existente = videoJuegoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Videojuego no encontrado con Id: " + id));
        validar(datos);

        //Verificamos que la desarroladora exista
        if (datos.getDesarrolladora() != null && datos.getDesarrolladora().getId() != null) {
            Desarrolladora desarrolladora = desarrolladoraService.buscarPorId(
                    datos.getDesarrolladora().getId());
            existente.setDesarrolladora(desarrolladora);
        }

        //Actualizar los campos del videojuego
        existente.setTitulo(datos.getTitulo());
        existente.setPrecio(datos.getPrecio());
        existente.setCodigoRegistro(datos.getCodigoRegistro());
        existente.setGenero(datos.getGenero());

        VideoJuego actualizado = videoJuegoRepository.save(existente);
        calcularIva(actualizado);
        return actualizado;
    }

    //Eliminar un videojuego
    public void eliminarVideojuego(Long id) {
        if(!videoJuegoRepository.existsById(id)) {
            throw new ResourceNotFoundException("VideoJuego no encontrado con Id: " + id);
        }
        videoJuegoRepository.deleteById(id);
    }

    //Buscar un videojuego por su título
    public List<VideoJuego> buscarPorTitulo(String titulo) {
        List<VideoJuego> videojuegos = videoJuegoRepository.findByTituloContainingIgnoreCase(titulo);
        calcularIvaLista(videojuegos);
        return videojuegos;
    }

    //Buscar un videojuego por rango de precio
    public List<VideoJuego> buscarPorRangoDePrecio(Double min, Double max) {
        List<VideoJuego> videojuegos = videoJuegoRepository.buscarPorRangoDePrecio(min, max);
        calcularIvaLista(videojuegos);
        return videojuegos;
    }

    //Aplicar descuento a un videojuego
    public VideoJuego aplciarDescuento(Long id, Double porcentaje) {
        if(porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 e 100");
        }

        VideoJuego videojuego = videoJuegoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Videojuego no encontrado con Id: " + id));

        double nuevoPrecio = videojuego.getPrecio() * (1 - (porcentaje/100));
        videojuego.setPrecio(nuevoPrecio);

        VideoJuego actualizado = videoJuegoRepository.save(videojuego);
        calcularIva(actualizado);
        return actualizado;
    }
}
