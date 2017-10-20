package sample.model;

import java.util.Arrays;

public class PL {
    private final int numVariables;

    public PL(PL pl) {
        this.type = pl.type;

        this.coefs = new double[pl.coefs.length];
        System.arraycopy(pl.coefs,0,this.coefs,0,this.coefs.length);

        this.variables = new String[pl.variables.length];
        System.arraycopy(pl.variables,0,this.variables,0,this.variables.length);
        A = new double[pl.A.length][pl.A[0].length];
        double[][] a = pl.A;
        for (int i = 0; i < a.length; i++) {
            double[] doubles = a[i];
            for (int j = 0; j < doubles.length; j++) {
                double aDouble = doubles[j];
                A[i][j] = aDouble;
            }
        }
        this.B = new double[pl.B.length];
        System.arraycopy(pl.B,0,this.B,0,this.B.length);

        numVariables = pl.variables.length;
        numConstraints = pl.A.length;
    }

    public int getNumVariables() {
        return numVariables;
    }

    public int getNumConstraints() {
        return numConstraints;
    }

    private final int numConstraints;
    private type type ;
    private double[] coefs ;
    private String[] variables;
    private double[][] A;
    private double[] B;

    public PL(type type, double[] coefs, String[] variables, double[][] a, double[] b) {
        this.type = type;
        this.coefs = coefs;
        this.variables = variables;
        A = a;
        B = b;
        numVariables = variables.length;
        numConstraints = a.length;
    }

    @Override
    public String toString() {
        return "PL{" +
                "type=" + type +
                ", coefs=" + Arrays.toString(coefs) +
                ", variables=" + Arrays.toString(variables) +
                ", A=" + Arrays.deepToString(A) +
                ", B=" + Arrays.toString(B) +
                '}';
    }

    public type getType() {
        return type;
    }

    public void setType(type type) {
        this.type = type;
    }

    public double[] getCoefs() {
        return coefs;
    }

    public void setCoefs(double[] coefs) {
        this.coefs = coefs;
    }

    public String[] getVariables() {
        return variables;
    }

    public void setVariables(String[] variables) {
        this.variables = variables;
    }

    public double[][] getA() {
        return A;
    }

    public void setA(double[][] a) {
        A = a;
    }

    public double[] getB() {
        return B;
    }

    public void setB(double[] b) {
        B = b;
    }
}
