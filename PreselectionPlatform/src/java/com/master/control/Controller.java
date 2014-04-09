/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.control;

import ManagedBeans.MastersBean;
import com.master.model.Master;
import com.master.utils.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author Dell
 */
public class Controller {

    Session session = null;

    public Controller() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();

    }

    /**
     *
     * @param mastersList : the list of masters to be added.
     */
    public void addMasters(ArrayList<MastersBean.Master> mastersList) {

        for (MastersBean.Master master : mastersList) {
            Master masterObject = new Master(master.getNomMaster());
            session.beginTransaction();
            session.save(masterObject);
        }
        session.getTransaction().commit();
    }

}
