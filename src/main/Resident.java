package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe de resident
 */
public class Resident implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private ArrayList<Bac> bacs = new ArrayList<>();
    private HashMap<String, Double> metricParams = new HashMap<String, Double>();

    public Resident(String id, String fn, String ln, String email, String num, String address) {
        this.id = id;
        this.firstName=fn;
        this.lastName=ln;
        this.email=email;
        this.phoneNumber=num;
        this.address=address;
        metricParams.put("Recyclage",0.333);
        metricParams.put("Compostage",0.333);
        metricParams.put("Ordures",0.333);
        metricParams.put("CIM",5.0);
    }

    public String getId() {
        return this.id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Bac> getBacs() {return this.bacs;}
    public HashMap<String, Double> getMetricParams(){return this.metricParams;}

    public void addBac(Bac b){
        this.bacs.add(b);
    }

    public boolean deleteBac(int index){
        if (index < 0 || index >= this.bacs.size()) {
            throw new IllegalArgumentException("L'index du bac n'est pas valide");
        }
        this.bacs.remove(index);
        this.bacs.trimToSize();
        return true;
    }

    public boolean editMetricParam(String param, double newVal){
        if (!this.metricParams.containsKey(param)) {
            throw new IllegalArgumentException("Le paramètre entré n'existe pas");
        }
        if ((param.equals("Recyclage") || param.equals("Compostage") || param.equals("Ordures")) && (newVal > 1 || newVal < 0)) {
            throw new IllegalArgumentException("La valeur entrée est invalide");
        }
        if ((param.equals("CIM")) && (newVal > 100 || newVal < 0)) {
            throw new IllegalArgumentException("La valeur entrée est invalide");
        }
        this.metricParams.put(param,newVal);
        return true;
    }

    public String getReportInfos(){
        return (this.firstName+","+this.lastName+","+this.address+","+this.email);
    }

}