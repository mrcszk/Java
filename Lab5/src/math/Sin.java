package math;

public class Sin extends Node {
    private Node value;


    Sin(Node value) {
        this.value = value;
    }

    Sin(double value) {
        this.value = new Constant(value);
    }

    @Override
    public double evaluate() {
        return Math.sin(value.evaluate());
    }

    @Override
    public String toString() {
        return "sin(" + value.toString() + ')';
    }

    @Override
    public Node diff(Variable var) {
        Node cos = new Cos(value);

        return new Prod(cos, value.diff(var));
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