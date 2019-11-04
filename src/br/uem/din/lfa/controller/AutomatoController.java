/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.controller;

import br.uem.din.lfa.model.AutomatoModel;
import br.uem.din.lfa.model.PilhaModel;
import br.uem.din.lfa.model.TransicaoModel;
import br.uem.din.lfa.view.ConsoleView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Luiz Flávio
 */
public class AutomatoController {
    Scanner in = new Scanner(System.in);

    //método responsável por preencher o alfabeto do autômato
    public List<String> inserirAlfabeto(AutomatoModel autM) {
        in = new Scanner(System.in);
        System.out.print("Informe a quantidade de símbolos: ");
        int qtdeSimbolos = 0;

        //uso de try/catch para tratativas de exceções
        try {
            qtdeSimbolos = in.nextInt();
        } catch (InputMismatchException e) {
            qtdeSimbolos = -1; //situação inválida (usuário digitou qualquer coisa, exceto um valor inteiro)
        }

        //limpando tela
        ConsoleView.limparConsole();

        //valida se a quantidade de simbolos é válido
        if (qtdeSimbolos <= 0) {
            System.out.println("Quantidade inválida de símbolos.\n\nAs informações anteriores foram limpadas e a inserção de símbolos abortada!");
        } else {
            String strPalavraTemp;
            List<String> palavrasTemp = new ArrayList();

            //iniciando a obtenção dos símbolos do alfabeto
            for (int i = 1; i <= qtdeSimbolos; i++) {
                System.out.print("Informe o " + i + "º símbolo: ");
                strPalavraTemp = in.next();

                //limpa tela
                ConsoleView.limparConsole();

                if (strPalavraTemp.trim().length() == 1) { //valida se o simbolo tem tamanho exatamente = 1, apesar de ser String... Funciona como char
                    if (!simboloAlfabetoRepetido(strPalavraTemp.trim(), palavrasTemp)) {//valida se o simbolo está sendo inserido de forma repetida
                        palavrasTemp.add(strPalavraTemp.trim());
                    } else {
                        System.out.println("O símbolos '" + strPalavraTemp.trim() + "' já foi inserido no alfabeto.\n");
                        i--;
                    }
                } else {
                    System.out.println("O tamanho de cada símbolos deve ser exatamente 1.\n");
                    i--;
                }
            }
            System.out.println("O(s) símbolo(s) foi/foram inserido(s) com sucesso.");
            return palavrasTemp;
        }
        return new ArrayList<>();
    }

    //método responsável por inserir os estados desejados
    public List<String> inserirEstados(AutomatoModel autM) {
        in = new Scanner(System.in);
        System.out.print("Informe a quantidade de estados: ");
        int qtdeEstados = 0;

        //uso de try/catch para tratativas de exceções
        try {
            qtdeEstados = in.nextInt();
        } catch (InputMismatchException e) {
            qtdeEstados = -1; //situação inválida (usuário digitou qualquer coisa, exceto um valor inteiro)
        }

        //limpando a tela
        ConsoleView.limparConsole();

        if (qtdeEstados <= 0) { //valida se a quantidade de estados informado é inválido
            System.out.println("Quantidade inválida de estados.\n\nO conjunto de estados e o estado inicial anteriores foram limpados e a inserção de estados foi abortada!");
        } else {
            String strEstadoTemp;
            List<String> estadosTemp = new ArrayList();

            for (int i = 1; i <= qtdeEstados; i++) {
                System.out.print("Informe o " + i + "º estado: ");
                strEstadoTemp = in.next();

                //limpa tela
                ConsoleView.limparConsole();

                if (strEstadoTemp.trim().length() == 1) { //valida se o estado tem tamanho exatamente = 1, apesar de ser String... Funciona como char
                    if (!estadoRepetido(strEstadoTemp.trim(), estadosTemp)) { //valida se está sendo inserido um estado repetido
                        estadosTemp.add(strEstadoTemp.trim());
                    } else {
                        System.out.println("O estado '" + strEstadoTemp.trim() + "' já foi inserido no conjunto de estados.\n");
                        i--;
                    }
                } else {
                    System.out.println("O tamanho de cada estado deve ser exatamente 1.\n");
                    i--;
                }
            }
            System.out.println("Os estados foram inseridas com sucesso.");
            return estadosTemp;
        }
        return new ArrayList<>();
    }

    //método para inserção de estado inicial
    public void inserirEstadoInicial(AutomatoModel autM) {
        //valida se os estados já foram cadastrados
        if (autM.getEstadosAutomato().size() > 0) {
            System.out.print("Insira o estado inicial: ");
            String strEstadoInicial = in.next();

            //limpa tela
            ConsoleView.limparConsole();

            if (estadoRepetido(strEstadoInicial.trim(), autM.getEstadosAutomato())) { //valida se o estado inicial informado existe no conjunto de estados cadastrados
                autM.setEstadoInicial(strEstadoInicial.trim()); //seta estado inicial
                System.out.println("Estado inicial inserido com sucesso.");
            } else {
                System.out.println("O estado informado não faz parte do conjunto de estados.\n\n Inserção de estado inicial abortada!");
            }
        } else {
            System.out.println("Nenhum estado foi cadastro neste autômato.");
        }
    }

    //método responsável por inserir a base da pilha
    public String inserirBasePilha(AutomatoModel autM) {
        System.out.print("Insira a base da pilha: ");
        String basePilha = in.next();

        //limpa tela
        ConsoleView.limparConsole();

        //validando tamanho da pilha e dando retorno ao usuário
        if (basePilha.trim().length() != 1) {
            System.out.println("A base da pilha deve ter tamanho igual a 1.\n\nInserção da base da pilha foi abortada!");
            basePilha = "";
        } else {
            //validando se a base informada já foi inserida no conjunto de alfabeto do autômato
            if(simboloAlfabetoRepetido(basePilha.trim(), autM.getAlfabetoAutomato())){
                System.out.println("O símbolo '"+ basePilha.trim() +"' já está contido no conjunto de alfabeto do autômato.\n\nInserção da base da pilha foi abortada!");
                basePilha = "";
            }else{
                //validando se a base informada já foi inserida no conjunto de alfabeto auxiliar da pilha
                if(simboloAlfabetoAuxiliarPilhaRepetido(basePilha.trim(), autM.getAlfabetoAuxiliarPilha())){
                    System.out.println("O símbolo '"+ basePilha.trim() +"' já está contido no conjunto de alfabeto auxiliar da pilha.\n\nOu seja, a base informada já faz parte do conjunto de transições e/ou conjunto de alfabeto auxiliar da pilha.\n\nInserção da base da pilha foi abortada!");
                    basePilha = "";
                }else{
                    System.out.println("Base da pilha foi inserida com sucesso!");
                }
            }
        }
        return basePilha.trim();
    }
    
    public List<String> inserirAlfabetoAuxiliarPilha(List<String> alfabetoAuxiliarPilha){
        in = new Scanner(System.in);
        System.out.print("Informe a quantidade de símbolos que deseja inserir: ");
        int qtdeSimbolos = 0;

        //uso de try/catch para tratativas de exceções
        try {
            qtdeSimbolos = in.nextInt();
        } catch (InputMismatchException e) {
            qtdeSimbolos = -1; //situação inválida (usuário digitou qualquer coisa, exceto um valor inteiro)
        }

        //limpa tela
        ConsoleView.limparConsole();
        
        //validando qtdeSimbolos informado
        if(qtdeSimbolos <= 0){
            System.out.println("A quantidade de símbolos informada é inválida.\n\nAs informações anteriores foram limpadas e a inserção de alfabeto auxiliar da pilha foi abortada!");
        }else{
            
            //obtendo todos os símbolos
            for(int i = 1; i <= qtdeSimbolos; i++){
                System.out.print("Informe o " + i + "º símbolo: ");
                String strAlfabetoAuxP = in.next();
                
                //limpa tela
                ConsoleView.limparConsole();
                
                //validando quantidade de caracteres do simbolo informado
                if(strAlfabetoAuxP.trim().length() > 1){
                    i--;
                    System.out.println("O símbolo do alfabeto auxiliar da pilha deve ter tamanho igual a 1.\n");
                }else{
                    //validando símbolo repetido
                    if(simboloAlfabetoAuxiliarPilhaRepetido(strAlfabetoAuxP, alfabetoAuxiliarPilha)){
                        i--;
                        System.out.println("O símbolo '" + strAlfabetoAuxP + "' já foi inserido no conjunto de alfabeto auxiliar da pilha.\n");
                    }else{
                        alfabetoAuxiliarPilha.add(strAlfabetoAuxP); 
                        
                        //ordenando lista que contém os elementos
                        Comparator<? super String> c = null;
                        alfabetoAuxiliarPilha.sort(c);
                    }
                } 
            }
            System.out.println("O alfabeto auxiliar da pilha foi inserido com sucesso.");
        }
        return alfabetoAuxiliarPilha;
    }
    
    public List<TransicaoModel> inserirTransições(AutomatoModel autM) {
        if(!validaDadosPreenchidos(autM)){
            System.out.println("Alguma informação não foi devidamente preenchida.\n\nAção: Valide o conjunto de estados, estado inicial, base, alfabeto do autômato e alfabeto auxiliar da pilha.\n\nUse a opção '7' para visualizar o que não foi preenchido...");
            return new ArrayList();
        }
        
        in = new Scanner(System.in);
        System.out.print("Informe a quantidade de transições que deseja inserir: ");
        int qtdeTransicoes = 0;

        //uso de try/catch para tratativas de exceções
        try {
            qtdeTransicoes = in.nextInt();
        } catch (InputMismatchException e) {
            qtdeTransicoes = -1; //situação inválida (usuário digitou qualquer coisa, exceto um valor inteiro)
        }
        
        //declarando objetos auxiliares
        TransicaoModel transicaoAux = new TransicaoModel();
        List<TransicaoModel> transicoesTemp = new ArrayList();

        //limpa tela
        ConsoleView.limparConsole();
        
        if(qtdeTransicoes <= 0){
            System.out.println("A quantidade de transições informada é inválida.\n\nAs informações anteriores foram limpadas e a inserção de transições foi abortada!");
            return new ArrayList();
        }else{
            for(int  i = 1; i <= qtdeTransicoes; i++){
                System.out.println("***** Informe os dados da " + i + "ª transição *****\n\nUse o *(asterisco) para representar o vazio...\n");
            
                //obtendo as informações do usuário
                System.out.print("Informe o estado de saída...........: ");
                transicaoAux.setEstadoSaida(in.next().trim());
                
                System.out.print("Informe o símbolo da transição......: ");
                transicaoAux.setSimboloTransicao(in.next().trim());
                
                System.out.print("Informe o símbolo removido da pilha.: ");
                transicaoAux.setSimboloSaiDaPilha(in.next().trim());
                
                System.out.print("Informe o estado de chegada.........: ");
                transicaoAux.setEstadoChegada(in.next().trim());
                
                System.out.print("Informe o símbolo que entra na pilha: ");
                transicaoAux.setSimboloEntraNaPilha(in.next().trim());
                
                //limpando a tela
                ConsoleView.limparConsole();
                
                if(!estadoRepetido(transicaoAux.getEstadoSaida(), autM.getEstadosAutomato())){
                    i--;
                    System.out.println("O estado de saída '" + transicaoAux.getEstadoSaida() + "' informado não existe no conjunto de estados.\n");
                }else{
                    if(!simboloAlfabetoRepetido(transicaoAux.getSimboloTransicao(), autM.getAlfabetoAutomato()) && !transicaoAux.getSimboloTransicao().trim().equals("*")){
                        i--;
                        System.out.println("O símbolo de transição '" + transicaoAux.getSimboloTransicao() + "' informado não existe no conjuno de alfabeto do autômato.\n");
                    }else{
                        if(!estadoRepetido(transicaoAux.getEstadoChegada(), autM.getEstadosAutomato())){
                            i--;
                            System.out.println("O estado de saída '" + transicaoAux.getEstadoSaida() + "' informado não existe no conjunto de estados.\n");
                        }else{
                            if(!simboloAlfabetoRepetido(transicaoAux.getSimboloSaiDaPilha(), autM.getAlfabetoAutomato()) &&
                               !simboloAlfabetoAuxiliarPilhaRepetido(transicaoAux.getSimboloSaiDaPilha(), autM.getAlfabetoAuxiliarPilha()) &&
                               (!existeElementosNoAlfabetoAuxiliarPilha(transicaoAux.getSimboloSaiDaPilha(), autM.getAlfabetoAuxiliarPilha()) && !transicaoAux.getSimboloSaiDaPilha().trim().equals("*"))){
                                i--;
                                System.out.println("O símbolo removido da pilha '" + transicaoAux.getSimboloSaiDaPilha() + "' informado não existe no conjunto de alfabeto do autômato e nem no conjunto de alfabeto auxiliar da pilha.\n");
                            }else{
                                if(!simboloAlfabetoRepetido(transicaoAux.getSimboloEntraNaPilha(), autM.getAlfabetoAutomato()) &&
                                   !simboloAlfabetoAuxiliarPilhaRepetido(transicaoAux.getSimboloEntraNaPilha(), autM.getAlfabetoAuxiliarPilha()) &&
                                   (!existeElementosNoAlfabetoAuxiliarPilha(transicaoAux.getSimboloSaiDaPilha(), autM.getAlfabetoAuxiliarPilha()) && !transicaoAux.getSimboloSaiDaPilha().trim().equals("*"))){
                                    i--;
                                    System.out.println("O símbolo que entra na pilha '" + transicaoAux.getSimboloEntraNaPilha() + "' informado não existe no conjunto de alfabeto do autômato e nem no conjunto de alfabeto auxiliar da pilha.\n");
                                }else{
                                    if(transicaoRepetida(transicaoAux, transicoesTemp)){
                                        i--;
                                        System.out.println("A transição informada já existe no conjunto de transições do autômato.\n");
                                    }else{
                                        transicoesTemp.add(transicaoAux);
                                    }
                                }
                            }
                        }      
                    }
                }
                transicaoAux = new TransicaoModel();
            }  
        }
        System.out.println("As transições foram inseridas com sucesso!");
        return transicoesTemp;
    }

    //método responsável por listar a descrição formal do autômato
    public void imprimirDescricaoFormal(AutomatoModel autM) {
        System.out.println(" -------------- DESCRIÇÃO FORMAL - AP --------------- ");
        System.out.println("    Σ: " + autM.getAlfabetoAutomato().toString());
        System.out.println("    E: " + autM.getEstadosAutomato().toString());
        System.out.println("    i: " + autM.getEstadoInicial());
        System.out.println(" Base: " + autM.getBaseAutomato());
        System.out.println("    Γ: " + autM.getAlfabetoAuxiliarPilha().toString());
        
        //listando as informações presentes na pilha
        System.out.println("Pilha: ");
        if(autM.getPilhaAutomato().pilhaVazia()){
            System.out.println("\t*** Nenhum elemento na pilha ***");
        }else{
            autM.getPilhaAutomato().listarPilha();
        }
        
        //listando as informações presentes na transição
        System.out.println("    δ: ");
        if (autM.getTransicoesAutomato().size() <= 0) {
            System.out.println("\t*** Nenhuma transição foi cadastrada ***");
        } else {
            for (int i = 0; i < autM.getTransicoesAutomato().size(); i++) {
                //imprimindo os dados com a formatação adotada em aula
                System.out.println("\t(" + autM.getTransicoesAutomato().get(i).getEstadoSaida()         + ", "
                                         + autM.getTransicoesAutomato().get(i).getSimboloTransicao()    + ", "
                                         + autM.getTransicoesAutomato().get(i).getSimboloSaiDaPilha()   + ") = ("
                                         + autM.getTransicoesAutomato().get(i).getEstadoChegada()       + ", "
                                         + autM.getTransicoesAutomato().get(i).getSimboloEntraNaPilha() + ")");
            }
        }
    }
    
    //método responsável por realizar a validação da palavra informada na descrição formal previamente preenchida
    public String validarPalavraNoAutomatoComPilha(AutomatoModel autM){
        if(!validaDadosPreenchidos(autM) || autM.getTransicoesAutomato().size() <= 0){
            return "Alguma informação não foi devidamente preenchida.\n\nAção: Valide o conjunto de estados, estado inicial, base, alfabeto do autômato, alfabeto auxiliar da pilha e conjunto de transições.\n\nUse a opção '7' para visualizar o que não foi preenchido...";
        }
        
        //empilhando a base na pilha
        PilhaModel pilhaAux = new PilhaModel();
        pilhaAux.empilharElemento(autM.getBaseAutomato().trim());
        autM.setPilhaAutomato(pilhaAux);
        
        in = new Scanner(System.in);
        System.out.print("Informe a palavra a ser validada na linguagem fornecida: ");
        String palavraAValidar = in.next().trim();

        //limpa tela
        ConsoleView.limparConsole();
        
        //variavel auxiliar usada durante as validações
        TransicaoModel transAux = new TransicaoModel();
        
        //setando estado inicial de partida para realizar as validações
        transAux.setEstadoSaida(autM.getEstadoInicial().trim());
        
        //percorrendo todos os simbolos da palavra e buscando transições para cada estados de saida, símbolo e elemento do topo da lista encontrado
        for (int i = 0; i < palavraAValidar.length(); i++) {
            //obtendo cada caracter da palavra a ser validada
            char caracterSimbolo = palavraAValidar.charAt(i);
            transAux.setSimboloTransicao(Character.toString(caracterSimbolo).trim());

            //se não existir transição pro estado atuale pro simbolo atual, então a palavra não pertence
            if (!validaTransicaoEAtualizaPilha(transAux, autM, i, (palavraAValidar.length() - 1))) {
                return "A palavra '" + palavraAValidar + "' NÃO PERTENCE à linguagem!\n\nNão foi encontrado uma transição que fosse possível dar continuidade na validação da palavra.";
            }
        }
        
        //validando se após todas as interações a pilha permaneceu vazia
        if(autM.getPilhaAutomato().pilhaVazia()){
            return "A palavra '" + palavraAValidar + "' PERTENCE à linguagem!";
        }else{
            autM.getPilhaAutomato().listarPilha();
            return "A palavra '" + palavraAValidar + "' NÃO PERTENCE à linguagem!\n\nA palavra foi totalmente validada, mas a fila não está vazia.";
        }
    }
    
    //valida se existe a transição ao validar uma palavra no autômato
    private boolean validaTransicaoEAtualizaPilha(TransicaoModel transAtual, AutomatoModel autM, int iteracaoAtual, int tamPalavra) {
        String elementoDesempilhado = autM.getPilhaAutomato().desempilharElemento().trim();
        
        for(int i = 0; i < autM.getTransicoesAutomato().size(); i++){
            //verificando se há alguma transição que satisfaça as condições
            if  (
                    autM.getTransicoesAutomato().get(i).getEstadoSaida().trim().equals(transAtual.getEstadoSaida().trim())           &&
                    autM.getTransicoesAutomato().get(i).getSimboloTransicao().trim().equals(transAtual.getSimboloTransicao().trim()) &&
                    autM.getTransicoesAutomato().get(i).getSimboloSaiDaPilha().trim().equals(elementoDesempilhado.trim())
                    
                ){
                
                //atualizando o estado de saída
                transAtual.setEstadoSaida(autM.getTransicoesAutomato().get(i).getEstadoChegada().trim());
                
                //esse if é responsável por empilhar ou não um novo elemento na pilha
                //no entanto, se a iteracaoAtual == tamPalavra, entendemos que estamos validando o último simbolo da palavra
                //por isso, não é necessário empilhar mais elementos, pois a última validação é justamente se a pilha está vazia
                //e também, se for identificado que o novo elemento a ser empilhado é o vazio(*), então não é necessário empilhar
                if(iteracaoAtual != tamPalavra && !autM.getTransicoesAutomato().get(i).getSimboloEntraNaPilha().trim().equals("*".trim())){
                    String simbolosASeremEmpilhados = autM.getTransicoesAutomato().get(i).getSimboloEntraNaPilha().trim();
                    
                    //realizando o empilhamento
                    for(int j = simbolosASeremEmpilhados.length(); j > 0; j--){
                        //obtendo cada caracter que deve ser empilhado na pilha
                        char caracterASerEmpilhado = simbolosASeremEmpilhados.charAt(j - 1);
                        
                        //atualizando o elemento do topo da pilha
                        autM.getPilhaAutomato().empilharElemento(Character.toString(caracterASerEmpilhado).trim());
                    }      
                }
                return true;
            }
        }
        return false;
    }

    //método auxiliar que valida se o símbolo informado existe no alfabeto
    public boolean simboloAlfabetoRepetido(String strSimboloTemp, List<String> alfabetoAutomato) {
        return alfabetoAutomato.stream().anyMatch((palavrasTemp1) -> (palavrasTemp1.equals(strSimboloTemp.trim())));
    }
    
    //método auxiliar que valida se o símbolo informado existe no alfabeto auxiliar da pilha
    public boolean simboloAlfabetoAuxiliarPilhaRepetido(String strSimboloTemp, List<String> alfabetoAuxiliarPilha) {
        return alfabetoAuxiliarPilha.stream().anyMatch((palavrasTemp1) -> (palavrasTemp1.equals(strSimboloTemp.trim())));
    }

    //método auxiliar que valida se o estado informado existe no conjunto de estados
    public boolean estadoRepetido(String strEstadoTemp, List<String> estadosTemp) {
        return estadosTemp.stream().anyMatch((estadosTemp1) -> (estadosTemp1.equals(strEstadoTemp.trim())));
    }
    
    //método auxiliar que valdia se a transição informada já existe no conjunto de transições
    public boolean transicaoRepetida(TransicaoModel transAux, List<TransicaoModel> transicoesAutomato){
        for(int i = 0; i < transicoesAutomato.size(); i++){
            if(transAux.equals(transicoesAutomato.get(i))){
                return true;
            }
        }
        return false;
    }
    
    public boolean existeElementosNoAlfabetoAuxiliarPilha(String elementoPilha, List<String> alfabetoAuxiliarPilha){
        for(int i = 0; i < elementoPilha.length(); i++){
            String strAux = String.valueOf(elementoPilha.charAt(i)).trim();
            if(!simboloAlfabetoAuxiliarPilhaRepetido(strAux, alfabetoAuxiliarPilha)){
                return false;
            }
        }
        return true;
    }
    
    public boolean validaDadosPreenchidos(AutomatoModel autM){
        return autM.getAlfabetoAutomato().size() > 0       &&
                autM.getAlfabetoAuxiliarPilha().size() > 0 &&
                autM.getEstadosAutomato().size() > 0       &&
                !autM.getBaseAutomato().trim().equals("")  &&
                !autM.getEstadoInicial().trim().equals("");
    }
}
