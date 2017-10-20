package sample.progam;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.model.Matrix;
import sample.model.PL;
import sample.model.Utils;
import sample.model.type;

import java.util.Arrays;

import static sample.model.type.max;

public class Controller {
    public Label result;
    public TextField b3;
    public TextField c12;
    public TextField c11;
    public TextField c22;
    public TextField c21;
    public TextField c32;
    public TextField c31;
    public TextField b2;
    public TextField b1;
    public TextField b;
    public TextField a;

    public void go(ActionEvent actionEvent) {
         double b3 = Double.parseDouble(this.b3.getText());
        double c12 = Double.parseDouble(this.c12.getText());
        double c11 = Double.parseDouble(this.c11.getText());
        double c22 = Double.parseDouble(this.c22.getText());
        double c21 = Double.parseDouble(this.c21.getText());
        double c32 = Double.parseDouble(this.c32.getText());
        double c31 = Double.parseDouble(this.c31.getText());
        double b2 = Double.parseDouble(this.b2.getText());
        double b1 = Double.parseDouble(this.b1.getText());
        double b = Double.parseDouble(this.b.getText());
        double a = Double.parseDouble(this.a.getText());
        PL pl = new PL(max, new double[]{a, b},new String[]{"X","Y"},new double[][]{{c11,c12},{c21,c22},{c31,c32}},new double[]{b1,b2,b3});
        PL x = Utils.toStandard(pl);
        Matrix matrix = new Matrix(x);
        while (matrix.test()){
            Matrix pivoter = Utils.pivoter(matrix, matrix.x, matrix.y);
            pivoter.doTheMath();
            matrix = pivoter;
        }
        int indexA = getIndex("X" , matrix.getVb().getVariables());
        int indexB = getIndex("Y" , matrix.getVb().getVariables());
        result.setText("X is : "+matrix.getB()[indexA]+
                ", Y is : "+matrix.getB()[indexB]+" , the result is :"+matrix.getZ());
    }

    private int getIndex(String x, String[] variables) {
        for (int i = 0; i < variables.length; i++) {
            if (x.equals(variables[i]))return i;
        }
        return -1;
    }
}
