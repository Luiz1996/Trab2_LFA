/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.controller;

import br.uem.din.lfa.model.AutomatoModel;
import br.uem.din.lfa.view.ConsoleView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Luiz Flávio
 */
public class MainController {
    //A classe MainController tem como único objeto controlar o fluxo do programa de acordo com a opção escolhida no menu
    //Esse controle de fluxo é feito usando a estrutura switch/case
    public static void main(String[] args) {
        AutomatoModel autM = new AutomatoModel();
        AutomatoController autC = new AutomatoController();
        List<String> alfabetoAuxiliarPilha = new ArrayList();
        autM.setEstadoInicial("".trim());
        autM.setBaseAutomato("".trim());

        int opcao = ConsoleView.showMenu();
        while (opcao != 0) {
            ConsoleView.limparConsole();
            switch (opcao) {
                case 1:
                    //limpando as informações anteriores
                    autM.setAlfabetoAutomato(new ArrayList());
                    
                    //inserindo novas informações
                    autM.setAlfabetoAutomato(autC.inserirAlfabeto(autM));
                    break;
                case 2:
                    //limpando as informações anteriores
                    autM.setEstadosAutomato(new ArrayList());
                    autM.setEstadoInicial("".trim());
                    
                    //inserindo as novas informações
                    autM.setEstadosAutomato(autC.inserirEstados(autM));
                    break;
                case 3:
                    autC.inserirEstadoInicial(autM);
                    break;
                case 4:
                    String baseP = autC.inserirBasePilha(autM);
                    if (!baseP.trim().equals("")) {
                        //validando se ja havia uma base cadastrada anteriormente, se tiver é necessário limpar no alfabeto auxiliar pilha também e depois inserir a nova base
                        if (!autM.getBaseAutomato().trim().equals("")) {
                            for (int i = 0; i < autM.getAlfabetoAuxiliarPilha().size(); i++) {
                                if (autM.getAlfabetoAuxiliarPilha().get(i).trim().equals(autM.getBaseAutomato().trim())) {
                                    autM.getAlfabetoAuxiliarPilha().remove(i);
                                }
                            }
                        }

                        //inserindo a base
                        autM.setBaseAutomato(baseP.trim());

                        //ja aproveitamos para inserir a base no alfabetoAuxiliarPilha do autômato de maneira ordenada
                        alfabetoAuxiliarPilha.add(baseP.trim());

                        //ordenando lista
                        Comparator<? super String> c = null;
                        alfabetoAuxiliarPilha.sort(c);
                        autM.setAlfabetoAuxiliarPilha(alfabetoAuxiliarPilha);
                    }
                    break;
                case 5:
                    //ao inserir é neessário limpar as informações que tinha antes e reinserir a base(se já estiver previamente cadastrada)
                    alfabetoAuxiliarPilha = new ArrayList();
                    if (!autM.getBaseAutomato().trim().equals("")) {
                        alfabetoAuxiliarPilha.add(autM.getBaseAutomato().trim());
                    }

                    //obtendo novas informações
                    autM.setAlfabetoAuxiliarPilha(autC.inserirAlfabetoAuxiliarPilha(alfabetoAuxiliarPilha));
                    break;
                case 6:
                    //ao inserir as novas informações, as anteriores são limpadas
                    autM.setTransicoesAutomato(new ArrayList());
                    
                    //inserindo as novas informações
                    autM.setTransicoesAutomato(autC.inserirTransições(autM));
                    break;
                case 7:
                    autC.imprimirDescricaoFormal(autM);
                    break;
                case 8:
                    autM = new AutomatoModel();
                    alfabetoAuxiliarPilha = new ArrayList();
                    autM.setEstadoInicial("".trim());
                    autM.setBaseAutomato("".trim());
                    System.out.println("Autômato resetado com sucesso.\n\nÉ necessário informar outra descrição formal para continuar...");
                    break;
                case 9:
                    System.out.println(autC.validarPalavraNoAutomatoComPilha(autM));
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            opcao = ConsoleView.showMenu();
            System.gc();
        }
    }
}
