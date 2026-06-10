package prye2aybarbazantabordasampaolesi.modelo;

public class Inscripcion {

    private int legajoEstudiante;
    private String materia;
    private String fecha;

    public Inscripcion(int legajoEstudiante, String materia, String fecha) {
        this.legajoEstudiante = legajoEstudiante;
        this.materia = materia;
        this.fecha = fecha;
    }

    public int getLegajoEstudiante() {
        return legajoEstudiante;
    }

    public void setLegajoEstudiante(int legajoEstudiante) {
        this.legajoEstudiante = legajoEstudiante;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return legajoEstudiante + " - " + materia + " (" + fecha + ")";
    }
}
