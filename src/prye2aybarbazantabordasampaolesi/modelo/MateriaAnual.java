package prye2aybarbazantabordasampaolesi.modelo;

public class MateriaAnual extends Materia {

    public MateriaAnual(String nombre, String codigo, int anio) {
        super(nombre, codigo, 1, anio);
    }

    public int getCantidadCuatrimestres() {
        return 2;
    }

    public boolean correspondeACuatrimestre(int cuatrimestre) {
        return cuatrimestre == 1 || cuatrimestre == 2;
    }

    public String getTipoMateria() {
        return "Anual";
    }

    @Override
    public String toTexto() {
        return getTipoMateria() + ";" + getNombre() + ";" + getCodigo() + ";" + getAnio();
    }

    public static MateriaAnual fromTexto(String linea) {
        if (linea == null || linea.trim().isEmpty()) {
            throw new IllegalArgumentException("La linea de materia anual no puede estar vacia.");
        }

        String[] partes = linea.split(";");
        if (partes.length != 4 || !"Anual".equalsIgnoreCase(partes[0])) {
            throw new IllegalArgumentException("Formato de materia anual invalido.");
        }

        return new MateriaAnual(partes[1], partes[2], Integer.parseInt(partes[3]));
    }
}
