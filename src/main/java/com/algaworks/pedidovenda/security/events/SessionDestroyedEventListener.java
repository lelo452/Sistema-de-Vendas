package com.algaworks.pedidovenda.security.events;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;

import com.algaworks.pedidovenda.security.UsuarioSistema;

public class SessionDestroyedEventListener implements ApplicationListener<SessionDestroyedEvent> {

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        for (SecurityContext sc: event.getSecurityContexts()) { // geralmente é somente 1 sc por sessão, mas temos uma lista.
            UsuariosLogados.remover((UsuarioSistema) sc.getAuthentication().getPrincipal());
        }       
    }
}