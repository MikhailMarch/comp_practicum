package sample;

public class Exact_solution {

    private int x0;
    private int y0;
    private int X;
    private int N;
    private double step;
    private double[] x_values;
    private double[] y_values;

    private double C;
    public Exact_solution(int x0, int y0, int X, int N){
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.N = N;
        C = (1.0/Double.valueOf(x0)) - Math.log(2 - y0*y0);
        step = Double.valueOf(X - x0)/N;
        x_values = new double[N+1];
        y_values = new double[N+1];
        x_values[0] = x0;
        y_values[0] = y0;
        if(y0 > 0) {
            for (int i = 1; i < N + 1; i++) {
                x_values[i] = x_values[i - 1] + step;
                y_values[i] = Function.exact_function(x_values[i], C);
            }
        } else {
            for (int i = 1; i < N + 1; i++) {
                x_values[i] = x_values[i - 1] + step;
                y_values[i] = Function.neg_exact_function(x_values[i], C);
            }
        }
    }



    public double[] x_array(){
        return x_values;
    }
    public double[] y_array(){
        return y_values;
    }

    public int getSize(){
        return x_values.length;
    }

}
