
public class percepOutput {
    public final int objectNumber;
    public int output;
    public double predictedOutput;
    String label;

    public percepOutput(int objectNumber) {
        this.objectNumber = objectNumber;
        this.label = Integer.toString(objectNumber);
        this.predictedOutput = 0.0;
        this.output = 0; 
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public int getObjectNumber() {
        return this.objectNumber;
    }

    public int getOutput() {
        return output; 
    }
    
    public void setOutput(int value) {
        this.output = value; 
    }

    public double getPredicteddOutput() {
        return predictedOutput;
    }
    
}
