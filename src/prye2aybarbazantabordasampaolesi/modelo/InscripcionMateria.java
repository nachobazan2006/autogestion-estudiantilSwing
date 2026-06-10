package prye2aybarbazantabordasampaolesi.modelo;

import java.util.ArrayList;
import java.util.List;

public class InscripcionMateria implements Evaluable, Rankeable {
    private static final int MAX_NOTAS = 5;
    private Materia materia;
    private int totalClases;
    private int clasesAsistidas;
    private ArrayList<Double> notas;
    public InscripcionMateria(Materia materia){ setMateria(materia); this.notas = new ArrayList<Double>(); }
    public Materia getMateria(){ return materia; }
    public final void setMateria(Materia materia){ if(materia==null) throw new IllegalArgumentException("La materia no puede ser nula."); this.materia=materia; }
    public int getTotalClases(){ return totalClases; }
    public int getClasesAsistidas(){ return clasesAsistidas; }
    public List<Double> getNotas(){ return new ArrayList<Double>(notas); }
    public void restaurarEstado(int totalClases,int clasesAsistidas,List<Double> notas){ if(totalClases<0) throw new IllegalArgumentException("El total de clases no puede ser negativo."); if(clasesAsistidas<0||clasesAsistidas>totalClases) throw new IllegalArgumentException("Las clases asistidas no son validas."); this.totalClases=totalClases; this.clasesAsistidas=clasesAsistidas; this.notas=new ArrayList<Double>(); if(notas!=null) this.notas.addAll(notas); }
    public void registrarAsistencia(boolean presente){ totalClases++; if(presente) clasesAsistidas++; }
    public void agregarNota(double nota){ if(nota<0||nota>10) throw new IllegalArgumentException("La nota debe estar entre 0 y 10."); if(notas.size()>=MAX_NOTAS) throw new IllegalStateException("No se permiten mas de 5 notas por materia."); notas.add(nota); }
    public double getPorcentajeAsistencia(){ return totalClases==0?0:(clasesAsistidas*100.0)/totalClases; }
    @Override public String getCondicion(){ return getPorcentajeAsistencia()>=materia.getPorcentajeRegularidadMinimo() ? "Regular" : "Libre"; }
    @Override public double getPromedio(){ if(notas.isEmpty()) return 0; double s=0; for(Double n:notas) s+=n; return s/notas.size(); }
    @Override public boolean estaAprobada(){ return getPromedio()>=6 && "Regular".equals(getCondicion()); }
    @Override public double getPuntajeRanking(){ return (getPromedio()*0.6)+(getPorcentajeAsistencia()*0.4); }
}

