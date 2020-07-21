package netcore;

import java.util.ArrayList;


/**
 *
 * @author Piotr Berger E5109181
 * version 1.0
 * April 2019
 */
public abstract class Layer {
    
    //Instance variables
    
    /*
    *   Represents the number of neurons
    *   in the layer.
    */
    protected int layerNeuronsCount;
   
    /*
    *   Represents an array list of Neuron
    *   objects in the layer.
    */
    private ArrayList<Neuron> neurons; 
   
    /*
    *   Returns a defensive copy of an array list 
    *   of Neuron objects linked to recipient layer.
    */
    public ArrayList<Neuron> getNeurons(){
        
        return new ArrayList<>(this.neurons); //defensive copy
    }
    
    /*
    *   Returns the number of neurons in
    *   the recipient layer.
    */
    public int getLayerNeuronsCount(){
        
        return this.layerNeuronsCount;
    }

    
    /*
    *   Sets the collection of Neuron objects linked
    *   to recipient layer to the argument collection.
    */
    public void setNeurons(ArrayList<Neuron> neurons){
        
        this.neurons = neurons;
    }
    
    /*
    *   Sets the number of neurons variable of
    *   recipient layer to the argument int recived.
    */
    public void setLayerNeuronsCount(int number){
        
        this.layerNeuronsCount = number;
    }
}
