import java.util.Objects;

public class User {

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
        if (Objects.equals(oldPwd, this.password)) {this.password=newPwd;}
    }

    public String getId() {return this.id;}
    public String getUsername() {return this.username;}
    public String getPwd() {return this.password;}
    public boolean isRes() {return this.isRes;}
}