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

    public float[] Run(float[] inputs) {
        return this.inputLayer.Activate(inputs);
    }

    private void RunEpoch(float[][] inputsArr, float[][] outputsArr) {
        for (int i = 0; i < inputsArr.length; i++) {
            float[] inputs = inputsArr[i];
            float[] outputs = outputsArr[i];
            float[] results = this.run(inputs);
            this.outputLayer.Learn(outputLayer);
        }
    }

    public void Train(float[][] inputs, float[][] outputs, int epoch) {
        for (int i = 0; i < epoch; i++) {
            this.RunEpoch(inputs, outputs);
        }
    }
}