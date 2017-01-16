package com.algaworks.pedidovenda.controller;

import java.io.*;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.tools.generic.NumberTool;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import java.util.Properties;

@Named
@RequestScoped
public class EnvioPedidoEmailBean {

    private static final long serialVersionUID = 1L;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void enviarPedido() {

        String from = "your email here";
        final Properties prop = System.getProperties();

        try {
            prop.load(new FileInputStream(new File("mail.properties")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        prop.getProperty("mail.user"), prop.getProperty("mail.passwd"));
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.pedido.getCliente().getEmail()));
            message.setSubject("Pedido " + this.pedido.getId());
            BodyPart body = new MimeBodyPart();

            VelocityEngine ve = new VelocityEngine();
            ve.init();

            Template t = ve.getTemplate("/emails/pedido.vm");
            VelocityContext context = new VelocityContext();
            context.put("pedido", this.pedido);
            context.put("numberTool", new NumberTool());
            context.put("locale", new Locale("pt", "BR"));
            StringWriter out = new StringWriter();
            t.merge( context, out );

            body.setContent(out.toString(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            Transport.send(message);

            FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
