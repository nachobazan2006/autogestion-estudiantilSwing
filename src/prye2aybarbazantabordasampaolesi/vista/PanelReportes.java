package prye2aybarbazantabordasampaolesi.vista;

import javax.swing.DefaultListModel;
import prye2aybarbazantabordasampaolesi.controlador.ControladorEstudiante;
import prye2aybarbazantabordasampaolesi.modelo.InscripcionMateria;

public class PanelReportes extends javax.swing.JPanel {

    private ControladorEstudiante controlador;
    private DefaultListModel<String> aprobadasModel;
    private DefaultListModel<String> criticasModel;

    public PanelReportes() {
        initComponents();
        Modelos();
    }

    public void ControladorModel(ControladorEstudiante controlador, DefaultListModel<String> criticasModel) {
        this.controlador = controlador;
        this.criticasModel = criticasModel;
        jListCriticas.setModel(criticasModel);
    }

    private void Modelos() {
        aprobadasModel = new DefaultListModel<>();
        jListAprobadas.setModel(aprobadasModel);
    }

    public void refrescar() {
        if (controlador == null) {
            return;
        }
        int cantidad = controlador.getInscripciones().size();
        double promedioGeneral = controlador.getEstudiante().getPromedioGeneral();
        int aprobadas = 0;
        int criticas = controlador.getEstudiante().getMateriasCriticas().size();
        aprobadasModel.clear();

        for (InscripcionMateria inscripcion : controlador.getInscripciones()) {
            if (inscripcion.estaAprobada()) {
                aprobadas++;
                aprobadasModel.addElement(inscripcion.getMateria().getNombre() + " - " + String.format("%.2f", inscripcion.getPromedio()));
            }
        }

        lblCantidad.setText("Cantidad de materias: " + cantidad);
        lblPromedioGeneral.setText("Promedio general: " + String.format("%.2f", promedioGeneral));
        lblAprobadas.setText("Cantidad aprobadas: " + aprobadas);
        lblCriticas.setText("Cantidad criticas: " + criticas);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelResumen = new javax.swing.JPanel();
        lblCantidad = new javax.swing.JLabel();
        lblPromedioGeneral = new javax.swing.JLabel();
        lblAprobadas = new javax.swing.JLabel();
        lblCriticas = new javax.swing.JLabel();
        jPanelListas = new javax.swing.JPanel();
        jScrollCriticas = new javax.swing.JScrollPane();
        jListCriticas = new javax.swing.JList();
        jScrollAprobadas = new javax.swing.JScrollPane();
        jListAprobadas = new javax.swing.JList();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanelResumen.setBackground(new java.awt.Color(255, 255, 255));
        jPanelResumen.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen Académico"));
        jPanelResumen.setLayout(new java.awt.GridLayout(1, 4, 15, 10));

        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidad.setText("Cantidad de materias: 0");
        jPanelResumen.add(lblCantidad);

        lblPromedioGeneral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPromedioGeneral.setText("Promedio general: 0.00");
        jPanelResumen.add(lblPromedioGeneral);

        lblAprobadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAprobadas.setText("Cantidad aprobadas: 0");
        jPanelResumen.add(lblAprobadas);

        lblCriticas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCriticas.setText("Cantidad criticas: 0");
        jPanelResumen.add(lblCriticas);

        add(jPanelResumen, java.awt.BorderLayout.NORTH);

        jPanelListas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelListas.setLayout(new java.awt.GridLayout(1, 2, 15, 10));

        jScrollCriticas.setBorder(javax.swing.BorderFactory.createTitledBorder("Materias Críticas"));
        jScrollCriticas.setViewportView(jListCriticas);
        jPanelListas.add(jScrollCriticas);

        jScrollAprobadas.setBorder(javax.swing.BorderFactory.createTitledBorder("Materias Aprobadas"));
        jScrollAprobadas.setViewportView(jListAprobadas);
        jPanelListas.add(jScrollAprobadas);

        add(jPanelListas, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jListAprobadas;
    private javax.swing.JList jListCriticas;
    private javax.swing.JPanel jPanelListas;
    private javax.swing.JPanel jPanelResumen;
    private javax.swing.JScrollPane jScrollAprobadas;
    private javax.swing.JScrollPane jScrollCriticas;
    private javax.swing.JLabel lblAprobadas;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCriticas;
    private javax.swing.JLabel lblPromedioGeneral;
    // End of variables declaration//GEN-END:variables
}
