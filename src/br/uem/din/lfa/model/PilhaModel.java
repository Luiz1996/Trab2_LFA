/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.model;

import java.util.Arrays;

/**
 *
 * @author Luiz Flávio
 */
public class PilhaModel {
    final int tamMax;
    String pilha[];
    int topo;
    
    public PilhaModel(){
        tamMax = 100;
        topo = -1;
        pilha = new String[tamMax];
    }
    
    public void empilharElemento(String str){
        if(pilhaCheia()){
            System.out.println("Não foi possível empilhar um novo elemento... Pilha Cheia!");
        }else{
            topo++;
            pilha[topo] = str.trim();
        }
    }
    
    public String desempilharElemento(){
        if(pilhaVazia()){
            return "-";
        }else{
            String elementoDesempilhado = pilha[topo];
            pilha[topo] = "-".trim();
            topo--;
            return elementoDesempilhado;
        }
    }
    
    public boolean pilhaVazia(){
        return (topo == -1);
    }
    
    public boolean pilhaCheia(){
        return (topo == (tamMax - 1));
    }
    
    public String elementoTopo(){
        return pilha[topo];
    }
    
    public void listarPilha() {
        for (int i = topo; i >= 0; i--) {
            if (i == topo && topo > 0) {
                System.out.print("\tTopo -> (" + pilha[topo].trim() + ") -> ");
            } else {
                if (i == topo && topo == 0) {
                    System.out.print("\tTopo -> (" + pilha[topo].trim() + ")\n");
                } else {
                    if (i == 0) {
                        System.out.print("(" + pilha[i].trim() + ")\n");
                    } else {
                        System.out.print("(" + pilha[i].trim() + ") -> ");
                    }
                }
            }
        }
    }
    
    public void inicializarPilha(){
        for(int i = 0; i < tamMax; i++){
            pilha[i] = "-";
        }
    }
    
    public int tamanhoPilha(){
        int tamP = 0;
        for(int i = 0; i < tamMax; i++){
            if(pilha[i].equals("-".trim())){
                return tamP;
            }else{
                tamP++;
            }
        }
        return tamP;
    }
    
    public boolean procurarElementoPilha(String strElemento){
        for(int i = 0; i < tamMax; i++){
            if(pilha[i].trim().equals(strElemento.trim())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.tamMax;
        hash = 53 * hash + Arrays.deepHashCode(this.pilha);
        hash = 53 * hash + this.topo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PilhaModel other = (PilhaModel) obj;
        if (this.tamMax != other.tamMax) {
            return false;
        }
        if (this.topo != other.topo) {
            return false;
        }
        if (!Arrays.deepEquals(this.pilha, other.pilha)) {
            return false;
        }
        return true;
    }
}
