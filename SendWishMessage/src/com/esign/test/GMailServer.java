package com.esign.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GMailServer extends Authenticator{
	private String mailhost ="smtp.gmail.com";
    private String user;
    private String password;
    private Session session;
    private String filename;
    private String logo="D:\\Javaprograms\\wishmsg\\logo\\logo.png";
    public GMailServer(String user, String password) {
        this.user = user;
        this.password = password;  

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");  

        session = Session.getDefaultInstance(props, this);
    }  
    
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(user, password);
    }  
    
    
    public synchronized void sendMail(String subject,String name, String body, String sender, String recipients,String filename) throws Exception
    {
        MimeMessage message = new MimeMessage(session);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/html"));
        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);
        message.setDataHandler(handler);
        if (recipients.indexOf(',') > 0)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        else
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
        
        
        String htmtbody="<!DOCTYPE html>\r\n"
        		+ "<html lang=\"en\">"
        		+ "<head>"
        		+"   <style>"
        		+ "        *{"
        		+ "            font-family:Georgia, 'Times New Roman', Times, serif;"
        		+ "            font-size: large;"
        		+ "        }"
        		+ "    </style>"
        		+ "</head>"
        		+ "<body>"
        		+ "<p>Dear "+name+",</p><br><br><p>"
        		+body+"</p><br><br><img src=\"cid:image\"><br><br>"
        		+"<p>Sincerely Your ," + "<br>" + "Team Foxit eSign<br></p>"
        		+"<img width='90' height='50' src=\"cid:logo\">"
        		+ "</body>"
        		+ "</html>";
        
        
        // the BODY and the embedded image
        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPart = new MimeBodyPart();
        
        String htmlText =htmtbody;
        messageBodyPart.setContent(htmlText, "text/html");
        // add it
        multipart.addBodyPart(messageBodyPart);

        // second part (the image)
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(filename);

        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");
        // add image to the multipart
        multipart.addBodyPart(messageBodyPart);
        
        //adding logo
        messageBodyPart = new MimeBodyPart();
        fds = new FileDataSource(logo);

        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<logo>");
        // add image to the multipart
        multipart.addBodyPart(messageBodyPart);

        // put everything together
        message.setContent(multipart);
        
        
        
        //====================================
//        BodyPart messageBodyPart1 = new MimeBodyPart();  
//        messageBodyPart1.setText(body);
        
//        DataSource source = new FileDataSource(filename);  
//        messageBodyPart1.setDataHandler(new DataHandler(source));
//        messageBodyPart1.setFileName(filename);
        
//        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
//        DataSource source = new FileDataSource(filename);  
//        messageBodyPart2.setDataHandler(new DataHandler(source));  
//        messageBodyPart2.setFileName(filename); 
//        
//        Multipart multipart = new MimeMultipart(); 
//        multipart.addBodyPart(messageBodyPart1);
//        multipart.addBodyPart(messageBodyPart2);  
       
//        message.setContent(multipart );  
        
        Transport.send(message);
    }  

    public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;  

        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }  

        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }  

        public void setType(String type) {
            this.type = type;
        }  

        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }  

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }  

        public String getName() {
            return "ByteArrayDataSource";
        }  

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }
}