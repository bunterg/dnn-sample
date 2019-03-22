package main.java.activators;

public class SigmoidActivator implements Activator {

    public SigmoidActivator() {}

    public float Activate(float value) {
        return 1 / (1 + Math.exp(-value));
    }

    public float Derivative(float value) {
        float res = this.Activate(axions);
        return  res * ( 1 - res);
    }
}