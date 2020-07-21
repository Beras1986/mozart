package netcore;

import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Piotr Berger E5109181
 * version 1.0
 * April 2019
 */
public class OutputLayer extends Layer {
    
    
    //Methods
    
    /*
    *   The method initialise neurons of recipient 
    *   output layer. Iterates creating new neuron,
    *   over the number of neurons in output layer,
    *   assignes random values to output weights of
    *   created neuron and assigns this neuron to 
    *   the recipient output layer neurons collection.
    */
    public OutputLayer outputLayerInit(OutputLayer outputLayer){
        
        
        ArrayList<Double> weightsOut = new ArrayList<>();
        ArrayList<Neuron> neurons = new ArrayList<>();
        
        for(int i=0 ; i<outputLayer.getLayerNeuronsCount() ; i++){
            
            Neuron aNeuron = new Neuron();
            
            weightsOut.add(aNeuron.neuronRandomWeight());
            aNeuron.setListOfWeightsOut(weightsOut);
            neurons.add(aNeuron);
            
            weightsOut = new ArrayList<>();
        }
        outputLayer.setNeurons(neurons);
        return outputLayer;
        }
    
    /*
    *   Prints out output layer, its neurons
    *   and their weights.
    */
    public void printLayer(OutputLayer outputLayer){
		System.out.println("__________OUTPUT LAYER__________");
		int i = 1;
        for (Neuron neuron : outputLayer.getNeurons()) {
            System.out.println("Neuron No " + i + ": ");
            System.out.println("Output Weights: ");
            System.out.println(Arrays.deepToString( neuron.getListOfWeightsOut().toArray() ));
            i++;
        }
    }
    
    public void setNeurons(int neuronCount){
        
        this.layerNeuronsCount = neuronCount;
        }
}
