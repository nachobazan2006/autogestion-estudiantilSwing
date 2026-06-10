package prye2aybarbazantabordasampaolesi.modelo;

import java.util.HashSet;
import java.util.Set;

public class Materia implements Consultable {
    private static final Set<String> CODIGOS_REGISTRADOS = new HashSet<String>();
    private String nombre;
    private String codigo;
    private int cuatrimestre;
    private int anio;
    public Materia(String nombre, String codigo, int cuatrimestre, int anio) {
        setNombre(nombre); setCodigo(codigo); setCuatrimestre(cuatrimestre); setAnio(anio);
    }
    public String getNombre(){ return nombre; }
    public final void setNombre(String nombre){ if(nombre==null||nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre de la materia no puede estar vacio."); this.nombre=nombre.trim(); }
    public String getCodigo(){ return codigo; }
    public final void setCodigo(String codigo){ String c=normalizarCodigo(codigo); if(this.codigo!=null&&this.codigo.equals(c)) return; if(CODIGOS_REGISTRADOS.contains(c)) throw new IllegalArgumentException("El codigo de materia ya existe."); String previo=this.codigo; this.codigo=c; CODIGOS_REGISTRADOS.add(c); if(previo!=null) CODIGOS_REGISTRADOS.remove(previo); }
    public int getCuatrimestre(){ return cuatrimestre; }
    public final void setCuatrimestre(int cuatrimestre){ if(cuatrimestre!=1&&cuatrimestre!=2) throw new IllegalArgumentException("El cuatrimestre debe ser 1 o 2."); this.cuatrimestre=cuatrimestre; }
    public int getAnio(){ return anio; }
    public final void setAnio(int anio){ if(anio<1) throw new IllegalArgumentException("El anio debe ser mayor a cero."); this.anio=anio; }
    public double getPorcentajeRegularidadMinimo(){ return 75.0; }
    public int getCantidadCuatrimestres(){ return 1; }
    public boolean correspondeACuatrimestre(int c){ return this.cuatrimestre==c; }
    public String getTipoMateria(){ return "Cuatrimestral"; }
    @Override public void mostrarResumen(){ System.out.printf("%s (%s) | Tipo: %s | Cuatrimestre: %d | Anio: %d%n", nombre, codigo, getTipoMateria(), cuatrimestre, anio); }
    public static void liberarCodigoRegistrado(String codigo){ if(codigo!=null) CODIGOS_REGISTRADOS.remove(codigo.trim().toUpperCase()); }
    private static String normalizarCodigo(String codigo){ if(codigo==null) throw new IllegalArgumentException("El codigo no puede ser nulo."); String c=codigo.trim().toUpperCase(); if(c.length()<3||c.length()>10) throw new IllegalArgumentException("El codigo debe tener entre 3 y 10 caracteres."); return c; }
}

