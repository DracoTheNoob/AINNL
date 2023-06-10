package fr.dtn.ainnl;

/**
 * This class represents the axon linking two neurons
 */
public class Axon {
    /**
     * The "from" neuron is the neuron that send his value to the "to" one
     */
    private final Neuron from, to;
    /**
     * The weight is the number that will be multiplied, to the value of the "from" neuron to send the result to the "to" neuron
     */
    private float weight;

    /**
     * Constructor of the class
     * @param from The neuron which the axon is connected from
     * @param to The neuron which the axon is connected to
     * @param weight The value that multiply the value of the "from" neuron
     */
    public Axon(Neuron from, Neuron to, float weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * The getters of the class attributes
     */
    public Neuron getFrom(){ return from; }
    public Neuron getTo(){ return to; }
    public float getWeight(){ return weight; }

    /**
     * The setters of the class attributes
     */
    public void setWeight(float weight){ this.weight = weight; }

    /**
     * Convert the object into an understandable string of the axon
     * @return {"from" > "to" * weight}
     */
    @Override public String toString() {
        return "{(" + from.getLayer() + ';' + from.getId() + ") > (" + to.getLayer() + ';' + to.getId() + ") * " + weight + '}';
    }
}