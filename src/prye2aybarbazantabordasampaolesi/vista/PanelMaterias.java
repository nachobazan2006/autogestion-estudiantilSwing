package prye2aybarbazantabordasampaolesi.vista;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import prye2aybarbazantabordasampaolesi.controlador.ControladorEstudiante;
import prye2aybarbazantabordasampaolesi.modelo.InscripcionMateria;

public class PanelMaterias extends javax.swing.JPanel {

    private ControladorEstudiante controlador;
    private DefaultTableModel tablaModel;
    private DefaultComboBoxModel<String> materiasModel;
    private DefaultListModel<String> criticasModel;

    public PanelMaterias() {
        initComponents();
        initModelos();
    }

    public void init(ControladorEstudiante controlador, DefaultListModel<String> criticasModel) {
        this.controlador = controlador;
        this.criticasModel = criticasModel;
    }

    private void initModelos() {
        materiasModel = new DefaultComboBoxModel<>();
        jComboMaterias.setModel(materiasModel);
        tablaModel = new DefaultTableModel(new Object[]{"Nombre", "Condicion", "Asistencia %", "Promedio"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTableMaterias.setModel(tablaModel);
        jTableMaterias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void refrescar() {
        if (controlador == null) {
            return;
        }
        tablaModel.setRowCount(0);
        materiasModel.removeAllElements();
        ArrayList<InscripcionMateria> inscripciones = new ArrayList<>(controlador.getInscripciones());
        for (InscripcionMateria inscripcion : inscripciones) {
            tablaModel.addRow(new Object[]{
                inscripcion.getMateria().getNombre(),
                inscripcion.getCondicion(),
                String.format("%.2f", inscripcion.getPorcentajeAsistencia()),
                String.format("%.2f", inscripcion.getPromedio())
            });
            materiasModel.addElement(inscripcion.getMateria().getCodigo());
        }
        actualizarCriticas();
    }

    public DefaultListModel<String> getCriticasModel() {
        if (criticasModel == null) {
            criticasModel = new DefaultListModel<>();
        }
        return criticasModel;
    }

    private void inscribirMateria() {
        if (controlador == null) {
            return;
        }
        try {
            controlador.inscribirMateria(
                    jTextNombre.getText(),
                    jTextCodigo.getText(),
                    Integer.parseInt(jTextCuatrimestre.getText().trim()),
                    Integer.parseInt(jTextAnio.getText().trim())
            );
            limpiarCamposAlta();
            refrescar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarAsistencia(boolean presente) {
        if (controlador == null) {
            return;
        }
        String codigo = (String) jComboMaterias.getSelectedItem();
        if (codigo == null) {
            return;
        }
        try {
            controlador.registrarAsistencia(codigo, presente);
            InscripcionMateria inscripcion = controlador.getEstudiante().getInscripcion(codigo);
            if (inscripcion.getPorcentajeAsistencia() < 75) {
                JOptionPane.showMessageDialog(this, "La asistencia bajo del 75%.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            refrescar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarNota() {
        if (controlador == null) {
            return;
        }
        String codigo = (String) jComboMaterias.getSelectedItem();
        if (codigo == null) {
            return;
        }
        try {
            controlador.registrarNota(codigo, Double.parseDouble(jTextNota.getText().trim()));
            jTextNota.setText("");
            refrescar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void darDeBaja() {
        if (controlador == null) {
            return;
        }
        String codigo = (String) jComboMaterias.getSelectedItem();
        if (codigo == null) {
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this, "Dar de baja la materia seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                controlador.darDeBaja(codigo);
                refrescar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarCriticas() {
        if (criticasModel == null) {
            return;
        }
        criticasModel.clear();
        for (InscripcionMateria inscripcion : controlador.getEstudiante().getMateriasCriticas()) {
            criticasModel.addElement(inscripcion.getMateria().getNombre() + " (" + inscripcion.getMateria().getCodigo() + ")");
        }
    }

    private void limpiarCamposAlta() {
        jTextNombre.setText("");
        jTextCodigo.setText("");
        jTextCuatrimestre.setText("");
        jTextAnio.setText("");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelForm = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jLabelCodigo = new javax.swing.JLabel();
        jTextCodigo = new javax.swing.JTextField();
        jLabelCuatrimestre = new javax.swing.JLabel();
        jTextCuatrimestre = new javax.swing.JTextField();
        jLabelAnio = new javax.swing.JLabel();
        jTextAnio = new javax.swing.JTextField();
        jBtnInscribir = new javax.swing.JButton();
        jScrollTabla = new javax.swing.JScrollPane();
        jTableMaterias = new javax.swing.JTable();
        jPanelAcciones = new javax.swing.JPanel();
        jLabelMateria = new javax.swing.JLabel();
        jComboMaterias = new javax.swing.JComboBox();
        jBtnAsistencia = new javax.swing.JButton();
        jBtnAusente = new javax.swing.JButton();
        jBtnBaja = new javax.swing.JButton();
        jLabelNota = new javax.swing.JLabel();
        jTextNota = new javax.swing.JTextField();
        jBtnNota = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanelForm.setBackground(new java.awt.Color(255, 255, 255));
        jPanelForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Inscribir Nueva Materia"));
        jPanelForm.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 10));

        jLabelNombre.setText("Nombre");
        jPanelForm.add(jLabelNombre);

        jTextNombre.setColumns(12);
        jPanelForm.add(jTextNombre);

        jLabelCodigo.setText("Codigo");
        jPanelForm.add(jLabelCodigo);

        jTextCodigo.setColumns(6);
        jPanelForm.add(jTextCodigo);

        jLabelCuatrimestre.setText("Cuatrimestre");
        jPanelForm.add(jLabelCuatrimestre);

        jTextCuatrimestre.setColumns(4);
        jPanelForm.add(jTextCuatrimestre);

        jLabelAnio.setText("Anio");
        jPanelForm.add(jLabelAnio);

        jTextAnio.setColumns(4);
        jPanelForm.add(jTextAnio);

        jBtnInscribir.setText("Inscribir");
        jBtnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInscribirActionPerformed(evt);
            }
        });
        jPanelForm.add(jBtnInscribir);

        add(jPanelForm, java.awt.BorderLayout.NORTH);

        jTableMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Condicion", "Asistencia %", "Promedio"
            }
        ));
        jScrollTabla.setViewportView(jTableMaterias);

        add(jScrollTabla, java.awt.BorderLayout.CENTER);

        jPanelAcciones.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones sobre Materia Seleccionada"));
        jPanelAcciones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 10));

        jLabelMateria.setText("Materia");
        jPanelAcciones.add(jLabelMateria);

        jPanelAcciones.add(jComboMaterias);

        jBtnAsistencia.setText("Registrar asistencia");
        jBtnAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAsistenciaActionPerformed(evt);
            }
        });
        jPanelAcciones.add(jBtnAsistencia);

        jBtnAusente.setText("Ausente");
        jBtnAusente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAusenteActionPerformed(evt);
            }
        });
        jPanelAcciones.add(jBtnAusente);

        jBtnBaja.setText("Dar de baja");
        jBtnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBajaActionPerformed(evt);
            }
        });
        jPanelAcciones.add(jBtnBaja);

        jLabelNota.setText("Nota");
        jPanelAcciones.add(jLabelNota);

        jTextNota.setColumns(4);
        jPanelAcciones.add(jTextNota);

        jBtnNota.setText("Registrar nota");
        jBtnNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNotaActionPerformed(evt);
            }
        });
        jPanelAcciones.add(jBtnNota);

        add(jPanelAcciones, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnInscribirActionPerformed
        inscribirMateria();
    }//GEN-LAST:event_jBtnInscribirActionPerformed

    private void jBtnAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAsistenciaActionPerformed
        registrarAsistencia(true);
    }//GEN-LAST:event_jBtnAsistenciaActionPerformed

    private void jBtnAusenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAusenteActionPerformed
        registrarAsistencia(false);
    }//GEN-LAST:event_jBtnAusenteActionPerformed

    private void jBtnNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNotaActionPerformed
        registrarNota();
    }//GEN-LAST:event_jBtnNotaActionPerformed

    private void jBtnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBajaActionPerformed
        darDeBaja();
    }//GEN-LAST:event_jBtnBajaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAsistencia;
    private javax.swing.JButton jBtnAusente;
    private javax.swing.JButton jBtnBaja;
    private javax.swing.JButton jBtnInscribir;
    private javax.swing.JButton jBtnNota;
    private javax.swing.JComboBox jComboMaterias;
    private javax.swing.JLabel jLabelAnio;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelCuatrimestre;
    private javax.swing.JLabel jLabelMateria;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNota;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JScrollPane jScrollTabla;
    private javax.swing.JTable jTableMaterias;
    private javax.swing.JTextField jTextAnio;
    private javax.swing.JTextField jTextCodigo;
    private javax.swing.JTextField jTextCuatrimestre;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextNota;
    // End of variables declaration//GEN-END:variables
}
