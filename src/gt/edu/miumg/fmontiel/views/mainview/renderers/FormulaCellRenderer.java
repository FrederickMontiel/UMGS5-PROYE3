package gt.edu.miumg.fmontiel.views.mainview.renderers;

import gt.edu.miumg.fmontiel.views.mainview.CeldaTableModel;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormulaCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        if (isSelected && column > 0) {
            CeldaTableModel model = (CeldaTableModel) table.getModel();
            gt.edu.miumg.fmontiel.model.Celda celda = model.getCeldaAt(row, column);

            if (celda != null && celda.getFormula() != null && !celda.getFormula().isEmpty()) {
                setText(celda.getFormula());
            }
        }

        return c;
    }
}
