package EmailControl;


import EmailControl.EmailContent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author naitik
 */
public class EmailmessagesBean {

    private List<EmailContent> messages = new ArrayList<EmailContent>();
    private EmailContent emailcontent;
    private List<Integer> deletedMailsNumber = new ArrayList<Integer>();

    public List<EmailContent> getMessages() {
        return messages;
    }

    
	public EmailContent getEmailContent() {
		return emailcontent;
	}

	public void setEmailContent(EmailContent emailContent) {
		this.emailcontent = emailContent;
	}
	
    public void setMessages(List<EmailContent> messages) {
        this.messages.clear();
        for (EmailContent emailcontent : messages) {
            boolean isMaildelete = false;
            for (Integer deletedMailNumber : deletedMailsNumber) {
                if (emailcontent.getEmailnumer() == deletedMailNumber) {
                    isMaildelete = true;
                    break;
                }
            }
            if (!isMaildelete) {
                this.messages.add(emailcontent);
            }
        }

    }

    public List<Integer> getDeletedMailsNumber() {
        return deletedMailsNumber;
    }

    public void setDeletedMailsNumber(List<Integer> deletedMailsNumber) {
        this.deletedMailsNumber = deletedMailsNumber;
    }

    public void display(EmailContent emailcontent) {
        this.emailcontent = emailcontent;
    }

    public EmailContent getEmailcontent() {
        return emailcontent;
    }

    public void setEmailcontent(EmailContent emailcontent) {
        this.emailcontent = emailcontent;
    }

    public void deleteMail()  {
    	
        Iterator <EmailContent> itr=messages.iterator();
        while(itr.hasNext()) {
            EmailContent emailcontent = (EmailContent)itr.next();
          if(emailcontent.isSelected()) {
            int i = messages.indexOf(emailcontent);
            deletedMailsNumber.add(emailcontent.getEmailnumer());
            if (i != -1) {itr.remove();}
          }
        }
        }
}
