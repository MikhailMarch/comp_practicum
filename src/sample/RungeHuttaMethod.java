package sample;

public class RungeHuttaMethod extends NumericalMethod {
    public RungeHuttaMethod(int x0, int y0, int x, int n) {
        super(x0, y0, x, n);
        y_arr[0] = y0;
        RungePopulate();
    }

    public void RungePopulate(){
        for(int i = 1; i < N+1; i++) {
            double k1 = Function.eu_function(x_arr[i-1], y_arr[i-1]);
            double k2 = Function.eu_function(x_arr[i - 1] + step/2 , y_arr[i - 1] + (step*k1)/2);
            double k3 = Function.eu_function(x_arr[i - 1] + step/2, y_arr[i - 1] + (step*k2)/2);
            double k4 = Function.eu_function(x_arr[i - 1] + step, y_arr[i - 1] + step*k3);
            y_arr[i] = y_arr[i - 1] + (step*(k1 + 2*k2 + 2*k3 + k4))/6;
        }
    }
}
