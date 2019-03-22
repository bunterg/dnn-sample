package main.java.neurons;

import main.java.activators.*;

public abstract class Neuron {
    private Activator activator;
    private float[] weights;

    public Neuron(Activator activator, int inputSize) {
        this.activator = activator;
        this.weights = new float[inputSize + 1];
        for (int i = 0; i < inputSize; i++) {
            this.weights[i] = Math.random();
        }
    }

    public float Activate(float[] inputs) {
        float total = this.weights[0];
        for (int i = 0; i < inputs.length; i++) {
            total+=inputs[i] * this.weights[i+1];
        }
        return this.activator.Activate(total);
    }
}