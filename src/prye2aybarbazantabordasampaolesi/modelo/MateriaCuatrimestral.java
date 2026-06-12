package prye2aybarbazantabordasampaolesi.modelo;

public class MateriaCuatrimestral extends Materia {

    public MateriaCuatrimestral(String nombre, String codigo, int cuatrimestre, int anio) {
        super(nombre, codigo, cuatrimestre, anio);
    }

    public int getCantidadCuatrimestres() {
        return 1;
    }

    public boolean correspondeACuatrimestre(int cuatrimestre) {
        return getCuatrimestre() == cuatrimestre;
    }

    public String getTipoMateria() {
        return "Cuatrimestral";
    }

    @Override
    public String toTexto() {
        return getTipoMateria() + ";" + getNombre() + ";" + getCodigo() + ";" + getCuatrimestre() + ";" + getAnio();
    }

    public static MateriaCuatrimestral fromTexto(String linea) {
        if (linea == null || linea.trim().isEmpty()) {
            throw new IllegalArgumentException("La linea de materia cuatrimestral no puede estar vacia.");
        }

        String[] partes = linea.split(";");
        if (partes.length != 5 || !"Cuatrimestral".equalsIgnoreCase(partes[0])) {
            throw new IllegalArgumentException("Formato de materia cuatrimestral invalido.");
        }

        return new MateriaCuatrimestral(
                partes[1],
                partes[2],
                Integer.parseInt(partes[3]),
                Integer.parseInt(partes[4])
        );
    }
}
