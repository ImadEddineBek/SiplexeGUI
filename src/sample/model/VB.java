package sample.model;

import java.util.Arrays;

public class VB {
    private double[] coefs ;
    private String[] variables;

    public VB(double[] coefs, String[] variables) {
        this.coefs = coefs;
        this.variables = variables;
    }
    public void setCoefsVariables(int index , double coef , String variable){
        coefs[index]=coef;
        variables[index]=variable;

    }

    @Override
    public String toString() {
        return "VB{" +
                "coefs=" + Arrays.toString(coefs) +
                ", variables=" + Arrays.toString(variables) +
                '}';
    }

    public double[] getCoefs() {
        return coefs;
    }

    public String[] getVariables() {
        return variables;
    }
}
