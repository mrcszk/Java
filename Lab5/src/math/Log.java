package math;

public class Log extends Node {
    private Constant base;
    private Node value;

    Log(double base, Node value) {
        this.base = new Constant(base);
        this.value = value;
    }

    @Override
    public double evaluate() {
        return Math.log(value.evaluate()) / Math.log(base.evaluate());
    }

    @Override
    public String toString() {
        return "log_" + base + "^(" + value.toString() + ')';
    }

    @Override
    public Node diff(Variable var) {
        return new Prod(new Log(base.evaluate(), new Constant(Math.E)), value.diff(var));
    }

    @Override
    int getArgumentsCount() {
        return 1;
    }
    boolean isZero(Variable variable) {
        return false;
    }
}