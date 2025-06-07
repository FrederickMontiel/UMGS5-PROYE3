/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gt.edu.miumg.fmontiel.views;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import gt.edu.miumg.fmontiel.listeners.KeyTypedTextFieldFormulaListener;
import gt.edu.miumg.fmontiel.model.Celda;
import gt.edu.miumg.fmontiel.views.mainview.CeldaCustomViewController;
import gt.edu.miumg.fmontiel.views.mainview.CeldaTableModel;
import gt.edu.miumg.fmontiel.views.mainview.renderers.FormulaCellRenderer;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Trabajo
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
        initializeTable();
    }

    private void initializeTable() {
        jtableParaExcel = new JTable(new CeldaTableModel(1000, 1000, () -> {
            textFieldFormulaCelda.setText("");
        }));

        ajustarAnchoPrimeraColumna(jtableParaExcel, jtableParaExcel.getRowCount());

        jtableParaExcel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtableParaExcel.getTableHeader().setReorderingAllowed(false);
        scrollPaneParaCeldas.setViewportView(jtableParaExcel);

        jtableParaExcel.setDefaultRenderer(Object.class, new FormulaCellRenderer());

        jtableParaExcel.setDefaultEditor(Object.class, new CeldaCustomViewController(
                new KeyTypedTextFieldFormulaListener() {
                    @Override
                    public void keyTyped(String text) {
                        textFieldFormulaCelda.setText(text);
                    }
                }));

        jtableParaExcel.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateFormulaTextField();
            }
        });

        jtableParaExcel.getColumnModel().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateFormulaTextField();
            }
        });

        jtableParaExcel.getModel().addTableModelListener(e -> {
            updateFormulaTextField();
        });

        jtableParaExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateFormulaTextField();
            }
        });
    }

    private void updateFormulaTextField() {
        int row = jtableParaExcel.getSelectedRow();
        int column = jtableParaExcel.getSelectedColumn();
        if (row >= 0 && column > 0) {
            CeldaTableModel model = (CeldaTableModel) jtableParaExcel.getModel();
            Celda celda = model.getCeldaAt(row, column);
            if (celda != null) {
                String formula = celda.getFormula();
                textFieldFormulaCelda.setText(formula != null && !formula.isEmpty() ? formula : celda.getValor());
            } else {
                textFieldFormulaCelda.setText("");
            }
        }
    }

    private static void ajustarAnchoPrimeraColumna(JTable tabla, int filas) {
        TableColumn primeraColumna = tabla.getColumnModel().getColumn(0);

        FontMetrics fontMetrics = tabla.getFontMetrics(tabla.getFont());

        String textoMasGrande = String.valueOf(filas);
        int anchoTexto = fontMetrics.stringWidth(textoMasGrande);

        int margen = 10;

        primeraColumna.setPreferredWidth(anchoTexto + margen);
        primeraColumna.setMinWidth(anchoTexto + margen);
        primeraColumna.setMaxWidth(anchoTexto + margen);

        primeraColumna.setCellRenderer(new RendererColumnaGris());
    }

    static class RendererColumnaGris extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (column == 0) {
                c.setBackground(Color.lightGray);
                c.setForeground(Color.BLACK);
            } else {
                c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            }

            return c;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        textFieldFormulaCelda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        scrollPaneParaCeldas = new javax.swing.JScrollPane();
        jtableParaExcel = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openFileMenuItem = new javax.swing.JMenuItem();
        guardarDocumentoMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        informacionProgramaMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(null);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(215, 215, 215));
        jPanel1.setForeground(new java.awt.Color(54, 54, 54));
        jPanel1.setToolTipText("");
        jPanel1.setMaximumSize(null);

        jPanel3.setPreferredSize(new java.awt.Dimension(868, 50));

        textFieldFormulaCelda.setMinimumSize(new java.awt.Dimension(64, 20));
        textFieldFormulaCelda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFormulaCeldaActionPerformed(evt);
            }
        });

        jLabel1.setText("f(x)=");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldFormulaCelda, javax.swing.GroupLayout.DEFAULT_SIZE, 856,
                                        Short.MAX_VALUE)
                                .addContainerGap()));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textFieldFormulaCelda, javax.swing.GroupLayout.DEFAULT_SIZE, 25,
                                                Short.MAX_VALUE)
                                        .addComponent(jLabel1))
                                .addContainerGap()));

        scrollPaneParaCeldas.setMinimumSize(new java.awt.Dimension(16, 480));

        jtableParaExcel.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        scrollPaneParaCeldas.setViewportView(jtableParaExcel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                        .addComponent(scrollPaneParaCeldas, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(scrollPaneParaCeldas, javax.swing.GroupLayout.DEFAULT_SIZE, 480,
                                        Short.MAX_VALUE)
                                .addGap(0, 0, 0)));

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2);

        jMenu1.setText("Archivo");

        openFileMenuItem.setText("Abrir");
        openFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(openFileMenuItem);

        guardarDocumentoMenuItem.setText("Guardar");
        guardarDocumentoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarDocumentoMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(guardarDocumentoMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Ayuda");

        informacionProgramaMenuItem.setText("Información");
        jMenu3.add(informacionProgramaMenuItem);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarDocumentoComoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_guardarDocumentoComoMenuItemActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_guardarDocumentoComoMenuItemActionPerformed

    private void openFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            // Create file chooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Abrir archivo CSV");

            // Set default directory to Documents
            String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
            fileChooser.setCurrentDirectory(new File(documentsPath));

            // Set file filter for CSV files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv");
            fileChooser.setFileFilter(filter);

            int userSelection = fileChooser.showOpenDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();

                // Create a new table model with the same dimensions as the current one
                CeldaTableModel model = (CeldaTableModel) jtableParaExcel.getModel();

                // Create BufferedReader
                BufferedReader br = new BufferedReader(new FileReader(fileToOpen));

                String line;
                int rowIndex = 0;

                // Read file line by line
                while ((line = br.readLine()) != null && rowIndex < model.getRowCount()) {
                    // Split the line, handling quoted values
                    java.util.List<String> values = parseCSVLine(line);

                    // Fill the cells with the values
                    for (int j = 0; j < values.size() && j < model.getColumnCount() - 1; j++) {
                        String value = values.get(j);
                        Celda celda = model.getCeldaAt(rowIndex, j + 1);

                        // Check if the value is a formula (starts with =)
                        if (value.startsWith("=")) {
                            celda.setFormula(value);
                        } else {
                            celda.setFormula(value);
                        }

                        // Notify the model that the cell has been updated
                        model.fireTableCellUpdated(rowIndex, j + 1);
                    }

                    rowIndex++;
                }

                // Close the reader
                br.close();

                // Force table to refresh completely
                model.fireTableDataChanged();

                JOptionPane.showMessageDialog(this,
                        "Archivo cargado exitosamente:\n" + fileToOpen.getAbsolutePath(),
                        "Abrir CSV", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al abrir el archivo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }// GEN-LAST:event_jMenuItem1ActionPerformed

    private void guardarDocumentoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como CSV");

            String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
            fileChooser.setCurrentDirectory(new File(documentsPath));

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv");
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File("hoja_de_calculo.csv"));

            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                if (!fileToSave.getName().toLowerCase().endsWith(".csv")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
                }

                FileWriter fw = new FileWriter(fileToSave);
                BufferedWriter bw = new BufferedWriter(fw);

                CeldaTableModel model = (CeldaTableModel) jtableParaExcel.getModel();
                int rowCount = model.getRowCount();
                int colCount = model.getColumnCount();

                for (int i = 0; i < rowCount; i++) {
                    StringBuilder line = new StringBuilder();

                    for (int j = 0; j < colCount; j++) {
                        Celda celda = model.getCeldaAt(i, j);
                        String value = celda != null ? celda.getValor().toString() : "";

                        if (value.contains(",")) {
                            value = "\"" + value + "\"";
                        }

                        line.append(value);

                        if (j < colCount - 1) {
                            line.append(";");
                        }
                    }

                    bw.write(line.toString());
                    bw.newLine();
                }

                bw.close();
                fw.close();

                JOptionPane.showMessageDialog(this,
                        "Archivo guardado exitosamente en:\n" + fileToSave.getAbsolutePath(),
                        "Guardar CSV", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar el archivo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }// GEN-LAST:event_jMenuItem2ActionPerformed

    private java.util.List<String> parseCSVLine(String line) {
        java.util.List<String> result = new ArrayList<>();

        // Check if the file uses semicolons (;) or commas (,) as separators
        String separator = line.contains(";") ? ";" : ",";

        // Simple implementation for basic CSV parsing
        if (!line.contains("\"")) {
            // No quotes, simple split
            String[] values = line.split(separator);
            for (String value : values) {
                result.add(value.trim());
            }
        } else {
            // With quotes, more complex parsing
            boolean inQuotes = false;
            StringBuilder currentValue = new StringBuilder();

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                if (c == '"') {
                    inQuotes = !inQuotes;
                } else if (c == separator.charAt(0) && !inQuotes) {
                    result.add(currentValue.toString().trim());
                    currentValue = new StringBuilder();
                } else {
                    currentValue.append(c);
                }
            }

            // Add the last value
            result.add(currentValue.toString().trim());
        }

        return result;
    }

    private void textFieldFormulaCeldaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem guardarDocumentoMenuItem;
    private javax.swing.JMenuItem informacionProgramaMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable jtableParaExcel;
    private javax.swing.JMenuItem openFileMenuItem;
    private javax.swing.JScrollPane scrollPaneParaCeldas;
    private javax.swing.JTextField textFieldFormulaCelda;
    // End of variables declaration//GEN-END:variables
}
