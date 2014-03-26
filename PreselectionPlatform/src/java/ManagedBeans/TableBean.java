/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "tableBean")
public class TableBean implements Serializable {

    static {
        colors = new String[]{"Black", "White", "Green", "Red", "Blue", "Orange", "Silver", "Yellow", "Brown", "Maroon"};

        manufacturers = new String[]{"Mercedes", "BMW", "Volvo", "Audi", "Renault", "Opel", "Volkswagen", "Chrysler", "Ferrari", "Ford"};

    }

    private final static String[] colors;

    private final static String[] manufacturers;

    private static final List<Car> carsSmall = new ArrayList<>();

    public TableBean() {
        System.out.println("ON!");
        //carsSmall.clear();
        //   populateRandomCars(carsSmall, 9);

        this.addRecord();

    }

    private void populateRandomCars(List<Car> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(new Car(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));
        }
    }

    public List<Car> getCarsSmall() {
        return carsSmall;
    }

    public String[] getManufacturers() {
        return manufacturers;
    }

    public String[] getColors() {
        return colors;
    }

    public String addRecord() {
        if (carsSmall.size() >= 1) {
            if ((carsSmall.get(carsSmall.size() - 1).getYear() == 0 || carsSmall.get(carsSmall.size() - 1).getColor().equals("") || carsSmall.get(carsSmall.size() - 1).getModel().equals("") || carsSmall.get(carsSmall.size() - 1).getManufacturer().equals(""))) {
                System.out.println("finish what you have!");
                return null;

            } else {
                carsSmall.add(new Car("", 0, "", ""));
                System.out.println("adding");
            }
        } else {
            carsSmall.add(new Car("", 0, "", ""));
            System.out.println("adding");
        }

        return null;
    }

    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }

    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }

    private String getRandomManufacturer() {
        return manufacturers[(int) (Math.random() * 10)];
    }

    private String getRandomModel() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valeur changée", "");
            System.out.println("Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public class Car implements Serializable {

        String model;
        int year;
        String manufacturer;
        String color;

        public Car(String model, int year, String manufacturer, String color) {
            this.model = model;
            this.year = year;
            this.manufacturer = manufacturer;
            this.color = color;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

    }
}
