package co.sena.bovinnet.animal;

import java.time.LocalDate;

/**
 * Clase modelo (POJO) que representa un animal del hato.
 *
 * Corresponde a la entidad ANIMAL definida en el modelo relacional
 * del proyecto BovinNet (evidencias GA6-220501096-AA1-EV01 y
 * GA6-220501096-AA2-EV02), y al elemento "Animal" del diagrama de
 * clases construido en la fase de diseno del proyecto.
 *
 * Historia de usuario relacionada:
 * "Como operario de campo quiero registrar, consultar, actualizar
 * y eliminar los animales del hato para llevar el control del
 * inventario ganadero de la finca."
 */
public class Animal {

    private int idAnimal;
    private String arete;
    private String nombre;
    private String sexo;
    private LocalDate fechaNacimiento;
    private int idLote;

    public Animal() {
    }

    public Animal(int idAnimal, String arete, String nombre, String sexo,
                  LocalDate fechaNacimiento, int idLote) {
        this.idAnimal = idAnimal;
        this.arete = arete;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.idLote = idLote;
    }

    public Animal(String arete, String nombre, String sexo,
                  LocalDate fechaNacimiento, int idLote) {
        this.arete = arete;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.idLote = idLote;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getArete() {
        return arete;
    }

    public void setArete(String arete) {
        this.arete = arete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", arete='" + arete + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", idLote=" + idLote +
                '}';
    }
}
