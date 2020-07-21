package netcore;

import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Piotr Berger E5109181
 * version 1.0
 * Aril 2019
 */
public class InputLayer extends Layer {
       
    //Methods
    
    /*
    *   The method initialise neurons of recipient 
    *   input layer. Iterates creating new neuron,
    *   over the number of neurons in input layer,
    *   assignes random values to input weights of
    *   created neuron and assigns this neuron to 
    *   the recipient input layer neurons collection.
    */
    public InputLayer inputLayerInit(InputLayer inputLayer){
         
        ArrayList<Double> weightsIn = new ArrayList<>();
        ArrayList<Neuron> neurons = new ArrayList<>();
        
        for(int i=0 ; i<inputLayer.getLayerNeuronsCount() ; i++){
            
            Neuron aNeuron = new Neuron();
            
            weightsIn.add(aNeuron.neuronRandomWeight());
            aNeuron.setListOfWeightsIn(weightsIn);
            neurons.add(aNeuron);
            
            weightsIn = new ArrayList<>();
        }
        inputLayer.setNeurons(neurons);
        return inputLayer;
        }
    
    /*
    *   Prints out input layer, its neurons
    *   and their weights.
    */
    public void printLayer(InputLayer inputLayer){
		System.out.println("__________INPUT LAYER__________");
		int i = 1;
		for (Neuron neuron : inputLayer.getNeurons()) {
			System.out.println(String.format("Neuron No %s:", i));
			System.out.println("Input Weights: ");
			System.out.println(Arrays.deepToString( neuron.getListOfWeightsIn().toArray() ));
			i++;
		}
	}
    
    public void setNeurons(int neuronCount){
        
        this.layerNeuronsCount = neuronCount;
        }
    
    }
    

