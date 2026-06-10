package prye2aybarbazantabordasampaolesi.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import prye2aybarbazantabordasampaolesi.modelo.Inscripcion;

public class InscripcionDAO {

    private static final String RUTA_ARCHIVO = "datos/inscripciones.txt";

    public List<Inscripcion> listar() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        File archivo = obtenerArchivo();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    inscripciones.add(convertirDesdeTexto(linea));
                }
            }
        } catch (IOException ex) {
            System.out.println("No se pudieron leer las inscripciones: " + ex.getMessage());
        }

        return inscripciones;
    }

    public void guardar(List<Inscripcion> inscripciones) {
        File archivo = obtenerArchivo();

        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
            for (Inscripcion inscripcion : inscripciones) {
                escritor.println(convertirATexto(inscripcion));
            }
        } catch (IOException ex) {
            System.out.println("No se pudieron guardar las inscripciones: " + ex.getMessage());
        }
    }

    public void agregar(Inscripcion inscripcion) {
        List<Inscripcion> inscripciones = listar();
        inscripciones.add(inscripcion);
        guardar(inscripciones);
    }

    private File obtenerArchivo() {
        File archivo = new File(RUTA_ARCHIVO);
        File carpeta = archivo.getParentFile();

        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                System.out.println("No se pudo crear el archivo de inscripciones: " + ex.getMessage());
            }
        }

        return archivo;
    }

    private String convertirATexto(Inscripcion inscripcion) {
        return inscripcion.getLegajoEstudiante() + ";" + inscripcion.getMateria() + ";" + inscripcion.getFecha();
    }

    private Inscripcion convertirDesdeTexto(String linea) {
        String[] partes = linea.split(";");
        int legajoEstudiante = Integer.parseInt(partes[0]);
        String materia = partes[1];
        String fecha = partes[2];

        return new Inscripcion(legajoEstudiante, materia, fecha);
    }
}
