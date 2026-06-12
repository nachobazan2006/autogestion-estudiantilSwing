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
        Modelos();
    }

    public void ControladorModel(ControladorEstudiante controlador, DefaultListModel<String> criticasModel) {
        this.controlador = controlador;
        this.criticasModel = criticasModel;
    }

    private void Modelos() {
        materiasModel = (DefaultComboBoxModel<String>) cmbMaterias.getModel();
        tablaModel = (DefaultTableModel) jTableMaterias.getModel();
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
                    txtNombre.getText(),
                    txtCodigo.getText(),
                    Integer.parseInt(txtCuatrimestre.getText().trim()),
                    Integer.parseInt(txtAnio.getText().trim())
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
        String codigo = (String) cmbMaterias.getSelectedItem();
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
        String codigo = (String) cmbMaterias.getSelectedItem();
        if (codigo == null) {
            return;
        }
        try {
            controlador.registrarNota(codigo, Double.parseDouble(txtNota.getText().trim()));
            txtNota.setText("");
            refrescar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void darDeBaja() {
        if (controlador == null) {
            return;
        }
        String codigo = (String) cmbMaterias.getSelectedItem();
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
        txtNombre.setText("");
        txtCodigo.setText("");
        txtCuatrimestre.setText("");
        txtAnio.setText("");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelForm = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblCuatrimestre = new javax.swing.JLabel();
        txtCuatrimestre = new javax.swing.JTextField();
        lblAnio = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        btnInscribir = new javax.swing.JButton();
        jScrollTabla = new javax.swing.JScrollPane();
        jTableMaterias = new javax.swing.JTable();
        jPanelAcciones = new javax.swing.JPanel();
        lblMateria = new javax.swing.JLabel();
        cmbMaterias = new javax.swing.JComboBox();
        btnAsistencia = new javax.swing.JButton();
        btnAusente = new javax.swing.JButton();
        lblNota = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        btnNota = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanelForm.setBackground(new java.awt.Color(255, 255, 255));
        jPanelForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Inscribir Nueva Materia"));
        jPanelForm.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 10));

        lblNombre.setText("Nombre");
        jPanelForm.add(lblNombre);

        txtNombre.setColumns(12);
        jPanelForm.add(txtNombre);

        lblCodigo.setText("Codigo");
        jPanelForm.add(lblCodigo);

        txtCodigo.setColumns(6);
        jPanelForm.add(txtCodigo);

        lblCuatrimestre.setText("Cuatrimestre");
        jPanelForm.add(lblCuatrimestre);

        txtCuatrimestre.setColumns(4);
        jPanelForm.add(txtCuatrimestre);

        lblAnio.setText("Año");
        jPanelForm.add(lblAnio);

        txtAnio.setColumns(4);
        jPanelForm.add(txtAnio);

        btnInscribir.setText("Inscribir");
        btnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirActionPerformed(evt);
            }
        });
        jPanelForm.add(btnInscribir);

        add(jPanelForm, java.awt.BorderLayout.NORTH);

        jTableMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Condicion", "Asistencia %", "Promedio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMaterias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollTabla.setViewportView(jTableMaterias);

        add(jScrollTabla, java.awt.BorderLayout.CENTER);

        jPanelAcciones.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones sobre Materia Seleccionada"));
        jPanelAcciones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 10));

        lblMateria.setText("Materia");
        jPanelAcciones.add(lblMateria);

        cmbMaterias.setPreferredSize(new java.awt.Dimension(140, 22));
        jPanelAcciones.add(cmbMaterias);

        btnAsistencia.setText("Registrar asistencia");
        btnAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsistenciaActionPerformed(evt);
            }
        });
        jPanelAcciones.add(btnAsistencia);

        btnAusente.setText("Ausente");
        btnAusente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAusenteActionPerformed(evt);
            }
        });
        jPanelAcciones.add(btnAusente);

        lblNota.setText("Nota");
        jPanelAcciones.add(lblNota);

        txtNota.setColumns(4);
        jPanelAcciones.add(txtNota);

        btnNota.setText("Registrar nota");
        btnNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotaActionPerformed(evt);
            }
        });
        jPanelAcciones.add(btnNota);

        btnBaja.setText("Dar de baja");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });
        jPanelAcciones.add(btnBaja);

        add(jPanelAcciones, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscribirActionPerformed
        inscribirMateria();
    }//GEN-LAST:event_btnInscribirActionPerformed

    private void btnAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsistenciaActionPerformed
        registrarAsistencia(true);
    }//GEN-LAST:event_btnAsistenciaActionPerformed

    private void btnAusenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAusenteActionPerformed
        registrarAsistencia(false);
    }//GEN-LAST:event_btnAusenteActionPerformed

    private void btnNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotaActionPerformed
        registrarNota();
    }//GEN-LAST:event_btnNotaActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        darDeBaja();
    }//GEN-LAST:event_btnBajaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsistencia;
    private javax.swing.JButton btnAusente;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnInscribir;
    private javax.swing.JButton btnNota;
    private javax.swing.JComboBox cmbMaterias;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCuatrimestre;
    private javax.swing.JLabel lblMateria;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNota;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JScrollPane jScrollTabla;
    private javax.swing.JTable jTableMaterias;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCuatrimestre;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables
}
