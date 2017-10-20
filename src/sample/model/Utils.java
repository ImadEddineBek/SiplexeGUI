package sample.model;

import java.util.ArrayList;

public class Utils {
    public static PL toStandard(PL pl){
        int length = pl.getB().length;
        //coefs
        ArrayList<Double> coefs = new ArrayList<>();
        for (double v : pl.getCoefs()) coefs.add(v);
        for (int i = 1; i <= length; i++) coefs.add(0.0);
        //variable names
        ArrayList<String> variables = new ArrayList<>();
        for (String v : pl.getVariables()) variables.add(v);
        for (int i = 1; i <= length; i++) variables.add("e"+i);
        //constraints
        ArrayList<ArrayList<Double>> A = new ArrayList<>();
        double[][] a = pl.getA();
        for (int i = 0; i < a.length; i++) {
            double[] doubles = a[i];
            ArrayList<Double> contrainte = new ArrayList<>();
            for (double aDouble : doubles) contrainte.add(aDouble);
            for (int j = 0; j < length; j++) {
                if (j == i) contrainte.add(1.0);
                else contrainte.add(0.0);
            }
            A.add(contrainte);
        }
        return new PL(pl.getType(),toArrayDouble(coefs),toArrayString(variables),toArray(A),pl.getB());
    }

    private static double[] toArrayDouble(ArrayList<Double> coefs) {
        double[] strings = new double[coefs.size()];
        for (int i = 0; i < coefs.size(); i++) {
            double variable = coefs.get(i);
            strings[i]=variable;
        }
        return strings;
    }

    private static String[] toArrayString(ArrayList<String> variables) {
        String[] strings = new String[variables.size()];
        for (int i = 0; i < variables.size(); i++) {
            String variable = variables.get(i);
            strings[i]=variable;
        }
        return strings;
    }

    private static double[][] toArray(ArrayList<ArrayList<Double>> a) {
        double[][] doubles = new double[a.size()][a.get(0).size()];
        for (int i1 = 0; i1 < a.size(); i1++) {
            ArrayList<Double> arrayList = a.get(i1);
            for (int i = 0; i < arrayList.size(); i++) {
                Double aDouble = arrayList.get(i);
                doubles[i1][i]=aDouble;
            }
        }
        return doubles;
    }

    public static Matrix pivoter(Matrix matrix , int i , int j){
        PL pl = new PL(matrix.getPl());
        Matrix matrix1 = new Matrix(pl,matrix.getVb());
        changerBase(matrix1,i,j);
        changerB(matrix1,i,j);
        double[][] a = matrix.getPl().getA();
        for (int i1 = 0; i1 < a.length; i1++) {
            double[] doubles = a[i1];
            for (int i2 = 0; i2 < doubles.length; i2++) {
                double v = newValue(matrix, i, j, i1, i2);
                matrix1.getPl().getA()[i1][i2] = v;
            }
        }
        for (int k = 0; k < matrix.getPl().getNumVariables(); k++) {
            double v = matrix.getPl().getA()[i][k] / matrix.getPl().getA()[i][j];
            matrix1.getPl().getA()[i][k]= (double) Math.round(v* 100d) / 100d;
        }
        return matrix1;
    }

    private static void changerB(Matrix matrix1, int i, int j) {
        double bPivot = matrix1.getB()[i];
        for (int i1 = 0; i1 < matrix1.getB().length; i1++) {
            if (i1!=i){
            double v = enfaceHorizental(matrix1,i,j,i1,0);
            matrix1.getB()[i1]-=((bPivot*v)/matrix1.getPl().getA()[i][j]);
        }
        else matrix1.getB()[i1]/=matrix1.getPl().getA()[i][j];
        }
    }

    private static void changerBase(Matrix matrix1, int i, int j) {
            matrix1.getVb().setCoefsVariables(i,matrix1.getPl().getCoefs()[j],matrix1.getPl().getVariables()[j]);
    }
    public static double enfaceVertical(Matrix matrix , int i , int j , int x ,int y ){
        return matrix.getPl().getA()[i][y];
    }
    public static double enfaceHorizental(Matrix matrix , int i , int j , int x ,int y ){
        return matrix.getPl().getA()[x][j];
    }
    public static double newValue(Matrix matrix ,  int i , int j , int x ,int y ){
        double v = enfaceHorizental(matrix, i, j, x, y);
        double v1 = enfaceVertical(matrix, i, j, x, y);
        double v2 = matrix.getPl().getA()[i][j];
        double v3 = matrix.getPl().getA()[x][y];
        double value = v3 - ((v * v1) / v2);
        return (double) Math.round(value * 100d) / 100d;
    }
}
