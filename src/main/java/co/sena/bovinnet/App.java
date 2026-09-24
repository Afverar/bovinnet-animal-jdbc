package co.sena.bovinnet;

import co.sena.bovinnet.animal.Animal;
import co.sena.bovinnet.animal.AnimalDAO;
import co.sena.bovinnet.animal.AnimalDAOImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del modulo de codificacion de BovinNet.
 *
 * Presenta un menu de consola que ejecuta las cuatro operaciones
 * exigidas por la evidencia GA7-220501096-AA2-EV01 (insercion,
 * consulta, actualizacion y eliminacion) sobre la entidad Animal,
 * usando JDBC como mecanismo de conexion a la base de datos.
 *
 * Artefactos de referencia usados para esta codificacion:
 * - Diagrama de clases del proyecto (clase Animal).
 * - Modelo relacional y script de base de datos (GA6-220501096).
 * - Historia de usuario: registro y control de animales del hato.
 */
public class App {

    private static final AnimalDAO animalDAO = new AnimalDAOImpl();
    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> insertarAnimal();
                case 2 -> consultarAnimales();
                case 3 -> actualizarAnimal();
                case 4 -> eliminarAnimal();
                case 0 -> System.out.println("Cerrando el modulo. Hasta luego.");
                default -> System.out.println("Opcion no valida, intente de nuevo.");
            }
        } while (opcion != 0);

        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== BovinNet - Modulo de gestion de animales =====");
        System.out.println("1. Insertar un animal");
        System.out.println("2. Consultar animales");
        System.out.println("3. Actualizar un animal");
        System.out.println("4. Eliminar un animal");
        System.out.println("0. Salir");
    }

    private static void insertarAnimal() {
        System.out.println("\n-- Insertar animal --");
        String arete = leerTexto("Arete: ");
        String nombre = leerTexto("Nombre: ");
        String sexo = leerTexto("Sexo (Hembra/Macho): ");
        LocalDate fechaNacimiento = LocalDate.parse(leerTexto("Fecha de nacimiento (AAAA-MM-DD): "));
        int idLote = leerEntero("Id del lote: ");

        Animal nuevoAnimal = new Animal(arete, nombre, sexo, fechaNacimiento, idLote);
        boolean exito = animalDAO.insertarAnimal(nuevoAnimal);

        System.out.println(exito ? "Animal insertado correctamente." : "No se pudo insertar el animal.");
    }

    private static void consultarAnimales() {
        System.out.println("\n-- Consultar animales --");
        int idAnimal = leerEntero("Ingrese el id del animal (0 para listar todos): ");

        if (idAnimal == 0) {
            List<Animal> animales = animalDAO.consultarTodosLosAnimales();
            if (animales.isEmpty()) {
                System.out.println("No hay animales registrados.");
            } else {
                animales.forEach(System.out::println);
            }
        } else {
            Animal animal = animalDAO.consultarAnimalPorId(idAnimal);
            System.out.println(animal != null ? animal : "No existe un animal con ese id.");
        }
    }

    private static void actualizarAnimal() {
        System.out.println("\n-- Actualizar animal --");
        int idAnimal = leerEntero("Id del animal a actualizar: ");
        Animal animalExistente = animalDAO.consultarAnimalPorId(idAnimal);

        if (animalExistente == null) {
            System.out.println("No existe un animal con ese id.");
            return;
        }

        String nombre = leerTexto("Nuevo nombre: ");
        String sexo = leerTexto("Nuevo sexo (Hembra/Macho): ");
        int idLote = leerEntero("Nuevo id de lote: ");

        animalExistente.setNombre(nombre);
        animalExistente.setSexo(sexo);
        animalExistente.setIdLote(idLote);

        boolean exito = animalDAO.actualizarAnimal(animalExistente);
        System.out.println(exito ? "Animal actualizado correctamente." : "No se pudo actualizar el animal.");
    }

    private static void eliminarAnimal() {
        System.out.println("\n-- Eliminar animal --");
        int idAnimal = leerEntero("Id del animal a eliminar: ");

        boolean exito = animalDAO.eliminarAnimal(idAnimal);
        System.out.println(exito ? "Animal eliminado correctamente." : "No se pudo eliminar el animal.");
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        int valor = Integer.parseInt(entrada.nextLine().trim());
        return valor;
    }
}
