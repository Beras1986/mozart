package netcore;

import java.util.ArrayList;


/**
 *
 * @author Piotr Berger E5109181
 * version 1.11
 * August 2019
 */

public class Train {

    // Instance variables
    private double MSE; // mean squared error
    private double MAE; // mean absolute error
    
    // Methods
    
    int epoch = 0;
    
    public NeuralNetwork trainNetwork(NeuralNetwork ann) {
		
	this.setMse(1.0);
        this.setMae(0.0);
        
	while(getMse() > ann.getAcceptedError()) {
			
            if (epoch >= ann.getMaxEpochs()){
                
                System.out.println("________" + ann.getMaxEpochs() + " epochs reached________");
                System.out.println();
                break;
            }
			
            int rows = ann.getTrainingDataset().length;
            double errorsSum = 0.0;
            double maeErrorSum = 0.0;
            int errorCount = 0;
			
            for (int i = 0; i < rows; i++) {
				
                ann = forwardPass(ann, i);
                ann = backpropagate(ann, i);	
                errorsSum = errorsSum + ann.getMeanError();
                maeErrorSum = ann.getOutputLayer().getNeurons().get(0).getError();
                errorCount += 1;
                //System.out.println(ann.getMeanError());
                //System.out.println();
            }
			
            this.setMae(maeErrorSum/errorCount);
            this.setMse(errorsSum / rows);
            //System.out.println(this.getMse());
            //System.out.println(this.getMae());
            ann.getMsErrors().add(this.getMse());
            epoch++;	
        }
        
        //System.out.println("MAE is: " + getMae());
        //System.out.println("MSE is: " + getMse());
        //System.out.println("Number of epochs "+ epochs);
        
        ann.setMae(this.getMae());
        ann.setMse(this.getMse());
	return ann;
    }
    
    public NeuralNetwork forwardPass(NeuralNetwork ann, int i) { // i = row j = col
	
        ArrayList<HiddenLayer> hiddenLayers = new ArrayList<>(ann.getHiddenLayers());


	double calculatedOutput;
	double expectedOutput;
	double sumError = 0.0; 
					
        int hiddenLayerIndex = 0;
			
        for (HiddenLayer hiddenLayer : hiddenLayers) {
				
            int numberOfNeuronsInLayer = hiddenLayer.getLayerNeuronsCount();
				
            for (Neuron neuron : hiddenLayer.getNeurons()) {
					
                double valueOut;
                double value = 0.0;
						
                for (int j = 0; j < numberOfNeuronsInLayer-1; j++) { 
                      
                    double hiddenWeightIn = neuron.getListOfWeightsIn().get(j);
                    value = value + hiddenWeightIn * ann.getTrainingDataset()[i][j];
                    //System.out.println(ann.getTrainingDataset()[i][j]);
                }
						
                valueOut = sigmoidActFunct(value);
                //System.out.println(valueOut);
                neuron.setOutput(valueOut);
                //System.out.println(neuron.getOutput());
            }		
        		
                
        double value = 0.0;
        double valueOut = 0.0;
        for (int j = 0; j < ann.getOutputLayer().getLayerNeuronsCount(); j++){
					
            for (Neuron neuron : hiddenLayer.getNeurons()) {
			
                double hiddenWeightOut = neuron.getListOfWeightsOut().get(j);
                value = value + hiddenWeightOut * neuron.getOutput();
            }
            valueOut = linearFunct(value);
            ann.getOutputLayer().getNeurons().get(j).setOutput(valueOut);
					
            calculatedOutput = valueOut;
            expectedOutput = ann.getOutputDataset()[i][j];
            double theError = expectedOutput - calculatedOutput;
            ann.getOutputLayer().getNeurons().get(j).setError(theError);
            sumError = sumError + Math.pow(theError, 2.0);
            }
				
            double meanError = sumError / ann.getOutputLayer().getLayerNeuronsCount();
            ann.setMeanError(meanError);		
            ann.getHiddenLayers().get(hiddenLayerIndex).setNeurons(hiddenLayer.getNeurons());
            hiddenLayerIndex++;		
        }
            
        return ann;
    }
    
   public NeuralNetwork backpropagate(NeuralNetwork ann, int i) { // i = row j = col

	ArrayList<Neuron> outputLayer = new ArrayList<>(ann.getOutputLayer().getNeurons());
	ArrayList<Neuron> hiddenLayer = new ArrayList<>(ann.getHiddenLayers().get(0).getNeurons());
		
	double neuronError;
	double neuronOutput;
	double aMomentum;
		
	//magnitude of output layer
	for (Neuron neuron : outputLayer) {
            
            neuronError = neuron.getError();
            neuronOutput = neuron.getOutput();
            aMomentum = sigmoidActFunct(neuronOutput) * neuronError;
            neuron.setMomentum(aMomentum);
            //System.out.println(neuronOutput);
	}
		
	ann.getOutputLayer().setNeurons(outputLayer);
		

	//momemntum of hidden layer(s)
	for (Neuron neuron : hiddenLayer) {
			
            aMomentum = 0.0;
		
            ArrayList<Double> listOfWeightsOut = new ArrayList<>(neuron.getListOfWeightsOut());	
            double anotherMomentum = 0.0;	
            int weight_i = 0;
		
            for (Double weight : listOfWeightsOut) {
                anotherMomentum = anotherMomentum + (weight * outputLayer.get(weight_i).getMomentum());
                //weight_i++;
                //System.out.println(anotherMomentum);
            }
				
            aMomentum = sigmoidActFunct(neuron.getOutput()) * anotherMomentum;		
            neuron.setMomentum(aMomentum);			
	}
		
	// Backpropagate from output layer to hidden layer
	for (int outLayer_i = 0; outLayer_i < ann.getOutputLayer().getLayerNeuronsCount(); outLayer_i++) {
			
            for (Neuron neuron : hiddenLayer) {
				
		double newWeight = neuron.getListOfWeightsOut().get(outLayer_i) + 
								(ann.getLearningCoefficient() * 
								  outputLayer.get( outLayer_i ).getMomentum() * 
								  neuron.getOutput());
		//System.out.println("neuron list of weights out is: "+ neuron.getListOfWeightsOut().get(outLayer_i));		
              //  System.out.println("ann learning coefficient is: "+ ann.getLearningCoefficient());
              //  System.out.println("output layer momentum is: "+ outputLayer.get( outLayer_i ).getMomentum());
              //  System.out.println("neuron output is is: "+ neuron.getOutput());
              //  System.out.println("newWeight is: "+ newWeight);
               // System.out.println();
		neuron.getListOfWeightsOut().set(outLayer_i, newWeight);
            }	
	}
		
	// Backpropagate from hidden layer to input layer
	for (Neuron neuron : hiddenLayer) {
			
            ArrayList<Double> hiddenLayerInputWeights = new ArrayList<>(neuron.getListOfWeightsIn());
			
            if(hiddenLayerInputWeights.size() > 0) {
			
		int hidden_i = 0;
		double newWeight = 0.0;
		for (int j = 0; j < ann.getInputLayer().getLayerNeuronsCount(); j++) {
					
                    newWeight = hiddenLayerInputWeights.get(hidden_i) +
							(ann.getLearningCoefficient() *
							  neuron.getMomentum() * 
							  ann.getTrainingDataset()[i][j]); 
					
		neuron.getListOfWeightsIn().set(hidden_i, newWeight);
					
		hidden_i++;
		}		
            }	
	}
		
	ann.getHiddenLayers().get(0).setNeurons(hiddenLayer);
	return ann;	
    }
    
    
   
    private double sigmoidActFunct(double aNumber) {
	return (1.0 / (1.0 + Math.exp(-aNumber)));
    }
    
    private double linearFunct(double aNumber) {
	return aNumber;
    }
    
    private double derSigmoidActFunct(double aNumber) {
	return (aNumber * (1.0 - aNumber));
    }

    public double getMse() {
	return this.MSE;
    }

    public void setMse(double mse) {
	this.MSE = mse;
    }
    
    public double getMae(){
        
        return this.MAE;
    }
    
    public void setMae(double mae){
        
        this.MAE = Math.abs(mae);
    }
    
}
