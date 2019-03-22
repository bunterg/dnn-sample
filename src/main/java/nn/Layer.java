package main.java.nn;

public interface Layer {
    public float[] Activate(float[] inputs);
    public int GetOutSize();
    public void SetPreviousLayer(Layer layer);
}