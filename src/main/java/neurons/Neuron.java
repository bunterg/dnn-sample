package main.java.neurons;

import main.java.activators.*;

public abstract class Neuron {
    private Activator activator;
    private float[] weights;
    private float[] lastInputs;
    private float learningRate = 0.01;

    public Neuron(Activator activator, int inputSize) {
        this.activator = activator;
        // Initialize all weights with small random numbers, between -1 and 1 
        this.weights = new float[inputSize + 1];
        for (int i = 0; i < inputSize + 1; i++) {
            this.weights[i] = (Math.random() - 0.5) * 2;
        }
    }

    public float Activate(float[] inputs) {
        this.lastInputs = inputs;
        float total = this.weights[0];
        for (int i = 0; i < this.lastInputs.length; i++) {
            total+=this.lastInputs[i] * this.weights[i+1];
        }
        return this.activator.Activate(total);
    }

    public float UpdateWeights(float result, float output) {
        float deltaOut = result - output;
        float errorSignal = deltaOut * activator.Derivative(output);
        this.weights[0] = this.weights[0] + this.learingRate * errorSignal;
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i+1] = this.weights[i+1] + this.learingRate * errorSignal * this.lastInputs[i];
        }
        return errorSignal;
    }
}