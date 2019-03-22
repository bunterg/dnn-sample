package main.java.nn;

public class InputLayer implements Layer {
    private Layer nextLayer;
    private int inputSize;
    public InputLayer(Layer nextlayer, int inputSize) {
        this.nextLayer = nextlayer;
        this.inputSize = inputSize;
    }

    public float[] Activate(float[] inputs) {
        return this.nextLayer.Activate(inputs);
    }

    public int GetOutSize() {
        return this.inputSize;
    }

    public void SetPreviousLayer(Layer layer) { }
}
