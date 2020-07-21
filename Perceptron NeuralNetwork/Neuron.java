package netcore;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Piotr Berger E5109181
 * version 1.0
 * April 2019
 */
public class Neuron {
    
    //Instance variables
    
    /*
    *   Contains a collection of Double objects 
    *   representing the input weights of a neuron.
    */
    private ArrayList<Double> listOfWeightsIn;
    /*
    *   Contains a collection of Double objects 
    *   representing the output weights of a neuron.
    */
    private ArrayList<Double> listOfWeightsOut;
    private double output;
    private double error;
    private double momentum;
    
    // Methods
    
    /*
    *   Returns pseudo random positive or negative
    *   real number for neuron weight initialisation.
    */
    public double neuronRandomWeight(){
        
        Random r = new Random();
        return r.nextDouble();
        
        //return (Math.random()*((2.0-1.0)+1))-1.0;
    }
    
    
    /*
    *   Returns a defensive copy of an array list 
    *   of input weights of recipient neuron.
    */
    public ArrayList<Double> getListOfWeightsIn(){
        
        return this.listOfWeightsIn;
    }
    
    /*
    *   Returns a defensive copy of an array list 
    *   of output weights of recipient neuron.
    */
    public ArrayList<Double> getListOfWeightsOut(){
        
        return this.listOfWeightsOut;
    }
    
    public double getOutput(){
        
        return this.output;
    }
    
    public double getError(){
        
        return this.error;
    }
    
    public double getMomentum(){
        
        return this.momentum;
    }
    
    /*
    *   Sets input weights of recipient neuron
    *   to the argument array list values.
    */
    public void setListOfWeightsIn(ArrayList<Double> weightsIn){
        
        this.listOfWeightsIn = weightsIn;
    }
    
    /*
    *   Sets output weights of recipient neuron
    *   to the argument array list values.
    */
    public void setListOfWeightsOut(ArrayList<Double> weightsOut){
        
        this.listOfWeightsOut = weightsOut; 
    }
    
    public void setOutput(double anOutput){
        
        this.output = anOutput;
    }
    
    public void setError(double anError){
        
        this.error = anError;
    }
    
    public void setMomentum(double aMomentum){
        
        this.momentum = aMomentum;
    }
}
