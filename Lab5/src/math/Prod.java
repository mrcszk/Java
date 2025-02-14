package math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        this(new Constant(c));
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        this(new Constant(c),n);
    }



    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return null;
    }


    @Override
    double evaluate() {
        double result =1;
        for (Node node : args) {
            result *= node.evaluate();
        }
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}

    @Override
    Node diff(Variable var) {
        Sum r = new Sum();
        for(int i=0;i<args.size();i++){
            Prod m= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)m.mul(f.diff(var));
                else m.mul(f);
            }
            if (!m.isZero(var)) {
                r.add(m);
            }
        }
        return r;
    }
    public String toString(){
        StringBuilder b =  new StringBuilder();
        for ( int i = 0; i < args.size(); i++) {
            if(isNecessary(args.get(i))) {
                return b.toString();
            }
        }
        if(sign<0)b.append("-");
        for ( int i = 0; i < args.size(); i++) {
            if ( args.get(i).getSign() < 0 ) b.append("(");
            if ( i > 0 ) b.append("*");
            b.append(args.get(i).toString());
            if ( args.get(i).getSign() < 0 ) b.append(")");
        }
        return b.toString();
    }
    private boolean isNecessary(Node node){
        return node.toString().equals("0") || node.toString().isEmpty();
    }
    @Override
    boolean isZero(Variable variable)  {
        for(Node node: args){
            if(node instanceof Constant && node.evaluate() == 0){
                return true;
            }
        }
        return false;
    }

    Node simplify(){
        for (Node arg : args) {
            arg.simplify();
        }
        List<Node> y = new ArrayList<>();
        double s = 1;
        for (int j =0; j < args.size(); ++j) {
            Node arg = args.get(j);
            if (arg instanceof Prod){
                for (int i = 0; i < arg.getArgumentsCount(); i++) {
                    this.mul(((Prod) arg). args.get(i));
                }
                args.remove(arg);
            }
        }
        for (Node arg : args) {
            if(arg instanceof Constant ){
                double x = arg.evaluate();
                if (x != 0){
                    s*=x;
                    y.add(arg);
                }else {
                    return new Constant(0);
                }
            }
        }

        for (Node y1 : y) {
            args.remove(y1);
        }

        if(s == 1) return this;
        args.add(0,new Constant(s));

        return this;
    }
}