package prye2aybarbazantabordasampaolesi.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Estudiante extends PersonaAcademica {
    private String carrera;
    private int anioIngreso;
    private ArrayList<InscripcionMateria> materias;
    public Estudiante(String nombre, String legajo, String carrera, int anioIngreso){ super(nombre, legajo); setCarrera(carrera); setAnioIngreso(anioIngreso); this.materias=new ArrayList<InscripcionMateria>(); }
    public String getCarrera(){ return carrera; }
    public final void setCarrera(String carrera){ if(carrera==null||carrera.trim().isEmpty()) throw new IllegalArgumentException("La carrera no puede estar vacia."); this.carrera=carrera.trim(); }
    public int getAnioIngreso(){ return anioIngreso; }
    public final void setAnioIngreso(int anioIngreso){ if(anioIngreso<1900) throw new IllegalArgumentException("El anio de ingreso no es valido."); this.anioIngreso=anioIngreso; }
    public ArrayList<InscripcionMateria> getMaterias(){ return new ArrayList<InscripcionMateria>(materias); }
    public void inscribirse(Materia materia){ if(materia==null) throw new IllegalArgumentException("La materia no puede ser nula."); if(getInscripcion(materia.getCodigo())!=null) throw new IllegalArgumentException("Ya existe una inscripcion con ese codigo."); materias.add(new InscripcionMateria(materia)); }
    public boolean darDeBaja(String codigoMateria){ InscripcionMateria inscripcion=getInscripcion(codigoMateria); if(inscripcion==null) return false; boolean eliminada=materias.remove(inscripcion); if(eliminada) Materia.liberarCodigoRegistrado(inscripcion.getMateria().getCodigo()); return eliminada; }
    public InscripcionMateria getInscripcion(String codigoMateria){ if(codigoMateria==null||codigoMateria.trim().isEmpty()) return null; return buscarInscripcionRecursiva(codigoMateria.trim().toUpperCase(),0); }
    public double getPromedioGeneral(){ if(materias.isEmpty()) return 0; return calcularPromedioRecursivo(0,0)/materias.size(); }
    public ArrayList<InscripcionMateria> getMateriasCriticas(){ ArrayList<InscripcionMateria> criticas=new ArrayList<InscripcionMateria>(); for(InscripcionMateria i:materias){ double a=i.getPorcentajeAsistencia(); if(a>=75 && a<=85) criticas.add(i); } Collections.sort(criticas, Comparator.comparingDouble(InscripcionMateria::getPorcentajeAsistencia)); return criticas; }
    public void mostrarMaterias(){ for(InscripcionMateria i:materias){ System.out.printf("%s (%s) | Tipo: %s | Condicion: %s | Promedio: %.2f%n", i.getMateria().getNombre(), i.getMateria().getCodigo(), i.getMateria().getTipoMateria(), i.getCondicion(), i.getPromedio()); } }
    @Override public void mostrarResumen(){ System.out.println("=== PERFIL DEL ESTUDIANTE ==="); System.out.println("Nombre: " + getNombre()); System.out.println("Legajo: " + getLegajo()); System.out.println("Carrera: " + carrera); System.out.println("Anio de ingreso: " + anioIngreso); System.out.println("Materias inscriptas: " + materias.size()); }
    private double calcularPromedioRecursivo(int indice,double acumulado){ return indice==materias.size()?acumulado:calcularPromedioRecursivo(indice+1, acumulado + materias.get(indice).getPromedio()); }
    private InscripcionMateria buscarInscripcionRecursiva(String codigoMateria,int indice){ if(indice==materias.size()) return null; InscripcionMateria actual=materias.get(indice); if(actual.getMateria().getCodigo().equalsIgnoreCase(codigoMateria)) return actual; return buscarInscripcionRecursiva(codigoMateria, indice+1); }
}

