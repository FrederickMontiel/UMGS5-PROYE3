package gt.edu.miumg.fmontiel.views.mainview;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

import gt.edu.miumg.fmontiel.listeners.KeyTypedTextFieldFormulaListener;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CeldaCustomViewController extends AbstractCellEditor implements TableCellEditor {
    private JTextField textField;
    private int currentRow;
    private int currentColumn;
    private KeyTypedTextFieldFormulaListener actions;

    public static JTable tabla;

    public CeldaCustomViewController(KeyTypedTextFieldFormulaListener actions) {
        this.actions = actions;
        textField = new JTextField();
        textField.setBorder(null);

        textField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                actions.keyTyped(textField.getText());
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                actions.keyTyped(textField.getText());
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                actions.keyTyped(textField.getText());
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT ||
                        keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN ||
                        keyCode == KeyEvent.VK_HOME || keyCode == KeyEvent.VK_END) {
                    actions.keyTyped(textField.getText());
                }

            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CeldaCustomViewController.tabla = table;
        CeldaTableModel modelo = (CeldaTableModel) CeldaCustomViewController.tabla.getModel();
        currentRow = row;
        currentColumn = column;
        // textField.setText(value != null ? value.toString() : "");
        textField.setText(modelo.getCeldaAt(row, column).getFormula());
        return textField;
    }

    @Override
    public Object getCellEditorValue() {
        return textField.getText();
    }

    @Override
    public boolean stopCellEditing() {
        actions.keyTyped(textField.getText());
        return super.stopCellEditing();
    }

}
