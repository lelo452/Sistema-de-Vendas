package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.algaworks.pedidovenda.security.UsuarioSistema;

//nunca foi usado.

@Named
@ViewScoped
public class UsuariosLogadosBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    
    private List<UsuarioSistema> usuarios;
    
    @PostConstruct
    void postConstruct() {
        
    }
    
    public List<UsuarioSistema> getUsuarios() {
        return usuarios;
    }

}
