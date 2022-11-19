import java.util.ArrayList;

public class Resident {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private ArrayList<Bac> bacs = new ArrayList<>();
    private String[] metricParams;

    public Resident(String id, String fn, String ln, String email, String num, String address) {
        this.id = id;
        this.firstName=fn;
        this.lastName=ln;
        this.email=email;
        this.phoneNumber=num;
        this.address=address;
    }

    public String getId() {return this.id;}

    public ArrayList<Bac> getBacs() {return this.bacs;}

    public void addBac(Bac b){
        this.bacs.add(b);
    }

    public boolean deleteBac(String code){
        int bToDel=0;
        for (int i=0; i < bacs.size(); i++){
            if (bacs.get(i).getCode().equals(code)) {bToDel=i;}
        }
        this.bacs.remove(bToDel);
        return true;
        //Add catch false for when element doesn't exist.
    }

    public String[] getMetrics(){
        return null;
    }

    public boolean editMetricParams(){
        return true;
    }

}