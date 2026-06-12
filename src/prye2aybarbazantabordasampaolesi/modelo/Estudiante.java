package prye2aybarbazantabordasampaolesi.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Estudiante {

    private String nombre;
    private String legajo;
    private String carrera;
    private int anioIngreso;
    private final ArrayList<InscripcionMateria> inscripciones;

    public Estudiante(String nombre, String legajo, String carrera, int anioIngreso) {
        setNombre(nombre);
        setLegajo(legajo);
        setCarrera(carrera);
        setAnioIngreso(anioIngreso);
        this.inscripciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public String getLegajo() {
        return legajo;
    }

    public final void setLegajo(String legajo) {
        if (legajo == null || legajo.trim().isEmpty()) {
            throw new IllegalArgumentException("El legajo no puede estar vacio.");
        }
        this.legajo = legajo.trim();
    }

    public String getCarrera() {
        return carrera;
    }

    public final void setCarrera(String carrera) {
        if (carrera == null || carrera.trim().isEmpty()) {
            throw new IllegalArgumentException("La carrera no puede estar vacia.");
        }
        this.carrera = carrera.trim();
    }

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public final void setAnioIngreso(int anioIngreso) {
        if (anioIngreso < 1900) {
            throw new IllegalArgumentException("El anio de ingreso no es valido.");
        }
        this.anioIngreso = anioIngreso;
    }

    public ArrayList<InscripcionMateria> getInscripciones() {
        return new ArrayList<>(inscripciones);
    }
    
    
    public ArrayList<InscripcionMateria> getMaterias() 
    {
        return getInscripciones();
    }
    
    
    public void inscribirse(Materia materia) {
        if (materia == null) {
            throw new IllegalArgumentException("La materia no puede ser nula.");
        }
        if (buscarInscripcion(materia.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe una inscripcion con ese codigo.");
        }
        inscripciones.add(new InscripcionMateria(materia));
    }

    public void agregarInscripcion(InscripcionMateria ins) {
        this.inscripciones.add(ins);
    }

    public boolean darDeBaja(String codigoMateria) {
        InscripcionMateria inscripcion = buscarInscripcion(codigoMateria);
        if (inscripcion == null) {
            return false;
        }
        return inscripciones.remove(inscripcion);
    }

    public InscripcionMateria buscarInscripcion(String codigoMateria) {
        if (codigoMateria == null || codigoMateria.trim().isEmpty()) {
            return null;
        }

        String codigoBuscado = codigoMateria.trim().toUpperCase();
        for (InscripcionMateria inscripcion : inscripciones) {
            if (inscripcion.getMateria().getCodigo().equals(codigoBuscado)) {
                return inscripcion;
            }
        }

        return null;
    }
    public InscripcionMateria getInscripcion(String codigoMateria) 
    {
        return buscarInscripcion(codigoMateria);
    }
    public double getPromedioGeneral() {
        if (inscripciones.isEmpty()) {
            return 0;
        }

        double suma = 0;
        for (InscripcionMateria inscripcion : inscripciones) {
            suma += inscripcion.getPromedio();
        }

        return suma / inscripciones.size();
    }

    public ArrayList<InscripcionMateria> getMateriasCriticas() {
        ArrayList<InscripcionMateria> materiasCriticas = new ArrayList<>();

        for (InscripcionMateria inscripcion : inscripciones) {
            double asistencia = inscripcion.getPorcentajeAsistencia();
            if (asistencia >= 75 && asistencia <= 85) {
                materiasCriticas.add(inscripcion);
            }
        }

        Collections.sort(materiasCriticas, Comparator.comparingDouble(InscripcionMateria::getPorcentajeAsistencia));
        return materiasCriticas;
    }

    public String toTexto() {
        return nombre + ";" + legajo + ";" + carrera + ";" + anioIngreso;
    }

    public static Estudiante fromTexto(String linea) {
        if (linea == null || linea.trim().isEmpty()) {
            throw new IllegalArgumentException("La linea de estudiante no puede estar vacia.");
        }

        String[] partes = linea.split(";");
        if (partes.length != 4) {
            throw new IllegalArgumentException("Formato de estudiante invalido.");
        }

        return new Estudiante(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]));
    }
}
