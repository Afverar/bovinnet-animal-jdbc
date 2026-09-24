package co.sena.bovinnet.animal;

import co.sena.bovinnet.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de AnimalDAO usando JDBC puro sobre la base de datos
 * "bovinnet" (motor MySQL), definida en las evidencias del componente
 * de bases de datos (GA6-220501096-AA2-EV02 y AA2-EV03).
 *
 * Cada metodo corresponde a una de las cuatro operaciones exigidas
 * por la evidencia: insertarAnimal (insercion), consultarAnimalPorId
 * y consultarTodosLosAnimales (consulta), actualizarAnimal
 * (actualizacion) y eliminarAnimal (eliminacion).
 */
public class AnimalDAOImpl implements AnimalDAO {

    private static final String SQL_INSERTAR =
            "INSERT INTO animal (arete, nombre, sexo, fecha_nacimiento, id_lote) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_CONSULTAR_POR_ID =
            "SELECT id_animal, arete, nombre, sexo, fecha_nacimiento, id_lote " +
            "FROM animal WHERE id_animal = ?";

    private static final String SQL_CONSULTAR_TODOS =
            "SELECT id_animal, arete, nombre, sexo, fecha_nacimiento, id_lote " +
            "FROM animal ORDER BY id_animal";

    private static final String SQL_ACTUALIZAR =
            "UPDATE animal SET arete = ?, nombre = ?, sexo = ?, " +
            "fecha_nacimiento = ?, id_lote = ? WHERE id_animal = ?";

    private static final String SQL_ELIMINAR =
            "DELETE FROM animal WHERE id_animal = ?";

    @Override
    public boolean insertarAnimal(Animal animal) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_INSERTAR)) {

            sentencia.setString(1, animal.getArete());
            sentencia.setString(2, animal.getNombre());
            sentencia.setString(3, animal.getSexo());
            sentencia.setDate(4, java.sql.Date.valueOf(animal.getFechaNacimiento()));
            sentencia.setInt(5, animal.getIdLote());

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException error) {
            System.err.println("Error al insertar el animal: " + error.getMessage());
            return false;
        }
    }

    @Override
    public Animal consultarAnimalPorId(int idAnimal) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_CONSULTAR_POR_ID)) {

            sentencia.setInt(1, idAnimal);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return mapearAnimal(resultado);
                }
            }
            return null;

        } catch (SQLException error) {
            System.err.println("Error al consultar el animal: " + error.getMessage());
            return null;
        }
    }

    @Override
    public List<Animal> consultarTodosLosAnimales() {
        List<Animal> animales = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_CONSULTAR_TODOS);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                animales.add(mapearAnimal(resultado));
            }

        } catch (SQLException error) {
            System.err.println("Error al consultar los animales: " + error.getMessage());
        }
        return animales;
    }

    @Override
    public boolean actualizarAnimal(Animal animal) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_ACTUALIZAR)) {

            sentencia.setString(1, animal.getArete());
            sentencia.setString(2, animal.getNombre());
            sentencia.setString(3, animal.getSexo());
            sentencia.setDate(4, java.sql.Date.valueOf(animal.getFechaNacimiento()));
            sentencia.setInt(5, animal.getIdLote());
            sentencia.setInt(6, animal.getIdAnimal());

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException error) {
            System.err.println("Error al actualizar el animal: " + error.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarAnimal(int idAnimal) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_ELIMINAR)) {

            sentencia.setInt(1, idAnimal);

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException error) {
            System.err.println("Error al eliminar el animal: " + error.getMessage());
            return false;
        }
    }

    /**
     * Convierte la fila actual de un ResultSet en un objeto Animal.
     * Metodo privado de apoyo, reutilizado por las consultas.
     */
    private Animal mapearAnimal(ResultSet resultado) throws SQLException {
        Animal animal = new Animal();
        animal.setIdAnimal(resultado.getInt("id_animal"));
        animal.setArete(resultado.getString("arete"));
        animal.setNombre(resultado.getString("nombre"));
        animal.setSexo(resultado.getString("sexo"));

        java.sql.Date fecha = resultado.getDate("fecha_nacimiento");
        if (fecha != null) {
            animal.setFechaNacimiento(fecha.toLocalDate());
        }
        animal.setIdLote(resultado.getInt("id_lote"));
        return animal;
    }
}
