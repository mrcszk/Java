package math;

import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        for (Node node: args) {
            result += node.evaluate();
        }
        return sign*result;
    }

    int getArgumentsCount(){return args.size();}

    Node diff(Variable var) {
        Sum r = new Sum();

        for(Node n:args){
            if(!(isZero(var))){r.add(n.diff(var));
        }}
        return r;
    }
    public String toString(){
        if (args.size() == 0) {
            return new Constant(0).toString();
        }
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");
        for ( int i = 0; i < args.size(); i++) {
            if(isNecessary(args.get(i))){
                if ( i == 1 && !args.get(i-1).toString().equals("")) { b.append(" + "); }
                if ( i > 1) { b.append(" + "); }
                b.append(args.get(i).toString());
            }
        }
        if(sign<0)b.append(")");
        return b.toString();
    }
    boolean isNecessary(Node node){
        return !node.toString().equals("0") && !node.toString().isEmpty();
    }
    @Override
    boolean isZero(Variable variable) {
        for(Node node: args){
            if(! node.isZero(variable)){
                return  false;
            }
        }
        return true;
    }

}
