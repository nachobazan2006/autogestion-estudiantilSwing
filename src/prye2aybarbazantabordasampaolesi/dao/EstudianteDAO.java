package prye2aybarbazantabordasampaolesi.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import prye2aybarbazantabordasampaolesi.modelo.Estudiante;

public class EstudianteDAO {

    private static final String RUTA_ARCHIVO = "datos/estudiantes.txt";

    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        File archivo = obtenerArchivo();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    estudiantes.add(convertirDesdeTexto(linea));
                }
            }
        } catch (IOException ex) {
            System.out.println("No se pudieron leer los estudiantes: " + ex.getMessage());
        }

        return estudiantes;
    }

    public void guardar(List<Estudiante> estudiantes) {
        File archivo = obtenerArchivo();

        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
            for (Estudiante estudiante : estudiantes) {
                escritor.println(convertirATexto(estudiante));
            }
        } catch (IOException ex) {
            System.out.println("No se pudieron guardar los estudiantes: " + ex.getMessage());
        }
    }

    public void agregar(Estudiante estudiante) {
        List<Estudiante> estudiantes = listar();
        estudiantes.add(estudiante);
        guardar(estudiantes);
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
                System.out.println("No se pudo crear el archivo de estudiantes: " + ex.getMessage());
            }
        }

        return archivo;
    }

    private String convertirATexto(Estudiante estudiante) {
        return estudiante.getLegajo() + ";" + estudiante.getNombre() + ";" + estudiante.getApellido();
    }

    private Estudiante convertirDesdeTexto(String linea) {
        String[] partes = linea.split(";");
        int legajo = Integer.parseInt(partes[0]);
        String nombre = partes[1];
        String apellido = partes[2];

        return new Estudiante(legajo, nombre, apellido);
    }
}
