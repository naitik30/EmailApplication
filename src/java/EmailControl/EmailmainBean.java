package EmailControl;


import ejb.Email;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author naitik
 */
@ManagedBean
@SessionScoped
public class EmailmainBean {

    @EJB
    private Email email;
    private String username;
    private String password;
    private String recipient;
    private String mailsubject;
    private String emailcontant;
    private EmailmessagesBean emailmessagesBeans = new EmailmessagesBean();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public EmailmessagesBean getEmailmessagesBeans() {
        return emailmessagesBeans;
    }

    public void setEmailmessagesBeans(EmailmessagesBean emailmessagesBeans) {
        this.emailmessagesBeans = emailmessagesBeans;
    }


    public String getMailsubject() {
        return mailsubject;
    }

    public void setMailsubject(String mailsubject) {
        this.mailsubject = mailsubject;
    }

    public String getEmailcontant() {
        return emailcontant;
    }

    public void setEmailcontant(String emailcontant) {
        this.emailcontant = emailcontant;
    }

    public String checklogin() {
        if (email.connect(username, password)) {
            return "welcome";
        } else {
            ErrorMessage("The Username or password is incorrect.");
            return "login";
        }
    }
        

    public String sendMail() {
        email.sendMessage(getRecipient(), getUsername(), getMailsubject(), getEmailcontant());
        recipient="";
        mailsubject = "";
        emailcontant = "";
        return "welcome";
    }
    public String emailCheck()
    {
        emailmessagesBeans.setMessages(email.getAllMessages());
        return "emailCheck";
        
    }
    public  String logout()
    {
        email.disconnect(emailmessagesBeans.getDeletedMailsNumber());
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public void ErrorMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage ermessage = new FacesMessage(message);
        ermessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, ermessage);
    }

}
