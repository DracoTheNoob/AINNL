# Artificial Intelligence Neural Network Library
AINNL is tool to help making neural networks in Java.

## Creating a first network ##
First, to create our network, we need to create an instance of the Network class :
```java
Network network = new Network();
```

## Adding a first layer ##
But now, the network is empty, we need to add layers to it :
```java
Network network = new Network();
Layer layer = new Layer((short)0);

network.addLayer(layer);
```
The "(short)0" is the id of the layer, it is 0 because the layer is the first of the network.

## Adding a first neuron ##
But now, it's the layer that is empty, let's add a neuron to it :
```java
Network network = new Network();
Layer layer = new Layer((short)0);

Neuron neuron = new Neuron(layer.getId(), (short)0);
layer.addNeuron(neuron);

network.addLayer(layer);
```

## Render network on window ##
Fine ! We now have a network that contains a neuron ! Let's render it on a window :
```java
Network network = new Network();
Layer layer = new Layer((short)0);

Neuron neuron = new Neuron(layer.getId(), (short)0);
layer.addNeuron(neuron);

network.addLayer(layer);
network.render(80, 70);
```
The 80 and 70 parameters are respectivly the size in pixels of a neuron and the space between the differents layers of the network, but we have only 1 layer, then this value is not important yet.
With this new line, it's supposed to render a single neuron on the screen. The default value of a neuron is 0, then the value displayed in the circle representing the neuron is 0 too.

## Adding more neurons and layers ##
Now let's add a few more neurons in our layer, using a for loop :
```java
Network network = new Network();
Layer layer = new Layer((short)0);

for(short i = 0; i < 3; i++){
  Neuron neuron = new Neuron(layer.getId(), i);
  layer.addNeuron(neuron);
}

network.addLayer(layer);
network.render(80, 70);
```
With this new version, our layer is now containing 3 neurons !

But for a working network, we need more than a single layer, let's add more using another for loop :
```java
Network network = new Network();

for(short l = 0; l < 4; l++){
  Layer layer = new Layer(l);
  
  for(short n = 0; n < 3; n++){
    Neuron neuron = new Neuron(l, n);
    layer.addNeuron(neuron);
  }

  network.addLayer(layer);
}

network.render(80, 70);
```

## Connect neurons to other ones ##
Fine ! We now have 4 layers into our network. But, visually, we can't see axons between neurons of different layers, it's because we didn't connected the neurons between them yet. Let's do it :
```java
Network network = new Network();

for(short l = 0; l < 4; l++){
  Layer layer = new Layer(l);
  
  for(short n = 0; n < 3; n++){
    Neuron neuron = new Neuron(l, n);
    
    // Check that the current layer is not the first, because the first layer do not have input axons
    if(l != 0){
      // Loop all the neurons of the previous layer to connect them to our new neuron
      for(Neuron previous : network.getLayer((short)(network.getDepth()-1)).getNeurons()){
        // The new axon is created with a random weight between 0 and 1
        previous.connectTo(neuron, (float)Math.random());
      }
    }
    
    layer.addNeuron(neuron);
  }

  network.addLayer(layer);
}

network.render(80, 70);
```
Done ! We now have a real network that can work !

## Compute the network ##
To make our network working with inputs, we need to have inputs, the following method generate random float values between 0 include and 1 exclude :
```java
private static float[] getRandomInputs(int depth){
  float[] inputs = new float[depth];
  
  for(int i = 0; i < depth; i++)
    inputs[i] = (float)Math.random();
  
  return inputs;
}
```
And now, we can use this method to compute the network :
```java
Network network = new Network();

// Filling the network with neurons and layers like made before

network.compute(getRandomInputs(network.getLayer((short)0).getDepth()));
network.render(80, 70);
```
Fine ! By executing this code, the values of the network that are displayed are now random numbers : our network takes inputs and generates outputs ! You can now use all this stuff to make way more complex networks.


In theory, you can create layer with id from 0 to 32'767, because the layer id is short number.
Same the layer, so the maximum amount of neurons in a network is 32'767² = 1'073'676'289.
I think this is a sufficient amount of neurons.