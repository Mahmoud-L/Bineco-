import java.util.ArrayList;

public class ConsomController extends Controller {

    static ConsomRepository conRep = new ConsomRepository();
    static ResidentRepository resRep = new ResidentRepository();
    Consommateur consom;

    public ConsomController(String id){
        this.consom = (Consommateur) conRep.get(id);
    }


    public void notifyResids(String msg) {
        for (int i=0; i<resRep.size(); i++){
            //Send email to each res
            continue;
        }
    }

}