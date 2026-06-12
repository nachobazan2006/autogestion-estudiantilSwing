package prye2aybarbazantabordasampaolesi.modelo;

public class Materia implements Consultable {

    private String nombre;
    private String codigo;
    private int cuatrimestre;
    private int anio;

    public Materia(String nombre, String codigo, int cuatrimestre, int anio) {
        setNombre(nombre);
        setCodigo(codigo);
        setCuatrimestre(cuatrimestre);
        setAnio(anio);
    }

    public String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public String getCodigo() {
        return codigo;
    }

    public final void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio.");
        }

        String codigoNormalizado = codigo.trim().toUpperCase();
        if (codigoNormalizado.length() < 3 || codigoNormalizado.length() > 10) {
            throw new IllegalArgumentException("El codigo debe tener entre 3 y 10 caracteres.");
        }

        this.codigo = codigoNormalizado;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public final void setCuatrimestre(int cuatrimestre) {
        if (cuatrimestre != 1 && cuatrimestre != 2) {
            throw new IllegalArgumentException("El cuatrimestre debe ser 1 o 2.");
        }
        this.cuatrimestre = cuatrimestre;
    }

    public int getAnio() {
        return anio;
    }

    public final void setAnio(int anio) {
        if (anio <= 0) {
            throw new IllegalArgumentException("El anio debe ser mayor a cero.");
        }
        this.anio = anio;
    }

    public int getCantidadCuatrimestres() {
        return 1;
    }

    public boolean correspondeACuatrimestre(int cuatrimestre) {
        return this.cuatrimestre == cuatrimestre;
    }

    public String getTipoMateria() {
        return "Materia";
    }

    @Override
    public String getResumen() {
        return getNombre() + " (" + getCodigo() + ") - " + getTipoMateria();
    }

    public String toTexto() {
        return getTipoMateria() + ";" + nombre + ";" + codigo + ";" + cuatrimestre + ";" + anio;
    }

    public static Materia fromTexto(String linea) {
        if (linea == null || linea.trim().isEmpty()) {
            throw new IllegalArgumentException("La linea de materia no puede estar vacia.");
        }

        String[] partes = linea.split(";");
        if (partes.length < 4) {
            throw new IllegalArgumentException("Formato de materia invalido.");
        }

        String tipo = partes[0];
        if ("Anual".equalsIgnoreCase(tipo)) {
            return MateriaAnual.fromTexto(linea);
        }
        if ("Cuatrimestral".equalsIgnoreCase(tipo)) {
            return MateriaCuatrimestral.fromTexto(linea);
        }

        if (partes.length != 5) {
            throw new IllegalArgumentException("Formato de materia invalido.");
        }

        String nombre = partes[1];
        String codigo = partes[2];
        int cuatrimestre = Integer.parseInt(partes[3]);
        int anio = Integer.parseInt(partes[4]);

        return new Materia(nombre, codigo, cuatrimestre, anio);
    }
}
