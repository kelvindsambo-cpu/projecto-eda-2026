package testes;


import gui.DlgNovoPaciente;
import gui.JanelaPrincipal;
import javax.swing.JFrame;
import servicos.GestorAtendimento;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Edson
 */
public class Main {

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new JanelaPrincipal().setVisible(true);
        });
    }
}
