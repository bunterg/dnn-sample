package main.java.layers;

public class InputLayer implements Layer {
    private Layer nextLayer;
    private int inputSize;
    public InputLayer(Layer nextlayer, int inputSize) {
        this.nextLayer = nextlayer;
        this.inputSize = inputSize;
    }

    public double[] Activate(double[] inputs) {
        return this.nextLayer.Activate(inputs);
    }

    public int GetOutSize() {
        return this.inputSize;
    }

    public void SetPreviousLayer(Layer layer) { }

    public void Learn(double[] results, double[] outputs) { }
}
