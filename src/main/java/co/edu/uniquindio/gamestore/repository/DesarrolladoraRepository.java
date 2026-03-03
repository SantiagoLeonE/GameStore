package co.edu.uniquindio.gamestore.repository;

import co.edu.uniquindio.gamestore.entity.Desarrolladora;
import org.springframework.data.jpa.repository.JpaRepository;

//El Jpa nos permite utilizar métodos como save, findById, findAll, entre otros, sin tener que implementar ninguna lógica, el mismo Jpa los intuye
public interface DesarrolladoraRepository extends JpaRepository<Desarrolladora, Long> {

}
