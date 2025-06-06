/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.fmontiel.model;

import java.util.List;

import gt.edu.miumg.fmontiel.controllers.CeldaController;

/**
 *
 * @author Trabajo
 */
public class Celda {
    private CeldaController celdaController;

    public Celda(String formula, int row, int column) {
        this.celdaController = new CeldaController(
                row, column + 1);

        this.celdaController.setFormula(formula);
    }

    public String getFormula() {
        return this.celdaController.getFormula();
    }

    public void setFormula(String formula) {
        this.celdaController.setFormula(formula);
    }

    public String getValor() {
        return this.celdaController.getValor();
    }

    public void setValor(String valor) {
        this.celdaController.setValor(valor);
    }

    public List<Coordenada> getCoordenadasUsadas() {
        return this.celdaController.getCoordenadasUsadas();
    }

    public void updateByChangeValueUsed() {
        this.celdaController.updateByChangeValueUsed(this.celdaController.getFormula());

        System.out.println("Actualizando celda: " + this.celdaController.getFormula());
        System.out.println("Valor: " + this.celdaController.getValor());
    }
}