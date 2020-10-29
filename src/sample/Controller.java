package sample;

import javafx.fxml.FXML;

import javax.swing.*;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {
    @FXML
    private Tab tab2;

    @FXML
    private TextField text_x0;

    @FXML
    private TextField text_y0;

    @FXML
    private TextField text_X;

    @FXML
    private TextField text_N;

    @FXML
    private Button button;

    @FXML
    private LineChart<Double, Double> functionGraph;

    @FXML
    private LineChart<Double, Double> errorGraph;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField tab2_text_n0;

    @FXML
    private TextField tab2_text_N;

    @FXML
    private LineChart<Double, Double> nErrorGraph;

    private int x0;
    private int y0;
    private int X;
    private int N;

    public void handleButtonClick(){
        functionGraph.getData().clear();
        errorGraph.getData().clear();
        if(text_x0.getText().equals("") || text_y0.getText().equals("") || text_X.getText().equals("") || text_N.getText().equals("")){
            errorLabel.setVisible(true);
            tab2.setDisable(true);
        } else {
            errorLabel.setVisible(false);
            x0 = Integer.parseInt(text_x0.getText());
            y0 = Integer.parseInt(text_y0.getText());
            X = Integer.parseInt(text_X.getText());
            N = Integer.parseInt(text_N.getText());

            Exact_solution s = new Exact_solution(x0, y0, X, N);
            ImprEuler ie = new ImprEuler(x0, y0, X, N);
            RungeHuttaMethod run = new RungeHuttaMethod(x0, y0, X, N);
            EulerMethod eu = new EulerMethod(x0, y0, X, N);

            functionGraph.setCreateSymbols(false);
            errorGraph.setCreateSymbols(false);
            /*                      extraction of data for approximation graphs                               */
            XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
            for (int i = 0; i < s.getSize(); i++) {
                series.getData().add(new XYChart.Data<Double, Double>(s.x_array()[i], s.y_array()[i]));
            }
            series.setName("exact solution");

            XYChart.Series<Double, Double> euler_series = new XYChart.Series<Double, Double>();
            for (int i = 0; i < eu.getSize(); i++)
                euler_series.getData().add(new XYChart.Data(eu.getValueOfx(i), eu.getValueOfy(i)));
            euler_series.setName("euler method");

            XYChart.Series<Double, Double> runge_series = new XYChart.Series<Double, Double>();
            for (int i = 0; i < run.getSize(); i++)
                runge_series.getData().add(new XYChart.Data(run.getValueOfx(i), run.getValueOfy(i)));
            runge_series.setName("runge method");

            XYChart.Series<Double, Double> improved_euler_series = new XYChart.Series<Double, Double>();
            for (int i = 0; i < ie.getSize(); i++)
                improved_euler_series.getData().add(new XYChart.Data(ie.getValueOfx(i), ie.getValueOfy(i)));
            improved_euler_series.setName("improved euler");
            /*                      error calculation                               */
            ErrorCalculator eulerError = new ErrorCalculator(s, eu);
            ErrorCalculator impEulerError = new ErrorCalculator(s, ie);
            ErrorCalculator rungeError = new ErrorCalculator(s, run);
            /*                      error data extraction for the graph                               */
            XYChart.Series<Double, Double> euler_error_series = new XYChart.Series<Double, Double>();
            for (int i = 0; i < eulerError.getSize(); i++)
                euler_error_series.getData().add(new XYChart.Data(eulerError.getValueOfx(i), eulerError.getValueOfy(i)));
            euler_error_series.setName("euler error");

            XYChart.Series<Double, Double> impEuler_error_series = new XYChart.Series<Double, Double>();
            for (int i = 0; i < impEulerError.getSize(); i++)
                impEuler_error_series.getData().add(new XYChart.Data(impEulerError.getValueOfx(i), impEulerError.getValueOfy(i)));
            impEuler_error_series.setName("impEuler error");

            XYChart.Series<Double, Double> runge_error_series = new XYChart.Series<Double, Double>();
            for (int i = 0; i < rungeError.getSize(); i++)
                runge_error_series.getData().add(new XYChart.Data(rungeError.getValueOfx(i), rungeError.getValueOfy(i)));
            runge_error_series.setName("runge error");
            /*                      graph show                               */
            functionGraph.getData().addAll(series, euler_series, improved_euler_series, runge_series);
            errorGraph.getData().addAll(euler_error_series, impEuler_error_series, runge_error_series);
            tab2.setDisable(false);
        }

    }

    public void handleOnButton2Click(){
        if(tab2_text_n0.getText().equals("") || tab2_text_N.getText().equals("")){

        } else {
            int n0 = Integer.parseInt(tab2_text_n0.getText());
            int N2 = Integer.parseInt(tab2_text_N.getText());

            if(n0 >= N2){

            } else {
                double[] x_axis = new double[N2 - n0 + 1];
                double[] euler = new double[N2 - n0 + 1];
                double[] impEuler = new double[N2 - n0 + 1];
                double[] runge = new double[N2 - n0 + 1];
                for(int i = n0; i <= N2; i++){
                    Exact_solution exact_solution = new Exact_solution(x0, y0, X, i);
                    ImprEuler ie = new ImprEuler(x0, y0, X, i);
                    RungeHuttaMethod run = new RungeHuttaMethod(x0, y0, X, i);
                    EulerMethod eu = new EulerMethod(x0, y0, X, i);
                    ErrorCalculator eulerError = new ErrorCalculator(exact_solution, eu);
                    ErrorCalculator impEulerError = new ErrorCalculator(exact_solution, ie);
                    ErrorCalculator rungeError = new ErrorCalculator(exact_solution, run);

                    euler[i - n0] = eulerError.findMaxError();
                    impEuler[i - n0] = impEulerError.findMaxError();
                    runge[i - n0] = rungeError.findMaxError();
                    x_axis[i - n0] = i;
                }
                XYChart.Series<Double, Double> eulerError = new XYChart.Series<Double, Double>();
                for (int i = 0; i < x_axis.length; i++)
                    eulerError.getData().add(new XYChart.Data(x_axis[i], euler[i]));
                eulerError.setName("Euler error");

                XYChart.Series<Double, Double> impEulerError = new XYChart.Series<Double, Double>();
                for (int i = 0; i < x_axis.length; i++)
                    impEulerError.getData().add(new XYChart.Data(x_axis[i], impEuler[i]));
                impEulerError.setName("impEuler error");

                XYChart.Series<Double, Double> rungeError = new XYChart.Series<Double, Double>();
                for (int i = 0; i < x_axis.length; i++)
                    rungeError.getData().add(new XYChart.Data(x_axis[i], runge[i]));
                rungeError.setName("Runge-hutta error");
                nErrorGraph.getData().clear();
                nErrorGraph.setCreateSymbols(false);

                nErrorGraph.getData().addAll(eulerError, impEulerError, rungeError);



            }

        }

    }
}
