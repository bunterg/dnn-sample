package main.java.nn;

import main.java.layers.*;
import main.java.activators.*;
public class FeedForward {
    private InputLayer inputLayer;
    private OutputLayer outputLayer;

    public FeedForward(int inputs, int outputs, int[] hidden, Activator activator) {
        this.outputLayer = new OutputLayer(outputs, activator);
        Layer lastLayer = this.outputLayer;
        for (int i = 0; i < hidden.length; i++) {
            Layer layer = new HiddenLayer(hidden[i], activator, lastLayer);
            lastLayer.SetPreviousLayer(layer);
            lastLayer = layer;
        }
        this.inputLayer = new InputLayer(lastLayer, inputs);
        lastLayer.SetPreviousLayer(this.inputLayer);
    }

    public double[] Run(double[] inputs) {
        return this.inputLayer.Activate(inputs);
    }

    private void RunEpoch(double[][] inputsArr, double[][] outputsArr) {
        for (int i = 0; i < inputsArr.length; i++) {
            double[] inputs = inputsArr[i];
            double[] outputs = outputsArr[i];
            double[] results = this.Run(inputs);
            this.outputLayer.Learn(results, outputs);
        }
    }

    public void Train(double[][] inputs, double[][] outputs, int epoch) {
        for (int i = 0; i < epoch; i++) {
            this.RunEpoch(inputs, outputs);
        }
    }
}