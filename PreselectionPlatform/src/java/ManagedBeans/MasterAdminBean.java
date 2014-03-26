/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import java.awt.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "personBean")
@SessionScoped
public class MasterAdminBean {

    private String firstname;

    private String surname;

    /**
     * Creates a new instance of MasterAdminBean
     */
    public MasterAdminBean() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void savePerson(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + firstname + " " + surname + "!"));
    }

}
