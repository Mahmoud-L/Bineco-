package main;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe d'utilisateur
 */
public class User implements Serializable {

    private String id;
    private String username;
    private String password;
    private boolean isRes;

    /**
     * Definission d'un utilisateur a partir des parametres suivants.
     * @param i
     * @param u
     * @param p
     * @param r
     */
    public User(String i, String u, String p, boolean r){
        this.isRes =r ;
        this.id=i;
        this.username=u;
        this.password=p;
    }

    /**
     * remplacer l'ancien mot de passe d'un utilisateur par son nouveau mot de passe.
     * @param oldPwd
     * @param newPwd
     */
    public void changePassword(String oldPwd, String newPwd) {
        if (this.password.equals(oldPwd)) {this.password=newPwd;}
    }

    /**
     * Extraire l'id de l'utilisateur.
     * @return id de l'utilisateur
     */
    public String getId() {return this.id;}

    /**
     * Extraire le nom d'utilisateur de l'utilisateur.
     * @return  le nom d'utilisateur
     */
    public String getUsername() {return this.username;}    /**
     * Extraire le mot de passede l'utilisateur.
     * @return le mot de passe
     */
    public String getPwd() {return this.password;}
    /**
     * Savoir si l'utilisateur est un resident ou non.
     * @return true si l'utilisateur est un resident et False si l'utilisateur est un consommateur.
     */
    public boolean isRes() {return this.isRes;}

    /**
     * Configurer le nom d'utilisateur.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Configurer le mot de passe.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}