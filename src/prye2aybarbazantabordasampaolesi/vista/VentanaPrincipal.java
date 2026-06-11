package prye2aybarbazantabordasampaolesi.vista;

import java.awt.CardLayout;
import javax.swing.DefaultListModel;
import prye2aybarbazantabordasampaolesi.controlador.ControladorEstudiante;

public class VentanaPrincipal extends javax.swing.JFrame {

    private ControladorEstudiante controlador;
    private CardLayout cardLayout;

    public VentanaPrincipal() {
        initComponents();
        cardLayout = (CardLayout) contenedor.getLayout();
        setSize(950, 650);
        setLocationRelativeTo(null);
    }

    public VentanaPrincipal(ControladorEstudiante controlador) {
        this();
        this.controlador = controlador;
        DefaultListModel<String> criticasModel = panelMaterias.getCriticasModel();
        panelMaterias.init(controlador, criticasModel);
        panelReportes.init(controlador, criticasModel);
        refrescar();
        cardLayout.show(contenedor, "MATERIAS");
    }

    public void refrescar() {
        panelMaterias.refrescar();
        panelReportes.refrescar();
    }

    private void mostrarReportes() {
        refrescar();
        cardLayout.show(contenedor, "REPORTES");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JPanel();
        panelReportes = new prye2aybarbazantabordasampaolesi.vista.PanelReportes();
        panelMaterias = new prye2aybarbazantabordasampaolesi.vista.PanelMaterias();
        jMenuBarPrincipal = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItemGuardar = new javax.swing.JMenuItem();
        jMenuItemCargar = new javax.swing.JMenuItem();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuPantallas = new javax.swing.JMenu();
        jMenuItemMaterias = new javax.swing.JMenuItem();
        jMenuItemReportes = new javax.swing.JMenuItem();
        jMenuReportes = new javax.swing.JMenu();
        jMenuItemSituacion = new javax.swing.JMenuItem();
        jMenuItemCriticas = new javax.swing.JMenuItem();
        jMenuItemAprobadas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autogestion Estudiantil");
        setMinimumSize(new java.awt.Dimension(750, 148));

        contenedor.setLayout(new java.awt.CardLayout());
        contenedor.add(panelReportes, "REPORTES");
        contenedor.add(panelMaterias, "MATERIAS");

        getContentPane().add(contenedor);

        jMenuArchivo.setText("Archivo");

        jMenuItemGuardar.setText("Guardar");
        jMenuItemGuardar.addActionListener(this::jMenuItemGuardarActionPerformed);
        jMenuArchivo.add(jMenuItemGuardar);

        jMenuItemCargar.setText("Cargar");
        jMenuItemCargar.addActionListener(this::jMenuItemCargarActionPerformed);
        jMenuArchivo.add(jMenuItemCargar);

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(this::jMenuItemSalirActionPerformed);
        jMenuArchivo.add(jMenuItemSalir);

        jMenuBarPrincipal.add(jMenuArchivo);

        jMenuPantallas.setText("Pantallas");

        jMenuItemMaterias.setText("Materias");
        jMenuItemMaterias.addActionListener(this::jMenuItemMateriasActionPerformed);
        jMenuPantallas.add(jMenuItemMaterias);

        jMenuItemReportes.setText("Reportes");
        jMenuItemReportes.addActionListener(this::jMenuItemReportesActionPerformed);
        jMenuPantallas.add(jMenuItemReportes);

        jMenuBarPrincipal.add(jMenuPantallas);

        jMenuReportes.setText("Reportes");

        jMenuItemSituacion.setText("Situacion General");
        jMenuItemSituacion.addActionListener(this::jMenuItemSituacionActionPerformed);
        jMenuReportes.add(jMenuItemSituacion);

        jMenuItemCriticas.setText("Materias Criticas");
        jMenuItemCriticas.addActionListener(this::jMenuItemCriticasActionPerformed);
        jMenuReportes.add(jMenuItemCriticas);

        jMenuItemAprobadas.setText("Materias Aprobadas");
        jMenuItemAprobadas.addActionListener(this::jMenuItemAprobadasActionPerformed);
        jMenuReportes.add(jMenuItemAprobadas);

        jMenuBarPrincipal.add(jMenuReportes);

        setJMenuBar(jMenuBarPrincipal);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarActionPerformed
        if (controlador == null) {
            return;
        }
        try {
            controlador.guardarDatos();
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemGuardarActionPerformed

    private void jMenuItemCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCargarActionPerformed
        if (controlador == null) {
            return;
        }
        try {
            controlador.cargarDatos();
            refrescar();
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemCargarActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMateriasActionPerformed
        refrescar();
        cardLayout.show(contenedor, "MATERIAS");
    }//GEN-LAST:event_jMenuItemMateriasActionPerformed

    private void jMenuItemReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReportesActionPerformed
        mostrarReportes();
    }//GEN-LAST:event_jMenuItemReportesActionPerformed

    private void jMenuItemSituacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSituacionActionPerformed
        mostrarReportes();
    }//GEN-LAST:event_jMenuItemSituacionActionPerformed

    private void jMenuItemCriticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCriticasActionPerformed
        mostrarReportes();
    }//GEN-LAST:event_jMenuItemCriticasActionPerformed

    private void jMenuItemAprobadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAprobadasActionPerformed
        mostrarReportes();
    }//GEN-LAST:event_jMenuItemAprobadasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedor;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenuItem jMenuItemAprobadas;
    private javax.swing.JMenuItem jMenuItemCargar;
    private javax.swing.JMenuItem jMenuItemCriticas;
    private javax.swing.JMenuItem jMenuItemGuardar;
    private javax.swing.JMenuItem jMenuItemMaterias;
    private javax.swing.JMenuItem jMenuItemReportes;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemSituacion;
    private javax.swing.JMenu jMenuPantallas;
    private javax.swing.JMenu jMenuReportes;
    private prye2aybarbazantabordasampaolesi.vista.PanelMaterias panelMaterias;
    private prye2aybarbazantabordasampaolesi.vista.PanelReportes panelReportes;
    // End of variables declaration//GEN-END:variables
}
