package sample.model;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        PL pl = new PL(type.max, new double[]{140, 170},new String[]{"X","Y"},new double[][]{{0.6,0.4},{0.3,0.4},{0.1,0.2}},new double[]{2000,3000,500});
        PL x = Utils.toStandard(pl);
        Matrix matrix = new Matrix(x);
        while (matrix.test()){
        Matrix pivoter = Utils.pivoter(matrix, matrix.x, matrix.y);
        pivoter.doTheMath();
        matrix = pivoter;
        }
        System.out.println(Arrays.toString(matrix.getVb().getVariables()));
        System.out.println(Arrays.toString(matrix.getB()));
        System.out.println(matrix.getZ());
    }
}
