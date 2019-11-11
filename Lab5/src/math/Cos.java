package math;

public class Cos extends Node {
    private Node value;

    Cos(Node value) {
        this.value = value;
    }

    Cos(double value) {
        this.value = new Constant(value);
    }

    @Override
    public double evaluate() {
        return Math.cos(value.evaluate());
    }

    @Override
    public String toString() {
        return "cos(" + value.toString() + ')';
    }

    @Override
    public Node diff(Variable var) {
        Node sin = new Sin(value);

        return new Prod(sin.minus(), value.diff(var));
    }

    @Override
    int getArgumentsCount() {
        return 1;
    }
    @Override
    boolean isZero(Variable variable) {
        return false;
    }
}