import java.util.ArrayList;
import java.util.HashMap;

public class Resident {

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

    public HashMap<String, Double> getMetricParams(){
        return this.metricParams;
    }

    public boolean editMetricParam(String param, double newVal){
        this.metricParams.put(param,newVal);
        return true;
        //Add case when param is unvalid
    }


}