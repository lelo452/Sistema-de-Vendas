package com.algaworks.pedidovenda.security.events;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import com.algaworks.pedidovenda.security.UsuarioSistema;

public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        UsuariosLogados.adicionar((UsuarioSistema) event.getAuthentication().getPrincipal());
    }
}