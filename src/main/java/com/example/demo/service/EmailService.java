package com.example.demo.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.jboss.logging.Logger;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailService {

    final static Logger logger = Logger.getLogger(EmailService.class);
    public int sendEmailConfirmation(String subject, String to, String code, String firstName, String lastName) {
        int response = 0;
        // Recipient's email ID needs to be mentioned.

        // Sender's email ID needs to be mentioned
        String from = "haythem.belhadj@esprit.tn";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("haythem.belhadj@esprit.tn", "inrribioeahcpjks");
            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(new InternetAddress(to));
            helper.setFrom(new InternetAddress(from));
            helper.setSubject("test");
            helper.setText(geContentFromTemplate(code, firstName, lastName), true);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);
            //String contente = geContentFromTemplate();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //System.out.println(contente);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            // Now set the actual message
            //message.setText("<h1></h1>");

            System.out.println("sending...");
            // Send message
            MimeMessage content = helper.getMimeMessage();
            Object oldContent = content.getContent();
            content.setContent(oldContent, "text/plain; charset=UTF-8");
            Transport.send(content);

            System.out.println("Success !!! ");
            response = 200;
        } catch (MessagingException mex) {
            response = 400;
            logger.error(mex.getMessage());
        } finally {
            return response;
        }
    }

    public int sendArchitectConfirmation(String subject, String to, String code, String firstName, String lastName) {
        int response = 0;
        // Recipient's email ID needs to be mentioned.

        // Sender's email ID needs to be mentioned
        String from = "haythem.belhadj@esprit.tn";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("haythem.belhadj@esprit.tn", "inrribioeahcpjks");
            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(new InternetAddress(to));
            helper.setFrom(new InternetAddress(from));
            helper.setSubject("test");
            helper.setText(geContentArchitectFromTemplate(code, firstName, lastName), true);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);
            //String contente = geContentFromTemplate();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //System.out.println(contente);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            // Now set the actual message
            //message.setText("<h1></h1>");

            System.out.println("sending...");
            // Send message
            MimeMessage content = helper.getMimeMessage();
            Object oldContent = content.getContent();
            content.setContent(oldContent, "text/plain; charset=UTF-8");
            Transport.send(content);

            System.out.println("Success !!! ");
            response = 200;
        } catch (MessagingException mex) {
            response = 400;
            logger.error(mex.getMessage());
        } finally {
            return response;
        }
    }

    public int sendPasswordRenew(String subject, String to, String code, String firstName, String lastName) {
        int response = 0;
        // Recipient's email ID needs to be mentioned.

        // Sender's email ID needs to be mentioned
        String from = "haythem.belhadj@esprit.tn";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("haythem.belhadj@esprit.tn", "inrribioeahcpjks");
            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(new InternetAddress(to));
            helper.setFrom(new InternetAddress(from));
            helper.setSubject("test");
            helper.setText(getContentPassword(code, firstName, lastName), true);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);
            //String contente = geContentFromTemplate();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //System.out.println(contente);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            // Now set the actual message
            //message.setText("<h1></h1>");

            System.out.println("sending...");
            // Send message
            MimeMessage content = helper.getMimeMessage();
            Object oldContent = content.getContent();
            content.setContent(oldContent, "text/plain; charset=UTF-8");
            Transport.send(content);

            System.out.println("Success !!! ");
            response = 200;
        } catch (MessagingException mex) {
            response = 400;
            logger.error(mex.getMessage());
        } finally {
            return response;
        }
    }


    public String geContentFromTemplate(String code, String firstName, String lastName) {
        Map<String, Object> model = new HashMap<>();
        model.put("firstName", firstName);
        model.put("lastName", lastName);
        model.put("code", code);
        StringBuffer content = new StringBuffer();
        try {
            FreeMarkerConfigurationFactoryBean fcfb = new FreeMarkerConfigurationFactoryBean();
            fcfb.setTemplateLoaderPath("/templates/");
            fcfb.afterPropertiesSet();
            Configuration cfg = fcfb.getObject();
            cfg.setDefaultEncoding("utf-8");
            cfg.setOutputEncoding("utf-8");
            Template t = cfg.getTemplate("email.flth");
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(t, model));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public String geContentArchitectFromTemplate(String code, String firstName, String lastName) {
        Map<String, Object> model = new HashMap<>();
        model.put("firstName", firstName);
        model.put("lastName", lastName);
        model.put("code", code);
        StringBuffer content = new StringBuffer();
        try {
            FreeMarkerConfigurationFactoryBean fcfb = new FreeMarkerConfigurationFactoryBean();
            fcfb.setTemplateLoaderPath("/templates/");
            fcfb.afterPropertiesSet();
            Configuration cfg = fcfb.getObject();
            cfg.setDefaultEncoding("utf-8");
            cfg.setOutputEncoding("utf-8");
            Template t = cfg.getTemplate("architect.flth");
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(t, model));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public String getContentPassword(String code, String firstName, String lastName) {
        Map<String, Object> model = new HashMap<>();
        model.put("firstName", firstName);
        model.put("lastName", lastName);
        model.put("code", code);
        StringBuffer content = new StringBuffer();
        try {
            FreeMarkerConfigurationFactoryBean fcfb = new FreeMarkerConfigurationFactoryBean();
            fcfb.setTemplateLoaderPath("/templates/");
            fcfb.afterPropertiesSet();
            Configuration cfg = fcfb.getObject();
            cfg.setDefaultEncoding("utf-8");
            cfg.setOutputEncoding("utf-8");
            Template t = cfg.getTemplate("password.flth");
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(t, model));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }


}