import java.util.ArrayList;
import java.util.Collections;


public class percepBias {
    public ArrayList<Double> weights;
    String label = null;
    
    public percepBias(int numItems, double value, String label) {
        weights = new ArrayList<Double>(Collections.nCopies(numItems, value));
        this.label = label;
    }

}
