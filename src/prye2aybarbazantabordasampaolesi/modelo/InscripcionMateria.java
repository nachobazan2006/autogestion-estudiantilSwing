package prye2aybarbazantabordasampaolesi.modelo;

import java.util.ArrayList;

public class InscripcionMateria implements Evaluable, Rankeable {

    private static final int MAX_NOTAS = 5;

    private Materia materia;
    private int totalClases;
    private int clasesAsistidas;
    private final ArrayList<Double> notas;

    public InscripcionMateria(Materia materia) {
        setMateria(materia);
        this.totalClases = 0;
        this.clasesAsistidas = 0;
        this.notas = new ArrayList<>();
    }

    public Materia getMateria() {
        return materia;
    }

    public final void setMateria(Materia materia) {
        if (materia == null) {
            throw new IllegalArgumentException("La materia no puede ser nula.");
        }
        this.materia = materia;
    }

    public int getTotalClases() {
        return totalClases;
    }

    public int getClasesAsistidas() {
        return clasesAsistidas;
    }

    public ArrayList<Double> getNotas() {
        return new ArrayList<>(notas);
    }

    public void registrarAsistencia(boolean presente) {
        totalClases++;
        if (presente) {
            clasesAsistidas++;
        }
    }

    public void agregarNota(double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10.");
        }
        if (notas.size() >= MAX_NOTAS) {
            throw new IllegalStateException("No se permiten mas de 5 notas por materia.");
        }
        notas.add(nota);
    }

    public double getPorcentajeAsistencia() {
        if (totalClases == 0) {
            return 0;
        }
        return clasesAsistidas * 100.0 / totalClases;
    }

    @Override
    public double getPromedio() {
        if (notas.isEmpty()) {
            return 0;
        }

        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }

        return suma / notas.size();
    }

    @Override
    public String getCondicion() {
        if (getPorcentajeAsistencia() >= 75) {
            return "Regular";
        }
        return "Libre";
    }

    @Override
    public boolean estaAprobada() {
        return getPromedio() >= 6 && getPorcentajeAsistencia() >= 75;
    }

    @Override
    public double getPuntajeRanking() {
        return (getPromedio() * 0.6) + (getPorcentajeAsistencia() * 0.4);
    }

    public String toTexto() {
        return materia.toTexto() + ";" + totalClases + ";" + clasesAsistidas + ";" + notasComoTexto();
    }

    public static InscripcionMateria fromTexto(String linea) {
        if (linea == null || linea.trim().isEmpty()) {
            throw new IllegalArgumentException("La linea de inscripcion no puede estar vacia.");
        }

        String[] partes = linea.split(";", -1);
        if (partes.length < 7) {
            throw new IllegalArgumentException("Formato de inscripcion invalido.");
        }

        boolean esAnual = "Anual".equalsIgnoreCase(partes[0]);
        boolean esCuatrimestral = "Cuatrimestral".equalsIgnoreCase(partes[0]);

        if (!esAnual && !esCuatrimestral) {
            throw new IllegalArgumentException("Tipo de materia invalido.");
        }

        int camposMateria = esAnual ? 4 : 5;
        if (partes.length != camposMateria + 3) {
            throw new IllegalArgumentException("Formato de inscripcion invalido.");
        }

        StringBuilder materiaTexto = new StringBuilder();
        for (int i = 0; i < camposMateria; i++) {
            if (i > 0) {
                materiaTexto.append(";");
            }
            materiaTexto.append(partes[i]);
        }

        Materia materia = Materia.fromTexto(materiaTexto.toString());
        InscripcionMateria inscripcion = new InscripcionMateria(materia);
        inscripcion.restaurarEstado(
                Integer.parseInt(partes[camposMateria]),
                Integer.parseInt(partes[camposMateria + 1]),
                partes[camposMateria + 2]
        );

        return inscripcion;
    }

    private void restaurarEstado(int totalClases, int clasesAsistidas, String notasTexto) {
        if (totalClases < 0) {
            throw new IllegalArgumentException("El total de clases no puede ser negativo.");
        }
        if (clasesAsistidas < 0 || clasesAsistidas > totalClases) {
            throw new IllegalArgumentException("Las clases asistidas no son validas.");
        }

        this.totalClases = totalClases;
        this.clasesAsistidas = clasesAsistidas;
        this.notas.clear();

        if (notasTexto == null || notasTexto.trim().isEmpty()) {
            return;
        }

        String[] notasSeparadas = notasTexto.split(",");
        for (String notaTexto : notasSeparadas) {
            agregarNota(Double.parseDouble(notaTexto));
        }
    }

    private String notasComoTexto() {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < notas.size(); i++) {
            if (i > 0) {
                texto.append(",");
            }
            texto.append(notas.get(i));
        }
        return texto.toString();
    }
}
