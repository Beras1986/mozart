package netcore;

import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Piotr Berger E5109181
 * version 1.0
 * April 2019
 */
public class HiddenLayer extends Layer {
    
    
    //Methods
    
    /*
    *   The method initialise neurons of recipient 
    *   hidden layer, in this case inputs nad outputs. 
    *   Iterates creating new neuron, over the number
    *   of hidden layers and iterates over the number
    *   of neurons in each hidden layer assignes 
    *   random values to input and output weights of
    *   created neurons and assigns those neurons to 
    *   the recipient output hidden layer neurons collection.
    */
    public ArrayList<HiddenLayer> hiddenLayerInit(HiddenLayer hiddenLayer, 
            ArrayList<HiddenLayer> hiddenLayersList,InputLayer inputLayer, OutputLayer outputLayer){
            
        ArrayList<Double> weightsIn = new ArrayList<>();
	ArrayList<Double> weightsOut = new ArrayList<>();
	ArrayList<Neuron> neurons = new ArrayList<>();
        
        int hiddenLayersNumber = hiddenLayersList.size();
        
        for (int i=0; i<hiddenLayersNumber; i++) {
            for (int j=0; j<hiddenLayer.getLayerNeuronsCount(); j++) {
		Neuron neuron;
                neuron = new Neuron();

		int maxIn;
		int maxOut;

		if (i == 0) { // first
			maxIn = inputLayer.getLayerNeuronsCount();
			if (hiddenLayersNumber>1) {
				maxOut = hiddenLayersList.get(i+1).getLayerNeuronsCount();
			} else {
				maxOut = hiddenLayersList.get(i).getLayerNeuronsCount();
			}
		} else if (i==hiddenLayersNumber-1) { // last
			maxIn=hiddenLayersList.get(i-1).getLayerNeuronsCount();
			maxOut = outputLayer.getLayerNeuronsCount();
		} else { // middle
			maxIn = hiddenLayersList.get(i-1).getLayerNeuronsCount();
			maxOut = hiddenLayersList.get(i+1).getLayerNeuronsCount();
		}

		for (int k=0; k<maxIn; k++) {
			weightsIn.add(neuron.neuronRandomWeight());
		}
		for (int k=0; k<maxOut; k++) {
			weightsOut.add(neuron.neuronRandomWeight());
		}

		neuron.setListOfWeightsIn(weightsIn);
		neuron.setListOfWeightsOut(weightsOut);
		neurons.add(neuron);

		weightsIn = new ArrayList<>();
		weightsOut = new ArrayList<>();

	}

	hiddenLayersList.get(i).setNeurons(neurons);

	neurons = new ArrayList<>();

        }

        return hiddenLayersList;
    }
    
    public void printLayer(ArrayList<HiddenLayer> listOfHiddenLayer) {
		System.out.println("__________HIDDEN LAYER__________");
		int i = 1;
		for (HiddenLayer hiddenLayer : listOfHiddenLayer) {
			System.out.println("Hidden Layer No " + i);
			int j = 1;
			for (Neuron neuron : hiddenLayer.getNeurons()) {
				System.out.println("Neuron No " + j);
				System.out.println("Input Weights: ");
				System.out.println(Arrays.deepToString( neuron.getListOfWeightsIn().toArray() ));
				System.out.println("Output Weights: ");
				System.out.println(Arrays.deepToString( neuron.getListOfWeightsOut().toArray() ));
				j++;
			}
			i++;
		}
	}
    
    public void setNeurons(int neuronCount){
        
        this.layerNeuronsCount = neuronCount;
        }
}
