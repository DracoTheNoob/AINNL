package fr.dtn.ainnl;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a layer in the network
 */
public class Layer {
    /**
     * The id of the layer, that is his position in the network
     */
    private final short id;
    /**
     * The neurons that are in the layer
     */
    private final List<Neuron> neurons;

    /**
     * Constructor of the class
     * @param id The id of the layer
     */
    public Layer(short id){
        this.id = id;
        this.neurons = new ArrayList<>();
    }

    /**
     * Method to add a new neuron into the layer
     * @param neuron The neuron to add
     */
    public void addNeuron(Neuron neuron){ this.neurons.add(neuron); }

    /**
     * Method to set all the neurons of the layer a certain value, method used to fill the input layer values
     * @param inputs The given inputs
     */
    public void compute(float[] inputs){
        assert inputs.length == getDepth();

        for(short i = 0; i < getDepth(); i++)
            neurons.get(i).setValue(inputs[i]);
    }

    /**
     * Method to compute all the neurons of the layer using their inputs
     */
    public void compute(){ neurons.forEach(Neuron::compute); }

    /**
     * Getters of the layer
     */
    public short getId(){ return id; }
    public Neuron getNeuron(short id){ return neurons.get(id); }
    public List<Neuron> getNeurons(){ return neurons; }
    public short getDepth(){ return (short)neurons.size(); }
}