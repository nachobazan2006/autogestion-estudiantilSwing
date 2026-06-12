package prye2aybarbazantabordasampaolesi.vista;

import prye2aybarbazantabordasampaolesi.controlador.ControladorEstudiante;
import prye2aybarbazantabordasampaolesi.modelo.Estudiante;

public class PanelPerfil extends javax.swing.JPanel {

    private ControladorEstudiante controlador;

    public PanelPerfil() {
        initComponents();
    }

    public void Controlador(ControladorEstudiante controlador) {
        this.controlador = controlador;
        refrescar();
    }

    public void refrescar() {
        if (lblPerfilEstudiante == null) {
            return;
        }
        if (controlador == null || controlador.getEstudiante() == null) {
            lblPerfilEstudiante.setText("Estudiante: - | Carrera: - | Año de ingreso: -");
            return;
        }

        Estudiante estudiante = controlador.getEstudiante();
        lblPerfilEstudiante.setText("Estudiante: " + estudiante.getNombre()
                + " | Carrera: " + estudiante.getCarrera()
                + " | Año de ingreso: " + estudiante.getAnioIngreso());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPerfilEstudiante = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Perfil del estudiante"));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 6));

        lblPerfilEstudiante.setText("Estudiante: - | Carrera: - | Año de ingreso: -");
        add(lblPerfilEstudiante);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblPerfilEstudiante;
    // End of variables declaration//GEN-END:variables
}
