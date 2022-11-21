public class Consommateur {

    private String id;
    private String code;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String type;
    private int capacity;
    private String[] activities;// kenet String[] activities
    private double[] notes;
    private int noteCount;
    public Consommateur(String id, String code, String name, String email, String num, String address,int cap,String act) {
        this.id = id;
        this.code=code;
        this.name=name;
        this.email=email;
        this.phoneNumber=num;
        this.address=address;
        this.capacity= cap;
        this.activities= act.split(",");
        this.notes= new double[activities.length];
        this.noteCount=0;
    }

    public String getId() {
        return this.id;
    }
    public String getType() {return this.type;}
    public String getName() {return this.name;}
    public String[] getActivities() {return this.activities;}

    public void incCount() {this.noteCount+=1;}

    public void getInfos() {
        System.out.println("Nom du consommateur: "+this.name);
        System.out.println("E-mail: "+this.email);
        System.out.println("Num: "+this.phoneNumber);
        System.out.println("Adresse: "+this.address);
        System.out.println("Capacite: "+this.capacity);
        for (int i =0; i< activities.length; i++){
            System.out.println(activities[i]+" (Note: "+notes[i]+")");
        }
    }
    public void addNote(int note, int idx){
        this.notes[idx]=(this.notes[idx]*(this.noteCount-1)+note)/this.noteCount;
    }
}