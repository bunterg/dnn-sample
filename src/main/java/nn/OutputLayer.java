package main.java.nn;

import main.java.neurons.*;
import main.java.activators.*;

public class OutputLayer implements Layer {
    private int outSize;
    private Activator activator;
    private Neuron[] neurons;
    private Layer previousLayer;

    public OutputLayer(int out, Activator activator) {
        this.activator = activator;
        this.neurons = new Neuron[out];
        this.outSize = out;
    }

    public double[] Activate(double[] inputs) {
        double[] outputs = new double[this.neurons.length];
        for (int i = 0; i < this.neurons.length; i++) {
            outputs[i] = this.neurons[i].Activate(inputs);
        }
        return outputs;
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