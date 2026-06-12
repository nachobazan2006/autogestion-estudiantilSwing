package prye2aybarbazantabordasampaolesi.controlador;

import prye2aybarbazantabordasampaolesi.modelo.Estudiante;
import prye2aybarbazantabordasampaolesi.modelo.InscripcionMateria;
import prye2aybarbazantabordasampaolesi.modelo.Materia;
import prye2aybarbazantabordasampaolesi.modelo.MateriaAnual;
import prye2aybarbazantabordasampaolesi.modelo.MateriaCuatrimestral;
import java.util.List;

public class ControladorEstudiante {

    private Estudiante estudiante;

    public ControladorEstudiante() {
        // En un escenario real, el constructor llama a cargarDatos()
        // Por ahora lo inicializamos si hace falta, o esperamos a cargarDatos()
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    // 1. inscribir materia
    public void inscribirMateria(String nombre, String codigo, int cuatrimestre, int anio) {
        if (estudiante == null) {
            throw new IllegalStateException("No hay un estudiante cargado en el sistema.");
        }
        Materia materia = cuatrimestre == 1 || cuatrimestre == 2
                ? new MateriaCuatrimestral(nombre, codigo, cuatrimestre, anio)
                : new MateriaAnual(nombre, codigo, anio);
        estudiante.inscribirse(materia);
        guardarDatos();
    }

    // 2. registrar asistencia
    public void registrarAsistencia(String codigo, boolean presente) {
        if (estudiante == null) return;
        InscripcionMateria inscripcion = estudiante.getInscripcion(codigo);
        if (inscripcion != null) {
            inscripcion.registrarAsistencia(presente);
            guardarDatos();
        } else {
            throw new IllegalArgumentException("No existe una materia inscripta con el código: " + codigo);
        }
    }

    // 3. registrar nota
    public void registrarNota(String codigo, double nota) {
        if (estudiante == null) return;
        InscripcionMateria inscripcion = estudiante.getInscripcion(codigo);
        if (inscripcion != null) {
            inscripcion.agregarNota(nota);
            guardarDatos();
        } else {
            throw new IllegalArgumentException("No existe una materia inscripta con el código: " + codigo);
        }
    }

    // 4. dar de baja
    public boolean darDeBaja(String codigo) {
        if (estudiante == null) return false;
        boolean bajaExitosamente = estudiante.darDeBaja(codigo);
        if (bajaExitosamente) {
            guardarDatos();
        }
        return bajaExitosamente;
    }

    // 5. guardar
    public void guardarDatos() {
        if (estudiante == null) return;
        new prye2aybarbazantabordasampaolesi.dao.EstudianteDAO().guardar(List.of(estudiante));
        new prye2aybarbazantabordasampaolesi.dao.InscripcionDAO().guardar(estudiante.getInscripciones());
    }

    // 6. cargar
    public void cargarDatos() {
        this.estudiante = new prye2aybarbazantabordasampaolesi.dao.EstudianteDAO().listar().stream().findFirst()
            .orElse(new Estudiante("Alumno Generico", "00000", "Sistemas", 2026));
        new prye2aybarbazantabordasampaolesi.dao.InscripcionDAO().listar().forEach(this.estudiante::agregarInscripcion);
    }

    public List<InscripcionMateria> getInscripciones() {
        if (estudiante == null) {
            return new java.util.ArrayList<>();
        }
        return estudiante.getMaterias();
    }
}
