package main.java.nn;

public class OutputLayer implements Layer {
    private int outSize;
    private Neuron[] neurons;
    private Layer nextLayer, previousLayer;

    public HiddenLayer(int out, Activator activator, Layer layer) {
        this.nextLayer = layer;
        this.neurons = new Neuron[out];
        this.outSize = out;
    }

    public float[] Activate(float[] inputs) {
        float[] outputs = new Float[this.neurons.length];
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
            this.neurons[i] = new Neuron(activator, this.previousLayer.GetOutSize());
        }
    }
}