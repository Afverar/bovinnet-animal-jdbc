# BovinNet - Modulo de gestion de animales (JDBC)

Evidencia: **GA7-220501096-AA2-EV01** — Codificacion de modulos del
software segun requerimientos del proyecto.

## Descripcion

Este modulo implementa las cuatro operaciones basicas (insercion,
consulta, actualizacion y eliminacion) sobre la entidad **Animal**
del proyecto BovinNet, conectandose a la base de datos MySQL
`bovinnet` por medio de JDBC, conforme a lo visto en el componente
formativo "Construccion de aplicaciones con JAVA".

## Artefactos de referencia usados

- **Diagrama de clases** del proyecto: la clase `Animal` replica los
  atributos definidos alli (arete, nombre, sexo, fecha de nacimiento,
  lote).
- **Modelo relacional y script de base de datos** (evidencias
  GA6-220501096-AA1-EV01 y AA2-EV02/EV03): la tabla `animal` y sus
  columnas son las mismas usadas en las sentencias SQL de este
  modulo.
- **Historia de usuario**: "Como operario de campo quiero registrar,
  consultar, actualizar y eliminar los animales del hato para llevar
  el control del inventario ganadero de la finca."
- **Estandar de codificacion del proyecto** (evidencia
  GA7-220501096-AA1-EV02): nomenclatura de paquetes, clases, metodos
  y variables aplicada de forma consistente (ver siguiente seccion).

## Estandares de codificacion aplicados

| Elemento   | Convencion            | Ejemplo en este proyecto        |
|------------|------------------------|----------------------------------|
| Paquetes   | minusculas, dominio invertido | `co.sena.bovinnet.animal` |
| Clases     | PascalCase             | `Animal`, `AnimalDAOImpl`       |
| Metodos    | camelCase, verbo inicial | `insertarAnimal()`, `consultarAnimalPorId()` |
| Variables  | camelCase, nombre descriptivo | `idAnimal`, `fechaNacimiento` |
| Constantes | UPPER_SNAKE_CASE       | `SQL_INSERTAR`, `SQL_ELIMINAR`  |

## Estructura del proyecto

```
proyecto/
├── pom.xml
├── README.md
└── src/main/java/co/sena/bovinnet/
    ├── App.java                  (menu principal, punto de entrada)
    ├── animal/
    │   ├── Animal.java           (clase modelo)
    │   ├── AnimalDAO.java        (contrato de acceso a datos)
    │   └── AnimalDAOImpl.java    (implementacion JDBC del CRUD)
    └── util/
        └── ConexionBD.java       (utilidad de conexion JDBC)
```

## Requisitos para ejecutar

- JDK 17 o superior.
- Maven 3.8 o superior (o un IDE como IntelliJ IDEA / Eclipse con
  soporte para Maven).
- Servidor MySQL 8 con la base de datos `bovinnet` creada a partir
  del script `bovinnet.sql` de la evidencia GA6-220501096-AA2-EV02.
- Ajustar usuario y contrasena de conexion en
  `ConexionBD.java` segun la configuracion local de MySQL.

## Compilacion y ejecucion

Con Maven (recomendado, descarga automaticamente el conector JDBC):

```
mvn clean package
java -cp target/bovinnet-animal-jdbc.jar co.sena.bovinnet.App
```

Sin Maven, compilando manualmente (requiere tener el archivo
`mysql-connector-j-8.3.0.jar` descargado en la carpeta `lib/`):

```
javac -cp lib/mysql-connector-j-8.3.0.jar -d bin $(find src -name "*.java")
java -cp bin:lib/mysql-connector-j-8.3.0.jar co.sena.bovinnet.App
```

## Control de versiones

Este proyecto se versiona con Git. Ver el archivo
`enlace_repositorio.txt` para la direccion del repositorio remoto.
