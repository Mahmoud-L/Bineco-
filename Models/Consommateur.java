public class Consommateur {

    private String id;
    private String code;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String type;
    private int capacity;
    private String activities;// kenet String[] activities
    public Consommateur(String id, String code, String name, String email, String num, String address,int cap,String act) {
        this.id = id;
        this.code=code;
        this.name=name;
        this.email=email;
        this.phoneNumber=num;
        this.address=address;
        this.capacity= cap;
        this.activities= act;
    }

    public String getId() {
        return this.id;
    }

}