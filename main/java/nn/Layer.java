package main.java.nn;

public interface Layer {
    public double[] Activate(double[] inputs);
    public int GetOutSize();
    public void SetPreviousLayer(Layer layer);
    public void Learn(double[] results, double[] outputs);
}