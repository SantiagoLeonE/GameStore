package co.edu.uniquindio.gamestore.exception;

//Se utiliza para el caso de que no se encuentre nada en la base de datos
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
