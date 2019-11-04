
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.view;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Luiz Flávio
 */
public class ConsoleView {
    //método responsável por limpar a tela a cada iteração do usuário
    public final static void limparConsole() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        } catch (AWTException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao limpar console!\nErro: ".concat(ex.getMessage().trim()), "Falha ao limpar console", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //método responsável por apresentar o menu inicial e receber o valor da opção escolhida
    public static int showMenu() {
        Scanner in = new Scanner(System.in);    
        System.out.println("=========  VALIDADOR DE AUTÔMATOS COM PILHA =========");
        System.out.println("|    01) Inserir alfabeto                           |");
        System.out.println("|    02) Inserir estados                            |");
        System.out.println("|    03) Inserir estado inicial                     |");
        System.out.println("|    04) Inserir base                               |");
        System.out.println("|    05) Inserir alfabeto auxiliar da pilha         |");
        System.out.println("|    06) Inserir transição(ões)                     |");
        System.out.println("|    07) Imprimir descrição formal                  |");
        System.out.println("|    08) Resetar autômato                           |");
        System.out.println("|    09) Validar palavra                            |");
        System.out.println("|    00) Sair                                       |");
        System.out.println("-----------------------------------------------------");
        System.out.print("Opção: "); 
        
        try {
            return in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return -1;
        }         
    }  
}
