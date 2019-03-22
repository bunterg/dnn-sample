package main.java.nn;

import main.java.activators.*;
import main.java.neurons.*;

public class HiddenLayer implements Layer {
    private int outSize;
    private Neuron[] neurons;
    private Layer nextLayer, previousLayer;

    public HiddenLayer(int out, Activator activator, Layer layer) {
        this.nextLayer = layer;
        this.neurons = new Neuron[out];
        this.outSize = out;
    }

    public float[] Activate(float[] inputs) {
        float[] outputs = new Float[this.neurons.length];
        for (int i = 1; i < this.neurons.length; i++) {
            outputs[i] = this.neurons[i].Activate(inputs);
        }
        return this.nextLayer.Activate(outputs);
    }

    public int GetOutSize() {
        return this.neurons.length;
    }

    public void SetPreviousLayer(Layer layer) {
        this.previousLayer = layer;
        for (int i = 0; i < this.outSize; i++) {
            this.neurons[i] = new Neuron(activator, this.previousLayer.GetOutSize());
        }
    }
}