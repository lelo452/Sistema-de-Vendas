package com.algaworks.pedidovenda.security.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.algaworks.pedidovenda.security.UsuarioSistema;

public class UsuariosLogados {
    
    private static final List<UsuarioSistema> USUARIOS = new ArrayList<>();
    
    public static void adicionar(UsuarioSistema usuario) {
        USUARIOS.add(usuario);
    }
    
    public static void remover(UsuarioSistema usuario) {
        USUARIOS.remove(usuario);
    }

    public static List<UsuarioSistema> get() {
        return Collections.unmodifiableList(USUARIOS);
    }
}