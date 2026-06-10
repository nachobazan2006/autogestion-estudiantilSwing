package prye2aybarbazantabordasampaolesi.modelo;

public class MateriaCuatrimestral extends Materia {
    public MateriaCuatrimestral(String nombre, String codigo, int cuatrimestre, int anio){ super(nombre, codigo, cuatrimestre, anio); }
    @Override public String getTipoMateria(){ return "Cuatrimestral"; }
}

