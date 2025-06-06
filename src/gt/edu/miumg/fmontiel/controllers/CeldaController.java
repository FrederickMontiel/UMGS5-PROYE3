package gt.edu.miumg.fmontiel.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import gt.edu.miumg.fmontiel.model.Celda;
import gt.edu.miumg.fmontiel.model.Coordenada;
import gt.edu.miumg.fmontiel.views.mainview.CeldaCustomViewController;
import gt.edu.miumg.fmontiel.views.mainview.CeldaTableModel;

public class CeldaController {
    private String formula;
    private String valor;
    private int row;
    private int column;
    // Aqui se guardan las coordenadas de las celdas que se usan en la formula para
    // actualizar si alguna celda dentro de la lista cambia
    // por ejemplo: si la formula es =SUM(A1;B2) entonces las coordenadas usadas son
    // [0, 0] y [1, 1]
    List<Coordenada> coordenadasUsadas = new ArrayList<>();

    public CeldaController(
            int row,
            int column) {
        this.row = row;
        this.column = column;
        this.valor = "";
        this.formula = "";

    }

    public boolean isFormula(String formula) {
        return formula != null && !formula.isEmpty() && formula.startsWith("=");
    }

    public List<Coordenada> getCoordenadasUsadas() {
        return coordenadasUsadas;
    }

    public void validateAction(String formula) {
        List<Coordenada> nuevasCoordenadas = new ArrayList<>();
        String regex = "^=[a-zA-Z]+\\(([\\w;]+)\\)$";
        if (!formula.matches(regex)) {
            JOptionPane.showMessageDialog(null,
                    "Formato de fórmula inválido. Debe ser: =ACCION(REFERENCIA_CELDA)",
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Invalid formula format. Expected format: =ACTION(CELL_REFERENCE)");
        }

        String[] validActions = { "SUM", "MULTIPLICATION" };
        String action = formula.substring(1, formula.indexOf('('));
        boolean isValidAction = false;

        for (String validAction : validActions) {
            if (validAction.equalsIgnoreCase(action)) {
                isValidAction = true;
                break;
            }
        }

        if (!isValidAction) {
            JOptionPane.showMessageDialog(null,
                    "Acción inválida en la fórmula. Acciones válidas: SUM, MULTIPLICATION",
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Invalid action in formula. Valid actions are: SUM, MULTIPLICATION");
        }

        String cellReference = formula.substring(formula.indexOf('(') + 1, formula.indexOf(')'));

        String[] parts = cellReference.split(";");

        ArrayList<Double> valoresValidos = new ArrayList<>();

        for (String part : parts) {
            if (isCellReference(part.trim())) {
                Coordenada coordenada = convertirReferenciaCelda(part.trim());
                nuevasCoordenadas.add(coordenada);

                CeldaTableModel modelo = (CeldaTableModel) CeldaCustomViewController.tabla.getModel();

                if (coordenada.x < 0 || coordenada.y >= modelo.getRowCount() ||
                        coordenada.x < 0 || coordenada.y >= modelo.getColumnCount() - 1) {
                    JOptionPane.showMessageDialog(null, "Referencia de celda fuera de rango: " + part.trim(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    throw new IllegalArgumentException("Referencia de celda fuera de rango: " + part.trim());
                }

                Celda celda = modelo.getCeldaAt(coordenada.x,
                        coordenada.y);
                if (celda == null) {
                    JOptionPane.showMessageDialog(null, "Referencia de celda no válida: " + part.trim(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                valoresValidos.add(Double.parseDouble(celda.getValor()));
            } else {
                if (validateIfIsText(part.trim())) {
                    JOptionPane.showMessageDialog(null,
                            "Error de uso de comando, no se puede agregar un texto a una suma",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        double value = Double.parseDouble(part.trim());
                        valoresValidos.add(value);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("No es ");
                    }
                }
            }

        }

        if (action.equalsIgnoreCase("SUM")) {
            double suma = valoresValidos.stream().mapToDouble(Double::doubleValue).sum();
            this.valor = String.valueOf(suma);
        } else if (action.equalsIgnoreCase("MULTIPLICATION")) {
            double producto = valoresValidos.stream().reduce(1.0, (a, b) -> a * b);
            this.valor = String.valueOf(producto);
        }

        this.formula = formula;
        this.coordenadasUsadas = nuevasCoordenadas;
    }

    private Coordenada convertirReferenciaCelda(String referencia) {
        String columnaStr = referencia.replaceAll("[0-9]", "");
        String filaStr = referencia.replaceAll("[A-Za-z]", "");

        int columna = 0;
        for (char c : columnaStr.toCharArray()) {
            columna = columna * 26 + (Character.toUpperCase(c) - 'A' + 1);
        }

        int fila = Integer.parseInt(filaStr) - 1;

        return new Coordenada(fila, columna);
    }

    private boolean validateIfIsText(String part) {
        return part.matches(".*[a-zA-Z]+.*");
    }

    private boolean isCellReference(String param) {
        String regex = "^[A-Z]+\\d+$";
        return param.matches(regex);
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        if (this.isFormula(formula)) {
            validateAction(formula);
        } else {
            this.valor = formula;
            this.formula = formula;
        }

        updateFormula();
    }

    public void updateByChangeValueUsed(String formula) {
        if (this.isFormula(formula)) {
            validateAction(formula);
        } else {
            this.valor = formula;
            this.formula = formula;
        }
    }

    public void updateFormula() {
        if (CeldaCustomViewController.tabla != null) {
            CeldaTableModel modelo = (CeldaTableModel) CeldaCustomViewController.tabla.getModel();

            int rows = CeldaCustomViewController.tabla.getRowCount();
            int columns = CeldaCustomViewController.tabla.getColumnCount();

            for (int i = 0; i < rows; i++) {
                for (int j = 1; j < columns; j++) {
                    Celda celda = modelo.getCeldaAt(i, j);

                    List<Coordenada> coordenadas = celda.getCoordenadasUsadas();

                    if (coordenadas.size() > 0) {

                        for (Coordenada coordenada : coordenadas) {
                            if (coordenada.x != this.row || coordenada.y != this.column) {
                                continue;
                            }
                            celda.updateByChangeValueUsed();
                            modelo.fireTableCellUpdated(i, j);
                        }

                    }
                }
            }

        }
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
