/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import services.DBConnection;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "exigencesBean")
@SessionScoped
public class ExigencesBean implements Serializable {

    private final Connection connection = DBConnection.getConnection();

    private static final List<Moyennes> listExigences = new ArrayList<>();
    private static final List<Master> listMasters = new ArrayList<>();

    /**
     * The Master object determined by the value given in the form.
     */
    private Master currentMasterObject;

    /**
     * The currently selected Master as given by the form.
     */
    private String currentMaster;

    public String getCurrentMaster() {
        return currentMaster;
    }

    public void setCurrentMaster(String currentMaster) {
        this.currentMaster = currentMaster;
        if (!searchCurrentMaster(currentMaster)) {
            currentMasterObject = new Master(currentMaster);
            listMasters.add(currentMasterObject);
        }
    }

    private boolean searchCurrentMaster(String master) {
        for (int i = 0; i < listMasters.size(); i++) {
            if (listMasters.get(i).getNomMaster().equals(master)) {
                listExigences.clear();
                listExigences.addAll(listMasters.get(i).getListExigences());
                currentMasterObject = listMasters.get(i);
                return true;
            }
        }
        return false;
    }

    public Master getCurrentMasterObject() {
        return currentMasterObject;
    }

    public void setCurrentMasterObject(Master currentMasterObject) {
        this.currentMasterObject = currentMasterObject;
    }

    public ExigencesBean() {
        System.out.println("creating exigences bean");
        //addRecord();
    }

    public static List<Moyennes> getListExigences() {
        return listExigences;
    }

    public void deleteRecord(Moyennes m) {
        listExigences.remove(m);
    }

    public void addRecord() {
        System.out.println("Adding record");
        currentMasterObject.getListExigences().add(new Moyennes(1, 1, "Informatique"));
//        if (listExigences.isEmpty()) {
//            listExigences.add(new Moyennes(1, 1, "Informatique"));
//        } else {
//            if (listExigences.get(listExigences.size() - 1).getYear() != 1 && listExigences.get(listExigences.size() - 1).getCoefficient() != 1) {
//                listExigences.add(new Moyennes(1, 1, "Informatique"));
//            }
//        }
    }

    public void save() {

        try {
            java.sql.Statement statement = connection.createStatement();
            //statement.executeUpdate("DELETE FROM  exigences WHERE code_master = " + currentMasterObject.getNomMaster() + " ;");
            for (int i = 0; i < currentMasterObject.listExigences.size(); i++) {
                statement.executeUpdate("insert into exigences (NOM_MATIERE , COEFFICIENT , code_master ) values ('" + currentMasterObject.listExigences.get(i).getNomMatiere() + "' , '" + currentMasterObject.listExigences.get(i).coefficient + "' , '" + currentMasterObject.nomMaster + "');");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExigencesBean.class.getName()).log(Level.SEVERE, "Exception during persistance of exigences", ex);
        }

    }

    public class Moyennes implements Serializable {

        int year;
        String nomMatiere;
        int coefficient;

        public Moyennes() {
        }

        public Moyennes(int year, int coefficient, String nomMatiere) {
            this.year = year;
            this.coefficient = coefficient;
            this.nomMatiere = nomMatiere;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(int coefficient) {
            this.coefficient = coefficient;
        }

        public String getNomMatiere() {
            return nomMatiere;
        }

        public void setNomMatiere(String nomMatiere) {
            this.nomMatiere = nomMatiere;
        }

    }

    public class Master implements Serializable {

        private String nomMaster;
        private ArrayList<Moyennes> listExigences;

        public Master(String nomMaster) {
            this.nomMaster = nomMaster;
            this.listExigences = new ArrayList<>();
        }

        public String getNomMaster() {
            return nomMaster;
        }

        public void setNomMaster(String nomMaster) {
            this.nomMaster = nomMaster;
        }

        public ArrayList<Moyennes> getListExigences() {
            return listExigences;
        }

        public void setListExigences(ArrayList<Moyennes> listExigences) {
            this.listExigences = listExigences;
        }

    }

}
