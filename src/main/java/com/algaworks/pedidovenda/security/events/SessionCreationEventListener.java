package com.algaworks.pedidovenda.security.events;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionCreationEvent;

public class SessionCreationEventListener implements ApplicationListener<SessionCreationEvent> {

    @Override
    public void onApplicationEvent(SessionCreationEvent event) {
        System.out.println("AW ::: SessionCreationEvent: " + event.getSource());
    }
}
