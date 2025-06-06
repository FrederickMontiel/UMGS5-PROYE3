/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gt.edu.miumg.fmontiel.system;

import javax.swing.JFrame;

import gt.edu.miumg.fmontiel.views.MainView;

/**
 *
 * @author Trabajo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainView mainView = new MainView();

        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainView.setLocationRelativeTo(null);
        mainView.setTitle("MiniExcel");

        mainView.setVisible(true);
    }

}
