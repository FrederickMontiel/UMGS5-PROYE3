/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.fmontiel.views.mainview;

import javax.swing.table.AbstractTableModel;

import gt.edu.miumg.fmontiel.listeners.FinishFieldFormulaListener;
import gt.edu.miumg.fmontiel.model.Celda;

/**
 *
 * @author Trabajo
 */
public class CeldaTableModel extends AbstractTableModel {
    private final Celda[][] celdas;
    private final FinishFieldFormulaListener onFinishFieldFormula;

    public CeldaTableModel(int filas, int columnas, FinishFieldFormulaListener onFinishFieldFormula) {
        celdas = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda("", i, j);
            }
        }

        this.onFinishFieldFormula = onFinishFieldFormula;
    }

    public Celda getCeldaAt(int rowIndex, int columnIndex) {
        if (columnIndex > 0) {
            return celdas[rowIndex][columnIndex - 1];
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return celdas.length;
    }

    @Override
    public int getColumnCount() {
        return celdas[0].length + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return rowIndex + 1;
        }
        return celdas[rowIndex][columnIndex - 1].getValor();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex > 0) {
            celdas[rowIndex][columnIndex - 1].setFormula(aValue.toString());
            fireTableCellUpdated(rowIndex, columnIndex);

            this.onFinishFieldFormula.onFinish();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "";
        }
        return super.getColumnName(column - 1);
    }
}