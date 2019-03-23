package main.java.nn;

import main.java.activators.*;
public class FeedForward {
    private InputLayer inputLayer;
    private OutputLayer outputLayer;

    public FeedForward(int inputs, int outputs, int[] hidden, Activator activator) {
        OutputLayer outputLayer = new OutputLayer(outputs, activator);
        Layer lastLayer = outputLayer;
        for (int i = hidden.length - 1; i >= 0; i--) {
            Layer layer = new HiddenLayer(lastLayer.GetOutSize(), activator, lastLayer);
            lastLayer.SetPreviousLayer(layer);
            lastLayer = layer;
        }
        this.inputLayer = new InputLayer(lastLayer, inputs);
    }

    public double[] Run(double[] inputs) {
        return this.inputLayer.Activate(inputs);
    }

    private void RunEpoch(double[][] inputsArr, double[][] outputsArr) {
        for (int i = 0; i < inputsArr.length; i++) {
            double[] inputs = inputsArr[i];
            double[] outputs = outputsArr[i];
            double[] results = this.run(inputs);
            this.outputLayer.Learn(results, outputs);
        }
    }

    public void Train(double[][] inputs, double[][] outputs, int epoch) {
        for (int i = 0; i < epoch; i++) {
            this.RunEpoch(inputs, outputs);
        }
    }
}