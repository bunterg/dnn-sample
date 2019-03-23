package main.java.nn;

import main.java.activators.*;
import main.java.neurons.*;

public class HiddenLayer implements Layer {
    private int outSize;
    private Neuron[] neurons;
    private Activator activator;
    private Layer nextLayer, previousLayer;

    public HiddenLayer(int out, Activator activator, Layer layer) {
        this.activator = activator;
        this.nextLayer = layer;
        this.neurons = new Neuron[out];
        this.outSize = out;
    }

    public double[] Activate(double[] inputs) {
        double[] outputs = new double[this.neurons.length];
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
            this.neurons[i] = new Neuron(this.activator, this.previousLayer.GetOutSize());
        }
    }

    public void Learn(double[] results, double[] outputs) {
        for (int i = 0; i < this.neurons.length; i++) {
            this.neurons[i].UpdateWeights(results[i], outputs[i]);
        }
        this.previousLayer.Learn(results, outputs);
    }
}