import java.util.ArrayList;
import java.util.Collections;


public class percepInput {
    public static ArrayList<Double> weights;
    public static double inputValue = 0.0;
    public static boolean bias;
    
    public percepInput(int numItems, double value, boolean bias) {
        weights = new ArrayList<Double>(Collections.nCopies(numItems, value));
        this.inputValue = value;
        this.bias = bias;
    }
    
    public ArrayList<Double> getWeights() {
        return weights;
    }
    
    public double getInputValue() {
        return this.inputValue;
    }
    
    public void setInputValue(double value) {
        this.inputValue = value;
    }

}
