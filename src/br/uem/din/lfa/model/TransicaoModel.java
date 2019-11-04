/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.model;

import java.util.Objects;

/**
 *
 * @author Luiz Flávio
 */
public class TransicaoModel {
    //nesta classe temos o POJO do objeto transição(atributos private, construtor vazio, get/set, etc...)
    private String estadoSaida;
    private String simboloTransicao;
    private String simboloSaiDaPilha;
    private String estadoChegada;
    private String simboloEntraNaPilha;
    
    public TransicaoModel(){}

    public String getEstadoSaida() {
        return estadoSaida;
    }

    public void setEstadoSaida(String estadoSaida) {
        this.estadoSaida = estadoSaida;
    }

    public String getSimboloTransicao() {
        return simboloTransicao;
    }

    public void setSimboloTransicao(String simboloTransicao) {
        this.simboloTransicao = simboloTransicao;
    }

    public String getSimboloSaiDaPilha() {
        return simboloSaiDaPilha;
    }

    public void setSimboloSaiDaPilha(String simboloSaiDaPilha) {
        this.simboloSaiDaPilha = simboloSaiDaPilha;
    }

    public String getEstadoChegada() {
        return estadoChegada;
    }

    public void setEstadoChegada(String estadoChegada) {
        this.estadoChegada = estadoChegada;
    }

    public String getSimboloEntraNaPilha() {
        return simboloEntraNaPilha;
    }

    public void setSimboloEntraNaPilha(String simboloEntraNaPilha) {
        this.simboloEntraNaPilha = simboloEntraNaPilha;
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
        final TransicaoModel other = (TransicaoModel) obj;
        if (!Objects.equals(this.estadoSaida, other.estadoSaida)) {
            return false;
        }
        if (!Objects.equals(this.simboloTransicao, other.simboloTransicao)) {
            return false;
        }
        if (!Objects.equals(this.simboloSaiDaPilha, other.simboloSaiDaPilha)) {
            return false;
        }
        if (!Objects.equals(this.estadoChegada, other.estadoChegada)) {
            return false;
        }
        if (!Objects.equals(this.simboloEntraNaPilha, other.simboloEntraNaPilha)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.estadoSaida);
        hash = 71 * hash + Objects.hashCode(this.simboloTransicao);
        hash = 71 * hash + Objects.hashCode(this.simboloSaiDaPilha);
        hash = 71 * hash + Objects.hashCode(this.estadoChegada);
        hash = 71 * hash + Objects.hashCode(this.simboloEntraNaPilha);
        return hash;
    }
}
