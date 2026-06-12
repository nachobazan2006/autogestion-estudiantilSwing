package prye2aybarbazantabordasampaolesi.vista;

import javax.swing.DefaultListModel;
import prye2aybarbazantabordasampaolesi.controlador.ControladorEstudiante;


public class VentanaPrincipal extends javax.swing.JFrame {

    private ControladorEstudiante controlador;

    public VentanaPrincipal() {
        initComponents();
    }

    public VentanaPrincipal(ControladorEstudiante controlador) {
        this();
        this.controlador = controlador;
        panelPerfil.Controlador(controlador);
        DefaultListModel<String> criticasModel = panelMaterias.getCriticasModel();
        panelMaterias.ControladorModel(controlador, criticasModel);
        panelReportes.ControladorModel(controlador, criticasModel);
        refrescar();
        cardLayout.show(contenedor, "MATERIAS");
    }

    public void refrescar() {
        panelPerfil.refrescar();
        panelMaterias.refrescar();
        panelReportes.refrescar();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardLayout = new java.awt.CardLayout();
        panelPerfil = new prye2aybarbazantabordasampaolesi.vista.PanelPerfil();
        contenedor = new javax.swing.JPanel();
        panelMaterias = new prye2aybarbazantabordasampaolesi.vista.PanelMaterias();
        panelReportes = new prye2aybarbazantabordasampaolesi.vista.PanelReportes();
        menuBarPrincipal = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        jMenuItemGuardar = new javax.swing.JMenuItem();
        jMenuItemCargar = new javax.swing.JMenuItem();
        menuItemSalir = new javax.swing.JMenuItem();
        menuPantallas = new javax.swing.JMenu();
        menuItemMaterias = new javax.swing.JMenuItem();
        menuItemReportes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autogestion Estudiantil");
        setMinimumSize(new java.awt.Dimension(750, 148));
        setPreferredSize(new java.awt.Dimension(950, 650));
        getContentPane().add(panelPerfil, java.awt.BorderLayout.NORTH);

        contenedor.setLayout(cardLayout);
        contenedor.add(panelMaterias, "MATERIAS");
        contenedor.add(panelReportes, "REPORTES");

        getContentPane().add(contenedor);

        menuArchivo.setText("Archivo");

        jMenuItemGuardar.setText("Guardar");
        jMenuItemGuardar.addActionListener(this::jMenuItemGuardarActionPerformed);
        menuArchivo.add(jMenuItemGuardar);

        jMenuItemCargar.setText("Cargar");
        jMenuItemCargar.addActionListener(this::jMenuItemCargarActionPerformed);
        menuArchivo.add(jMenuItemCargar);

        menuItemSalir.setText("Salir");
        menuItemSalir.addActionListener(this::menuItemSalirActionPerformed);
        menuArchivo.add(menuItemSalir);

        menuBarPrincipal.add(menuArchivo);

        menuPantallas.setText("Pantallas");

        menuItemMaterias.setText("Materias");
        menuItemMaterias.addActionListener(this::menuItemMateriasActionPerformed);
        menuPantallas.add(menuItemMaterias);

        menuItemReportes.setText("Reportes");
        menuItemReportes.addActionListener(this::menuItemReportesActionPerformed);
        menuPantallas.add(menuItemReportes);

        menuBarPrincipal.add(menuPantallas);

        setJMenuBar(menuBarPrincipal);

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

    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemSalirActionPerformed

    private void menuItemMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemMateriasActionPerformed
        refrescar();
        cardLayout.show(contenedor, "MATERIAS");
    }//GEN-LAST:event_menuItemMateriasActionPerformed

    private void menuItemReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemReportesActionPerformed
        refrescar();
        cardLayout.show(contenedor, "REPORTES");
    }//GEN-LAST:event_menuItemReportesActionPerformed

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ControladorEstudiante controlador = new ControladorEstudiante();
            controlador.cargarDatos();

            VentanaPrincipal ventana = new VentanaPrincipal(controlador);
            ventana.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.CardLayout cardLayout;
    private javax.swing.JPanel contenedor;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuBar menuBarPrincipal;
    private javax.swing.JMenuItem menuItemMaterias;
    private javax.swing.JMenuItem menuItemReportes;
    private javax.swing.JMenuItem jMenuItemCargar;
    private javax.swing.JMenuItem jMenuItemGuardar;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenu menuPantallas;
    private prye2aybarbazantabordasampaolesi.vista.PanelMaterias panelMaterias;
    private prye2aybarbazantabordasampaolesi.vista.PanelPerfil panelPerfil;
    private prye2aybarbazantabordasampaolesi.vista.PanelReportes panelReportes;
    // End of variables declaration//GEN-END:variables
}
