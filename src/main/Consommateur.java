package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
Classe des consommateurs
*/

public class Consommateur implements Serializable {

    private String id;
    private String code;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String type;
    private int capacity;
    private ArrayList<String> activities;
    private ArrayList<Double> notes;
    private ArrayList<Integer> noteCount;
    /**
     * Affecter les paremtres suivant a un consommateur.
     * @param id
     * @param code
     * @param name
     * @param email
     * @param num
     * @param address
     * @param type
     * @param cap
     * @param act
     */
    public Consommateur(String id, String code, String name, String email, String num, String address,String type, int cap,String act) {
        this.id = id;
        this.code=code;
        this.name=name;
        this.email=email;
        this.phoneNumber=num;
        this.address=address;
        this.type = type;
        this.capacity= cap;
        this.activities= new ArrayList<>();
        Collections.addAll(this.activities, act.split(","));
        this.notes= new ArrayList<Double>(Collections.nCopies(activities.size(), 0.0));
        this.noteCount=new ArrayList<Integer>(Collections.nCopies(activities.size(), 0));;
    }
    //--------------------------Getters------------------------------------

    /**
     *Sert a avoir l'id d'un consommateur
     * @return l'id du consommateur.
     */
    public String getId() {return this.id;}
    /**
     * Sert a avoir le type d'un consommateur
     * @return le type du consommateur.
     */
    public String getType() {return this.type;}
    /**
     * Sert a avoir le nom d'un consommateur
     * @return le nom du consommateur.
     */
    public String getName() {return this.name;}
    /**
     * sert a avoir l'activite d'un consommateur
     * @return l'activite du consommateur.
     */
    public ArrayList<String> getActivities() {return this.activities;}


    //-----------------------Setters---------------------------------

    /**
     * Affecte un nom a un consommateur.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Affecte un e-mail a un consommateur.
     */

    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Affecte un numero de telephone a un consommateur.
     */

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Affecte une adresse a un consommateur.
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Affecte un type a un consommateur.
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Affecte un capacite a un consommateur.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Incremente le compteur des residents qui ont note le consommateur.
     * @param idx
     * @return compteur
     */
    public int incCount(int idx) {
        if (idx < 0 || idx >= this.notes.size()) {
            throw new IllegalArgumentException("L'index de la note est invalide");
        }
        int count = this.noteCount.get(idx);
        count++;
        this.noteCount.set(idx, count);
        return count;
    }

    /**
     * Affiche les infos d'un consommateur qu'un resident voudrait voir.
     */
    public void displayInfos() {
        System.out.println("Nom du consommateur: "+this.name);
        System.out.println("E-mail: "+this.email);
        System.out.println("Num: "+this.phoneNumber);
        System.out.println("Adresse: "+this.address);
        System.out.println("Capacite: "+this.capacity);
        System.out.println("Activites maintenues : ");
        displayActivitiesWithGrades();
    }

    /**
     * affiche les activite propose par un consommateur.
     */
    public void displayActivities() {
        for (int i =0; i< this.activities.size(); i++){
            System.out.println("[" + i + "]  " + this.activities.get(i));
        }
    }

    /**
     * affiche les activite propose par un consommateut avec la note correspondante pour chaque activite.
     */

    public void displayActivitiesWithGrades() {
        for (int i =0; i< this.activities.size(); i++){
            System.out.println(this.activities.get(i)+" (Note: "+ notes.get(i)+")");
        }
    }
    /**
     * Ajoute la note donnee par un resident et calcule la nouvelle moyenne de l'activite du consommateur
     * @param note
     * @param idx
     */
    public void addNote(int note, int idx){
        if (note < 0 || note > 5) {
            throw new IllegalArgumentException("La note entrée est invalide");
        }
        if (idx < 0 || idx >= this.notes.size()) {
            throw new IllegalArgumentException("L'index de la note à ajouté est invalide");
        }
        double newNote = (note + this.notes.get(idx) * this.noteCount.get(idx)) / incCount(idx);
        this.notes.set(idx, newNote);
    }

    /**
     * Ajoute une activite.
     * @param activity
     */
    public void addActivity(String activity) {
        this.activities.add(activity);
        this.notes.add(0.0);
        this.noteCount.add(0);
    }

    /**
     * Modifier une activite.
     * @param index
     * @param newActivity
     */
    public void editActivity(int index, String newActivity) {
        this.activities.set(index, newActivity);
    }

    /**
     * Supprimer une activite.
     * @param index
     */
    public void deleteActivity(int index) {
        if (index < 0 || index >= this.activities.size()) {
            throw new IllegalArgumentException("L'index de l'activité est invalide");
        }
        this.activities.remove(index);
        this.notes.remove(index);
        this.noteCount.remove(index);
    }

    @Override
    public String toString() {
        return this.name;
    }
}