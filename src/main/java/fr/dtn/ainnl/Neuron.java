package fr.dtn.ainnl;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a neuron
 */
public class Neuron {
    /**
     * The id of layer, that is his position in the network, and the id of the neuron, that is his position in the layer
     */
    private final short layer, id;
    /**
     * All the axon which the neuron is connected from and to
     */
    private final List<Axon> inputs, outputs;
    /**
     * The value stored in the neuron
     */
    private float value;

    /**
     * Constructor of the class
     * @param layer The id of the layer of the neuron, that is his position in the network
     * @param id The id of the neuron, that is his position in the layer
     */
    public Neuron(short layer, short id){
        this.layer = layer;
        this.id = id;

        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();

        this.value = 0f;
    }

    /**
     * Method to connect the neuron into another one by an axon, the local neuron is the "from" of the axon
     * @param to The neuron that the local neuron will be connected to
     * @param weight The weight of the axon
     */
    public void connectTo(Neuron to, float weight){
        Axon axon = new Axon(this, to, weight);

        this.outputs.add(axon);
        to.inputs.add(axon);
    }

    /**
     * Method that takes all the inputs of the neuron and calculate their sum, to be the new value of the neuron
     */
    public void compute(){
        float sum = 0f;
        
        for(Axon input : inputs)
            sum += input.getFrom().getValue() * input.getWeight();
        
        this.value = sum;
    }

    /**
     * The getters of the class attributes
     */
    public short getLayer(){ return layer; }
    public short getId(){ return id; }
    public List<Axon> getInputs(){ return inputs; }
    public List<Axon> getOutputs(){ return outputs; }
    public float getValue(){ return value; }

    /**
     * The setters of the class attributes
     */
    public void setValue(float value) { this.value = value; }

    /**
     * Convert the object into a readable string
     * @return Neuron{l="", n="", in=[], out=[], v=}
     */
    @Override public String toString() {
        return "Neuron{" +
                "l=" + layer +
                ", n=" + id +
                ", in=" + inputs +
                ", out=" + outputs +
                ", v=" + value +
                '}';
    }
}