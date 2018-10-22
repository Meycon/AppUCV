package com.catolica.salesianoapp.ucv.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Meycon Augusto on 02/02/2018.
 */

public class SendMail extends AsyncTask<Void,Void,Void> {


    //Declaração das variaveis
    private Context context;
    private Session session;

    //Informação para enviar o email
    private String email;
    private String subject;
    private String message;

    //ProgressDialog para mostrar ao enviar a mensagem
    private ProgressDialog progressDialog;

    //Construor
    public SendMail(Context context, String email, String subject, String message){
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Mostrar o progress Dialog enquanto estiver enviando a mensagem
        progressDialog = ProgressDialog.show(context,"Enviando mensagem","Por favor aguarde...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Finaliza o ProgressDialog
        progressDialog.dismiss();
        //Mostra que a mensagem foi enviada
        Toast.makeText(context,"Mensagem enviada", Toast.LENGTH_LONG).show();
        //Finaliza a ActivityFaleConosco
        ((Activity)context).finish();

    }

    @Override
    protected Void doInBackground(Void... params) {
        //Cria as propriedades
        Properties props = new Properties();

        //Configurando as propriedades do Email
        //Se as configurações não for GMAIL é necessário alterar
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Cria uma nova sessão
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Autentica com a senha
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Config.EMAIL, Config.SENHA);
                    }
                });

        try {
            //Cria o objeto MimeMessage
            MimeMessage mm = new MimeMessage(session);

            //Configurações de endereço de email de quem está mandando sender address
            mm.setFrom(new InternetAddress(Config.EMAIL));
            //Adicionando o Para
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adicionando o assunto
            mm.setSubject(subject);
            //Adicionando a mensagem
            mm.setText(message);

            //Enviando o Email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

