/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import com.master.control.Controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import services.DBConnection;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "mastersBean", eager = true)
@ApplicationScoped
public class MastersBean implements Serializable {

    private final ArrayList<Master> mastersList = new ArrayList<>();
    private final Connection connection = DBConnection.getConnection();

    public Connection getConnection() {
        return connection;
    }

    String Query = "select NOM_MASTER from master";

    /**
     * Creates a new instance of MastersBean
     */
    public MastersBean() {
        System.err.println("Location :" + MastersBean.class
                .getProtectionDomain().getCodeSource().getLocation().getFile());
        connect();
        System.out.println("Masters Bean loading complete");

    }

    public ArrayList<Master> getMastersList() {
        return mastersList;
    }

    public void deleteRecord(Master master) {
        mastersList.remove(master);
    }

    public void addRecord() {
        mastersList.add(new Master(""));
    }

    public void save() {
        System.out.println("writing to XHTML");
        writeInXHTML();
        System.out.println("writing to db");
        writeToDB();
    }

    public void writeToDB() {
//        try {
//            java.sql.Statement statement = connection.createStatement();
//            statement.executeUpdate("DELETE FROM  master;");
//
//            int i = 1;
//            for (Master master : mastersList) {
//                statement.executeUpdate("insert into master (code_master , NOM_MASTER) values ('" + i + "' , '" + master.getNomMaster() + "');");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MastersBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Controller controller = new Controller();
        controller.addMasters(mastersList);

    }

    public void writeInXHTML() {

        try {

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            ServletContext servletContext = (ServletContext) externalContext.getContext();
            String absoluteDiskPath = servletContext.getRealPath("/WEB-INF/masters.xhtml");
            PrintWriter out;
            out = new PrintWriter(new BufferedWriter(new FileWriter(
                    absoluteDiskPath, false)));
            out.println("<?xml version='1.0' encoding='UTF-8' ?>\r\n"
                    + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
                    + "<html xmlns=\"http://www.w3.org/1999/xhtml\"\r\n"
                    + "      xmlns:f=\"http://xmlns.jcp.org/jsf/core\">\r\n<!-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DO NOT MODIFY!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->");
            int i = 1;
            for (Master master : mastersList) {
                out.println("<f:selectItem itemLabel=\"" + master.getNomMaster()
                        + "\" itemValue=\"" + i + "\"/>");
                i++;
            }
            out.println("</html>");
            out.close();
        } catch (IOException e) {
            System.err.println("IOException when trying to write to masters.xhtml");
        }
    }

    private void connect() {
        java.sql.Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(Query);
            while (result.next()) {
                mastersList.add(new Master(result.getString("NOM_MASTER")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(MastersBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public class Master implements Serializable {

        private String nomMaster;

        public String getNomMaster() {
            return nomMaster;
        }

        public void setNomMaster(String nomMaster) {
            this.nomMaster = nomMaster;
        }

        public Master(String nomMaster) {
            this.nomMaster = nomMaster;
        }

    }
}
