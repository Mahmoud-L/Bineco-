import java.util.Objects;

public class User {

    private String id;
    private String username;
    private String password;
    private boolean isRes;


    public void changePassword(String oldPwd, String newPwd) {
        if (Objects.equals(oldPwd, this.password)) {this.password=newPwd;}
    }

    public String getId() {return this.id;}
    public String getUsername() {return this.username;}
    public boolean isRes() {return this.isRes;}
}