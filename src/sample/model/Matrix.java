package sample.model;

import java.util.Arrays;

public class Matrix {
    private PL pl ;
    private double z ;
    private double[] delta;
    private double[] Cj_Zj;
    private VB vb ;
    public int x,y;

    public Matrix(PL pl, VB vb) {

        this.pl = pl;
        double[] coefs = new double[vb.getCoefs().length];
        System.arraycopy(vb.getCoefs(),0,coefs,0,coefs.length);

        String[] variables = new String[vb.getCoefs().length];
        System.arraycopy( vb.getVariables(), 0 , variables, 0, variables.length);
        this.vb = new VB(coefs,variables);
        double[] b= new double[pl.getB().length];
        double[] b1 = pl.getB();
        for (int i = 0; i < b1.length; i++) {
            double v = b1[i];
            b[i] = v;
        }
        setB(b);
        delta= new double[pl.getNumConstraints()];
        Cj_Zj = new double[pl.getNumVariables()];
        doTheMath();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PL getPl() {
        return pl;
    }

    public void setPl(PL pl) {
        this.pl = pl;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double[] getDelta() {
        return delta;
    }

    public void setDelta(double[] delta) {
        this.delta = delta;
    }

    public double[] getCj_Zj() {
        return Cj_Zj;
    }

    public void setCj_Zj(double[] cj_Zj) {
        Cj_Zj = cj_Zj;
    }

    public VB getVb() {
        return vb;
    }

    public void setVb(VB vb) {
        this.vb = vb;
    }

    public double[] getB() {
        return pl.getB();
    }

    public void setB(double[] b) {
        pl.setB(b);
    }

    public Matrix(PL pl) {
        this.pl = pl;
        double[] coefs = new double[pl.getNumConstraints()];
        double[] coefs1 = pl.getCoefs();
        for (int i = 0; i < coefs.length; i++) {
            double v = coefs1[i+pl.getNumVariables()-pl.getNumConstraints()];
            coefs[i] = v;
        }
        String[] variables = new String[pl.getNumConstraints()];
        System.arraycopy(pl.getVariables(), pl.getNumVariables()-pl.getNumConstraints() , variables, 0, variables.length);
        vb = new VB(coefs,variables);
        double[] b= new double[pl.getB().length];
        double[] b1 = pl.getB();
        for (int i = 0; i < b1.length; i++) {
            double v = b1[i];
            b[i] = v;
        }
        setB(b);
        delta= new double[pl.getNumConstraints()];
        Cj_Zj = new double[pl.getNumVariables()];
        doTheMath();
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "pl=" + pl +
                ", z=" + z +
                ", delta=" + Arrays.toString(delta) +
                ", Cj_Zj=" + Arrays.toString(Cj_Zj) +
                ", vb=" + vb +
                ", b=" + Arrays.toString(getB()) +
                '}';
    }

    public void doTheMath(){
        calculateCjZj();
        calculateZ();
        if (test())
            calculateDelta();

}

    private void calculateDelta() {
        int j = 0;
        double max = Cj_Zj[0];
        for (int i = 1; i < Cj_Zj.length; i++) {
            if (Cj_Zj[i]>max){
                j=i;max = Cj_Zj[j];
            }
        }
        y=j;
        for (int i = 0; i < delta.length; i++) {
            double v = pl.getA()[i][j];
            double v1 = getB()[i];
            double v2 = v1 / v;
            if (v2<0)delta[i]= Double.POSITIVE_INFINITY;
            else
            delta[i] = v2;
        }
        int m =0;
        double min = delta[0];
        for (int i = 1; i < delta.length; i++) {
            if (delta[i]<min){
                m=i;
                min = delta[i];
            }
        }
        x= m;
    }

    private void calculateZ() {
        z=0;
        for (int i = 0; i < getB().length; i++) {
            z+=getB()[i]*vb.getCoefs()[i];
        }
    }

    public boolean test() {
        for (double v : Cj_Zj) {
            if (v>0)return true;
        }
        return false;
    }

    private void calculateCjZj() {
        for (int i = 0; i < Cj_Zj.length; i++) {
            double somme = 0;
            for (int j = 0; j < vb.getCoefs().length; j++) {
                somme+=pl.getA()[j][i]*vb.getCoefs()[j];
            }
            double v = pl.getCoefs()[i] - somme;
            Cj_Zj[i]= (double) Math.round(v * 100d) / 100d;
        }
    }

}
