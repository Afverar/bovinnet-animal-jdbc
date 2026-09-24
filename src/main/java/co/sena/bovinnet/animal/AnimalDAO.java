package co.sena.bovinnet.animal;

import java.util.List;

/**
 * Contrato de acceso a datos para la entidad Animal.
 *
 * Define las cuatro operaciones basicas exigidas por la evidencia
 * (insercion, consulta, actualizacion y eliminacion), independientes
 * de la tecnologia de persistencia usada por la implementacion.
 */
public interface AnimalDAO {

    boolean insertarAnimal(Animal animal);

    Animal consultarAnimalPorId(int idAnimal);

    List<Animal> consultarTodosLosAnimales();

    boolean actualizarAnimal(Animal animal);

    boolean eliminarAnimal(int idAnimal);
}
