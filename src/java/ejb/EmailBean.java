/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import EmailControl.EmailContent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author naitik
 */
@Stateful
@LocalBean
public class EmailBean implements Email {
    @Resource(name="mail/MyMailSession")

    private Session session;
    private Store store = null;
    private Folder folder;
    private Message[] messageList = null;
    private static final String mailer = "JavaMailer";

    public void sendMessage(String recipient, String sender, String subject, String data) {
        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(sender, false));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient, false));
            msg.setSubject(subject);
            Date timeStamp = new Date();
            msg.setText(data);
            msg.setHeader("X-Mailer", mailer);
            msg.setSentDate(timeStamp);
            Transport.send(msg);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void disconnect(List <Integer> deleteEmailNumber) {
        if (deleteEmailNumber != null)
        {
            
            for (Integer emailNumber: deleteEmailNumber){
                for(int i=0; i<messageList.length; i++)
                {
                    if(emailNumber == messageList[i].getMessageNumber())
                    {
                        try {
                            messageList[i].setFlag(Flags.Flag.DELETED, true);
                        } catch (MessagingException ex) {
                            Logger.getLogger(EmailBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                    
            }
        }
        try {
            folder.close(true);
            store.close();
        } catch (MessagingException ex) {
            Logger.getLogger(EmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public List<EmailContent> getAllMessages() {
        List<EmailContent> allmails = new ArrayList<EmailContent>();
        try {
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);
            messageList = folder.getMessages();
            for (Message message : messageList) {
                EmailContent emailcontent = new EmailContent();
                emailcontent.setEmailnumer(message.getMessageNumber());
                Address[] fromAdd = message.getFrom();
                InternetAddress sender = (InternetAddress) fromAdd[0];
                emailcontent.setFrom(sender.getAddress());
                emailcontent.setEmailContent(message.getContent().toString());
                emailcontent.setSubject(message.getSubject());
                emailcontent.setEmaildate(message.getSentDate());
                allmails.add(emailcontent);
            }
        } catch (MessagingException ex) {
            Logger.getLogger(EmailBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allmails;
    }

    public boolean connect(String login, String passwd) {
        try {
            store = session.getStore("pop3");
            store.connect("localhost", login, passwd);
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);
            messageList = folder.getMessages();
            return true;
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EmailBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (MessagingException ex) {
            Logger.getLogger(EmailBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
