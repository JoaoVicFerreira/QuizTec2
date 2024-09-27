/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.joaovictor.quiz;

import javax.swing.JFrame;

/**
 *
 * @author 08239
 */
public class Quiz {

    public static void main(String[] args) {
        FrmJogo jogo = new FrmJogo();
        jogo.setLocationRelativeTo(jogo);
        jogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jogo.setVisible(true);
    }
}
