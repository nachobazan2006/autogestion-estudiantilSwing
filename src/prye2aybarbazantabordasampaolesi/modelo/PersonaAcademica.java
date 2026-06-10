package prye2aybarbazantabordasampaolesi.modelo;

public abstract class PersonaAcademica implements Consultable {
    private String nombre;
    private String legajo;

    public PersonaAcademica(String nombre, String legajo) {
        setNombre(nombre);
        setLegajo(legajo);
    }

    public String getNombre() { return nombre; }
    public final void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacio.");
        this.nombre = nombre.trim();
    }
    public String getLegajo() { return legajo; }
    public final void setLegajo(String legajo) {
        if (legajo == null || legajo.trim().isEmpty()) throw new IllegalArgumentException("El legajo no puede estar vacio.");
        this.legajo = legajo.trim();
    }
    @Override public void mostrarResumen() { System.out.println(getNombre() + " - " + getLegajo()); }
}

