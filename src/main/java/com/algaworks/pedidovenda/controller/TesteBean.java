package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TesteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List propriedade = new ArrayList<>();
    
    public List getPropriedade() {
        return propriedade;
    }
}
