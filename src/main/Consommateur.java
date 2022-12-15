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
        System.out.println(this.activities);
        this.notes= new ArrayList<Double>(Collections.nCopies(activities.size(), 0.0));
        this.noteCount=new ArrayList<Integer>(Collections.nCopies(activities.size(), 0));;
    }

    //Getters
    public String getId() {return this.id;}
    public String getType() {return this.type;}
    public String getName() {return this.name;}
    public ArrayList<String> getActivities() {return this.activities;}

    //Setters
    public void setName(String name) {
        this.name = name;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //Incremente le compteur des residents qui ont note le consommateur
    public int incCount(int idx) {
        if (idx < 0 || idx >= this.notes.size()) {
            throw new IllegalArgumentException("L'index de la note est invalide");
        }
        int count = this.noteCount.get(idx);
        count++;
        this.noteCount.set(idx, count);
        return count;
    }

    //Affiche les infos d'un consommateur qu'un resident voudrait voir
    public void displayInfos() {
        System.out.println("Nom du consommateur: "+this.name);
        System.out.println("E-mail: "+this.email);
        System.out.println("Num: "+this.phoneNumber);
        System.out.println("Adresse: "+this.address);
        System.out.println("Capacite: "+this.capacity);
        System.out.println("Activites maintenues : ");
        displayActivitiesWithGrades();
    }

    public void displayActivities() {
        for (int i =0; i< this.activities.size(); i++){
            System.out.println("[" + i + "]  " + this.activities.get(i));
        }
    }

    public void displayActivitiesWithGrades() {
        for (int i =0; i< this.activities.size(); i++){
            System.out.println(this.activities.get(i)+" (Note: "+ notes.get(i)+")");
        }
    }

    //Ajoute la note d'un resident et calcule la moyenne
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

    public void addActivity(String activity) {
        this.activities.add(activity);
        this.notes.add(0.0);
        this.noteCount.add(0);
    }

    public void editActivity(int index, String newActivity) {
        this.activities.set(index, newActivity);
    }

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