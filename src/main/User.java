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

    public User(String i, String u, String p, boolean r){
        this.isRes =r ;
        this.id=i;
        this.username=u;
        this.password=p;
    }

    public void changePassword(String oldPwd, String newPwd) {
        if (this.password.equals(oldPwd)) {this.password=newPwd;}
    }

    public String getId() {return this.id;}
    public String getUsername() {return this.username;}
    public String getPwd() {return this.password;}
    public boolean isRes() {return this.isRes;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}