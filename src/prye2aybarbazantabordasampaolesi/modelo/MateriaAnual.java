package prye2aybarbazantabordasampaolesi.modelo;

public class MateriaAnual extends Materia {
    public MateriaAnual(String nombre, String codigo, int anio) { super(nombre, codigo, 1, anio); }
    @Override public double getPorcentajeRegularidadMinimo(){ return 70.0; }
    @Override public int getCantidadCuatrimestres(){ return 2; }
    @Override public boolean correspondeACuatrimestre(int c){ return c==1 || c==2; }
    @Override public String getTipoMateria(){ return "Anual"; }
    @Override public void mostrarResumen(){ System.out.printf("%s (%s) | Tipo: %s | Cubre cuatrimestres: 1 y 2 | Anio: %d%n", getNombre(), getCodigo(), getTipoMateria(), getAnio()); }
}

