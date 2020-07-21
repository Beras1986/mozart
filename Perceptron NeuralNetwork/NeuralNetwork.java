package netcore;

import java.util.ArrayList;
/**
 *
 * @author Piotr Berger E5109181
 * version 1.0
 * April 2019
 */
public class NeuralNetwork {
    
    //Instance variables
    
    private InputLayer inputLayer;
    private HiddenLayer hiddenLayer;
    private OutputLayer outputLayer;
    private int numberOfHiddenLayers; 
    private double[][] trainingDataset;
    private double[][] validationDataset;
    private double[][] outputDataset;
    private double[] annOutput;
    private int maxEpochs;
    private double learningCoefficient;
    private double acceptedError;
    private double realError;
    private double meanError;
    private double mse;
    private double mae;
    private ArrayList<Double> msErrors = new ArrayList<>();
    
    //Links
    
    private ArrayList<HiddenLayer> hiddenLayers;

    //Methods
    
    public NeuralNetwork neuralNetworkInit(int inputNeuronsCount, 
                                          int numberOfHiddenLayers,
                                          int hiddenNeuronsCountPerLayer){
        
	inputLayer = new InputLayer();
	inputLayer.setNeurons(inputNeuronsCount);
		
	hiddenLayers = new ArrayList<>();
	for (int i = 0; i < numberOfHiddenLayers; i++) {
		hiddenLayer = new HiddenLayer();
		hiddenLayer.setNeurons(hiddenNeuronsCountPerLayer);
		hiddenLayers.add(hiddenLayer);
	}
		
	outputLayer = new OutputLayer();
	outputLayer.setNeurons(1);
		
	inputLayer = inputLayer.inputLayerInit(inputLayer);
		
	if(numberOfHiddenLayers > 0) {
		hiddenLayers = hiddenLayer.hiddenLayerInit(hiddenLayer, hiddenLayers, inputLayer, outputLayer);
	}

	outputLayer = outputLayer.outputLayerInit(outputLayer);
		
	NeuralNetwork ann = new NeuralNetwork();
	ann.setInputLayer(inputLayer);
	ann.setHiddenLayer(hiddenLayer);
	ann.setHiddenLayers(hiddenLayers);
	ann.setNumberOfHiddenLayers(numberOfHiddenLayers);
	ann.setOutputLayer(outputLayer);
	
	return ann;
    }
    
    public NeuralNetwork trainAnn(NeuralNetwork ann){

	NeuralNetwork trainedAnn = new NeuralNetwork();
	Train train = new Train();
	trainedAnn = train.trainNetwork(ann);
        //trainedAnn.printNet();
	return trainedAnn;		
    }
    
    public double[][] getNetworkOutput(NeuralNetwork trainedNet){
        
        int rows = trainedNet.getTrainingDataset().length;
        int cols = trainedNet.getOutputLayer().getLayerNeuronsCount();
        double[][] networkOutput = new double[rows][cols];
        
        Train aTrain = new Train();
        
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < cols ; j++){
                
                networkOutput[i][j] = 
                aTrain.forwardPass(trainedNet, i).getOutputLayer().getNeurons().get(j).getOutput();
            }
        }
        return networkOutput;
    }
    
    public InputLayer getInputLayer(){
        
        return this.inputLayer;
    }
    
    public void setInputLayer(InputLayer anInputLayer){
        
        this.inputLayer = anInputLayer;
    }
    
    public HiddenLayer getHiddenLayer(){
        
        return this.hiddenLayer;
    }
    
    public void setHiddenLayer(HiddenLayer aHiddenLayer){
        
        this.hiddenLayer = aHiddenLayer;
    }
    
    public ArrayList<HiddenLayer> getHiddenLayers(){
        
        return this.hiddenLayers;
    }
    
    public void setHiddenLayers(ArrayList<HiddenLayer> theHiddenLayers){
        
        this.hiddenLayers = theHiddenLayers;
    }
    
    public int getNumberOfHiddenLayers(){
        
        return this.numberOfHiddenLayers;
    }
    
    public void setNumberOfHiddenLayers(int aNumber){
        
        this.numberOfHiddenLayers = aNumber;
    }
    
    public OutputLayer getOutputLayer(){
        
        return this.outputLayer;
    }
    
    public void setOutputLayer(OutputLayer anOutputLayer){
        
        this.outputLayer = anOutputLayer;
    }
    
    public int getMaxEpochs(){
        
        return this.maxEpochs;
    }
    
    public void setMaxEpochs(int aNumber){
        
        this.maxEpochs = aNumber;
    }
    
    public double[][] getTrainingDataset(){
        
        return this.trainingDataset;
    }
    
    public void setTrainingDataset(double[][] dataset){
        
        this.trainingDataset = dataset;
    }
    
    public double[][] getValidationDataset(){
        
        return this.validationDataset;
    }
    
    public void setValidationDataset(double[][] dataset){
        
        this.validationDataset = dataset;
    }
    
    public double[] getAnnOutput(){
        
        return this.annOutput;
    }
    
    public void setAnnOutput(double[] output){
        
        this.annOutput = output;
    }
    
    public double getAcceptedError(){
        
        return this.acceptedError;
    }
    
    public void setAcceptedError(double aNumber){
        
        this.acceptedError = aNumber;
    }
    
    public double getRealError(){
     
        return this.realError;
    }
    
    public void setRealError(double aNumber){
        
        this.realError = aNumber;
    }
    
    public double getMeanError(){
        
        return this.meanError;
    }
    
    public void setMeanError(double aNumber){
        
        this.meanError = aNumber;
    }
    
    public ArrayList<Double> getMsErrors(){
        
        return this.msErrors;
    }
    
    public void setMsErrors(ArrayList<Double> msErrors){
        
        this.msErrors = msErrors;
    }
    
    public void addMsError(double error){
        
        this.msErrors.add(error);
    }
    
    public double getLearningCoefficient(){
        
        return this.learningCoefficient;
    }
    
    public void setLearningCoefficient(double coefficient){
        
        this.learningCoefficient = coefficient;
    }
    
    public double[][] getOutputDataset(){
        
        return this.outputDataset;
    }
    
    public void setOutputDataset(double[][] dataset){
        
        this.outputDataset = dataset;
    }
    
    public void setMae(double aMae){
        
        this.mae = aMae;
    }
    
    public double getMae(){
        
        return this.mae;
    }
    
    public void setMse(double aMse){
        
        this.mse = aMse;
    }
    
    public double getMse(){
    
        return this.mse;
    }   
    
    public void printNet(){
	inputLayer.printLayer(inputLayer);
	System.out.println();
	hiddenLayer.printLayer(hiddenLayers);
	System.out.println();
	outputLayer.printLayer(outputLayer);
	}
}
