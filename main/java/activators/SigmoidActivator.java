package main.java.activators;

public class SigmoidActivator implements Activator {

    public SigmoidActivator() {}

    public double Activate(double value) {
        return 1 / (1 + Math.exp(-value));
    }

    public double Derivative(double value) {
        double res = this.Activate(value);
        return  res * ( 1 - res);
    }
}