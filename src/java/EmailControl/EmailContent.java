package EmailControl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
/**
 *
 * @author naitik
 */
public class EmailContent {
    private String from;
    private int emailnumer;
    private String subject;
    private String emailContent;
    private boolean selected;
    private Date emaildate;
    private boolean markedDelete;

        public EmailContent() { }
    public EmailContent(String from, String subject, String emailContent, Date emaildate) {
        super();
        this.from = from;
        this.subject = subject;
        this.emailContent = emailContent;
        this.emaildate = emaildate;
    }



    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getEmailnumer() {
        return emailnumer;
    }

    public void setEmailnumer(int emailnumer) {
        this.emailnumer = emailnumer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Date getEmaildate() {
        return emaildate;
    }

    public void setEmaildate(Date emaildate) {
        this.emaildate = emaildate;
    }

    public boolean isMarkedDelete() {
        return markedDelete;
    }

    public void setMarkedDelete(boolean markedDelete) {
        this.markedDelete = markedDelete;
    }
    public String toString()
	{
		return "Sender: "+from
				+"<br/>Subject: "+subject
				+"<br/>Date: "+emaildate
				+"<br/><br/>"+emailContent;
	}
    
    
}
