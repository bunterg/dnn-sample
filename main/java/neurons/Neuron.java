package main.java.neurons;

import main.java.activators.*;

public class Neuron {
    private Activator activator;
    private double[] weights;
    private double[] lastInputs;
    private final double learningRate = 0.01;

    public Neuron(Activator activator, int inputSize) {
        this.activator = activator;
        // Initialize all weights with small random numbers, between -1 and 1 
        this.weights = new double[inputSize + 1];
        for (int i = 0; i < inputSize + 1; i++) {
            this.weights[i] = (Math.random() - 0.5) * 2;
        }
    }

    public double Activate(double[] inputs) {
        this.lastInputs = inputs;
        double total = this.weights[0];
        for (int i = 0; i < this.lastInputs.length; i++) {
            total+=this.lastInputs[i] * this.weights[i+1];
        }
        return this.activator.Activate(total);
    }

    public double UpdateWeights(double result, double output) {
        double deltaOut = result - output;
        double errorSignal = deltaOut * activator.Derivative(output);
        this.weights[0] = this.weights[0] + this.learningRate * errorSignal;
        for (int i = 1; i < this.weights.length; i++) {
            this.weights[i] = this.weights[i] + this.learningRate * errorSignal * this.lastInputs[i-1];
        }
        return errorSignal;
    }
}