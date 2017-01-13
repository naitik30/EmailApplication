/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author naitik
 */

import EmailControl.EmailContent;
import java.util.ArrayList;
import java.util.List;
import javax.mail.Message;
import javax.ejb.Remote;

@Remote
public interface Email {
  public void sendMessage(String recipient,String sender,String subject, String data);
  public boolean connect(String login, String passwd);
  public void disconnect(List <Integer> deleteEmailNumber);
  public List<EmailContent> getAllMessages();
  
}
