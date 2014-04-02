import java.util.ArrayList;
import java.util.List;

/**
 * You should implement your Perceptron in this class. 
 * Any methods, variables, or secondary classes could be added, but will 
 * only interact with the methods or variables in this framework.
 * 
 * You must add code for at least the 3 methods specified below. Because we
 * don't provide the weights of the Perceptron, you should create your own 
 * data structure to store the weights.
 * 
 */
public class Perceptron {
	
	/**
	 * The initial value for ALL weights in the Perceptron.
	 * We fix it to 0, and you CANNOT change it.
	 */
	public final double INIT_WEIGHT = 0.0;
	
	/**
	 * Learning rate value. You should use it in your implementation.
	 * You can set the value via command line parameter.
	 */
	public final double ALPHA;
	
	/**
	 * Training iterations. You should use it in your implementation.
	 * You can set the value via command line parameter.
	 */
	public final int EPOCH;
	public ArrayList<percepInput> inputUnits; 
	public ArrayList<percepOutput>outputUnits;
	public percepInput inputUnit, bias;
	public percepOutput outputUnit;
	public int weight;
	
	// TODO: create weights variables, input units, and output units.
	
	/**
	 * Constructor. You should initialize the Perceptron weights in this
	 * method. Also, if necessary, you could do some operations on
	 * your own variables or objects.
	 * 
	 * @param alpha
	 * 		The value for initializing learning rate.
	 * 
	 * @param epoch
	 * 		The value for initializing training iterations.
	 * 
	 * @param featureNum
	 * 		This is the length of input feature vector. You might
	 * 		use this value to create the input units.
	 * 
	 * @param labelNum
	 * 		This is the size of label set. You might use this
	 * 		value to create the output units.
	 */
	public Perceptron(double alpha, int epoch, int featureNum, int labelNum) {
		this.ALPHA = alpha;
		this.EPOCH = epoch;
		this.inputUnits = new ArrayList<percepInput>(featureNum);
		this.outputUnits = new ArrayList<percepOutput>(labelNum);
		
		//creates and populates list of input units with 1 bias object
		//boolean is for detecting bias unit.
		for (int i = 0; i < featureNum; i++) {
		    inputUnit = new percepInput(featureNum, INIT_WEIGHT, false);		    
		    inputUnits.add(inputUnit);	
		}
		bias = new percepInput(featureNum, 0.0, true); // add bias with constant weight of 1.
		inputUnits.add(bias);
		
		//creates and populates list of output units with proper label from 0-9
		for (int j = 0; j < labelNum; j++) {
		    outputUnit = new percepOutput(j);
		    outputUnits.add(outputUnit);
		}
	
		// TODO: add your code here
	}
	
	/**
	 * Train your Perceptron in this method.
	 * 
	 * @param trainingData
	 */
	public void train(Dataset trainingData) {
		// TODO: add your code her
	    //list of instances
	    percepInput currInput = null;
	    percepOutput currOutput = null;
	    List<Instance> instanceList = trainingData.instanceList;
	    List<Double> features; // features or input value list 
	    ArrayList<Double> weights = null;
	    Instance currInstance = null; //var for current instance being analyzed.
	    
	    String curLabel; // label associated with feature vector.
	    Double updatedWeight = 0.0;
	    for (int i = 0; i == EPOCH; i++) {
	        for (int y = 0; y < instanceList.size(); y++) {
    	        currInstance = instanceList.get(y); //obtain first instance in the list.
    	        features = currInstance.getFeatureValue(); // obtain feature list of current instance
    	        curLabel = currInstance.getLabel(); // obtain label in string format
    	        populatePerceptronInput(features); // populate perceptron input Units with feature values.	     
    	        for(int j = 0; j < outputUnits.size(); j++) {
    	            currOutput = outputUnits.get(j);	            	            
    	            updatedWeight = calculateNewWeight(currOutput, curLabel); //calc output
    	            for (int x = 0; x < inputUnits.size(); x++) {
    	                currInput = inputUnits.get(x);
    	                weights = currInput.getWeights();
    	                weights.set(j, updatedWeight);
    	            }	           
    	        }
	        }    
	    } 
	    currInput = inputUnits.get(7);
	    weights = currInput.getWeights();
	    double val = 0.0;
	    for(int k = 0; k < weights.size(); k++ ) {	       
	        val = weights.get(k);
	        System.out.print(val);
	    }
	}    
	    
	/* Method to populate current Perceptron with features.
	 * param features list
	 * return void */
	public void populatePerceptronInput(List<Double> features) {
	    percepInput currPerceptronInputUnit = null; // current input object/unit
	    for (int i = 0; i < inputUnits.size(); i++) { 
	        currPerceptronInputUnit = inputUnits.get(i);
	        if (currPerceptronInputUnit.bias == true) {
	            currPerceptronInputUnit.setInputValue(1.0);
	        } else {
	            currPerceptronInputUnit.setInputValue(features.get(i));
	        }
	    }      
	}
	/* Function used to gather all weights for a relative ouput unit
	 * and add them together for a final weight
	 * param current outputUnit 
	 * return value of all combined weights.
	 */	
	public double calculateNewWeight(percepOutput outputUnit, String curLabel) {
	    double inputValue = 0.0, weight = 0.0, updatedWeight = 0.0, activationVal = 0.0, T = 0.0;
	    percepOutput currOut = outputUnit; //obtain current output unit
        percepInput currIn = null; //initialize input unit
        String label = currOut.getLabel();// get label of input Unit
        int labelNum = currOut.getObjectNumber();// get output unit number (instead of string, for math)
        if (label == curLabel) { //compare strings to set which input unit to 1
            currOut.setOutput(1); //set currentOutput object output to 1 or 0
        } else {
            currOut.setOutput(0);
        }
        for (int i = 0; i < inputUnits.size(); i++) {   
            currIn = inputUnits.get(i);
            inputValue = currIn.getInputValue();
            weight = currIn.getWeights().get(labelNum);// obtain relative weight from relative input unit.
            T += (weight * inputValue);
        }            
            activationVal = 1 / (1 + Math.exp(-(T)));
            updatedWeight = (weight) - ((ALPHA) * (activationVal - currOut.getOutput())
                    * (activationVal) * (1 - activationVal) * (inputValue));                   
        
	    return updatedWeight;

	}
	
	/**
	 * Test your Perceptron in this method. Refer to the homework documentation
	 * for implementation details and requirement of this method.
	 * 
	 * @param testData
	 */
	public void classify(Dataset testData) {
		
		// TODO: add your code here
	}
	
}