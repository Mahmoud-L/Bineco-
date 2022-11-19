public class Main {
    static ResidentRepository resRep = new ResidentRepository();
    //
    public static void main(String[] args) {

        resRep.add(new Resident("a","b","c","d","e","f"));
        System.out.println(resRep.get("a"));
    }
}