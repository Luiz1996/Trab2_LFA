/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Luiz Flávio
 */
public class AutomatoModel {
    //nesta classe temos o POJO do objeto autômato(atributos private, get/set, contrutor vazio, etc...)
    private List<String> alfabetoAutomato = new ArrayList();
    private List<String> estadosAutomato = new ArrayList();
    private String estadoInicial;
    private String baseAutomato;
    private List<String> alfabetoAuxiliarPilha = new ArrayList();
    private List<TransicaoModel> transicoesAutomato = new ArrayList();
    private PilhaModel pilhaAutomato = new PilhaModel();

    public AutomatoModel(){}
    
    public List<String> getAlfabetoAutomato() {
        return alfabetoAutomato;
    }

    public void setAlfabetoAutomato(List<String> alfabetoAutomato) {
        this.alfabetoAutomato = alfabetoAutomato;
    }

    public List<String> getEstadosAutomato() {
        return estadosAutomato;
    }

    public void setEstadosAutomato(List<String> estadosAutomato) {
        this.estadosAutomato = estadosAutomato;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public String getBaseAutomato() {
        return baseAutomato;
    }

    public void setBaseAutomato(String baseAutomato) {
        this.baseAutomato = baseAutomato;
    }

    public List<String> getAlfabetoAuxiliarPilha() {
        return alfabetoAuxiliarPilha;
    }

    public void setAlfabetoAuxiliarPilha(List<String> alfabetoAuxiliarPilha) {
        this.alfabetoAuxiliarPilha = alfabetoAuxiliarPilha;
    }

    public List<TransicaoModel> getTransicoesAutomato() {
        return transicoesAutomato;
    }

    public void setTransicoesAutomato(List<TransicaoModel> transicoesAutomato) {
        this.transicoesAutomato = transicoesAutomato;
    } 

    public PilhaModel getPilhaAutomato() {
        return pilhaAutomato;
    }

    public void setPilhaAutomato(PilhaModel pilhaAutomato) {
        this.pilhaAutomato = pilhaAutomato;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.alfabetoAutomato);
        hash = 97 * hash + Objects.hashCode(this.estadosAutomato);
        hash = 97 * hash + Objects.hashCode(this.estadoInicial);
        hash = 97 * hash + Objects.hashCode(this.baseAutomato);
        hash = 97 * hash + Objects.hashCode(this.alfabetoAuxiliarPilha);
        hash = 97 * hash + Objects.hashCode(this.transicoesAutomato);
        hash = 97 * hash + Objects.hashCode(this.pilhaAutomato);
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
        final AutomatoModel other = (AutomatoModel) obj;
        if (!Objects.equals(this.estadoInicial, other.estadoInicial)) {
            return false;
        }
        if (!Objects.equals(this.baseAutomato, other.baseAutomato)) {
            return false;
        }
        if (!Objects.equals(this.alfabetoAutomato, other.alfabetoAutomato)) {
            return false;
        }
        if (!Objects.equals(this.estadosAutomato, other.estadosAutomato)) {
            return false;
        }
        if (!Objects.equals(this.alfabetoAuxiliarPilha, other.alfabetoAuxiliarPilha)) {
            return false;
        }
        if (!Objects.equals(this.transicoesAutomato, other.transicoesAutomato)) {
            return false;
        }
        if (!Objects.equals(this.pilhaAutomato, other.pilhaAutomato)) {
            return false;
        }
        return true;
    }
}
