package fr.dtn.ainnl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a neuron
 */
public class Network {
    /**
     * All the layers of the network
     */
    private final List<Layer> layers;

    /**
     * Constructor of the class
     */
    public Network(){
        this.layers = new ArrayList<>();
    }

    /**
     * Method to add a layer to the network
     * @param layer The layer to add
     */
    public void addLayer(Layer layer){ layers.add(layer); }

    /**
     * Method to compute all the network with givens inputs
     * @param inputs The values in the first layer of the network
     * @return The values of the last layer of the network into a float array
     */
    public float[] compute(float[] inputs){
        Layer last = layers.get(layers.size() - 1);
        float[] compute = new float[last.getDepth()];

        for(short i = 0; i < getDepth(); i++) {
            if (i == 0)
                layers.get(i).compute(inputs);
            else
                layers.get(i).compute();
        }

        for(short i = 0; i < compute.length; i++)
            compute[i] = last.getNeurons().get(i).getValue();

        return compute;
    }

    /**
     * Method to render the network visually with a given Graphics2D object
     * @param g The Graphics2D object that permits to render the network
     * @param scale The size of a neuron
     * @param depth The space in pixels between two rendered layers
     * @param offsetX The left margin of the rendered network
     * @param offsetY The top margin of the rendered network
     * @param containerWidth The width of the container which the network will be drawn in
     * @param containerHeight The height of the container which the network will be drawn in
     */
    public void render(Graphics2D g, int scale, int depth, int offsetX, int offsetY, int containerWidth, int containerHeight){
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, scale/(scale/12)));
        g.fillRect(0, 0, containerWidth, containerHeight);

        for(short l = 0; l < getDepth(); l++){
            Layer layer = layers.get(l);

            g.setColor(Color.white);

            for(short n = 0; n < layer.getDepth(); n++){
                Neuron from = layer.getNeuron(n);
                g.setColor(Color.red);
                g.drawString(String.valueOf(from.getValue()), offsetX + 10 + scale/8 + from.getLayer() * (scale + depth), offsetY + 10 + from.getId() * (scale + 10)+scale/2+scale/16);

                final int x = offsetX + 10 + from.getLayer() * (scale + depth);
                final int y = offsetY + 10 + from.getId() * (scale + 10);

                g.setColor(Color.white);
                g.drawOval(x, y, scale, scale);

                for(Axon axon : from.getOutputs()){
                    Neuron to = axon.getTo();

                    int startX = offsetX + 10 + from.getLayer() * (scale + depth) + scale;
                    int startY = offsetY + 10 + from.getId() * (scale + 11) + scale/2;
                    int endX = offsetX + 10 + to.getLayer() * (scale + depth);
                    int endY = offsetY + 10 + to.getId() * (scale + 11) + scale/2;

                    g.drawLine(startX, startY, endX, endY);
                }
            }
        }
    }

    /**
     * Method to render the network into a new window
     * @param scale The size of a neuron
     * @param depth The space in pixels between two rendered layers
     */
    public void render(int scale, int depth){
        JFrame frame = new JFrame("NAINL Network View");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new JPanel(){
            @Override protected void paintComponent(Graphics g) {
                render((Graphics2D) g, scale, depth, 0, 0, getWidth(), getHeight());
                g.dispose();
            }
        });

        frame.setVisible(true);
    }

    /**
     * Getters of the class
     */
    public Layer getLayer(short id){ return layers.get(id); }
    public short getDepth(){ return (short)layers.size(); }
    public List<Layer> getLayers(){ return layers; }
}