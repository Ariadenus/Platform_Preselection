/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "administrationBean")
public class AdministrationBean {

    private static Integer currentStation = new Integer(1);

    /**
     * Creates a new instance of AdministrationBean
     */
    public AdministrationBean() {

    }

    public boolean onMastersManagements() {
        System.out.println(currentStation);
        return currentStation == 2;
    }

    public boolean onDemandsManagements() {
        System.out.println(currentStation);
        return currentStation == 3;
    }

    public int getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(int onStation) {
        this.currentStation = onStation;
        System.out.println("setting to " + currentStation);
    }
}
