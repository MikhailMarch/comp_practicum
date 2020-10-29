package sample;

public class ImprEuler extends NumericalMethod {

    public ImprEuler(int x0, int y0, int x, int n) {
        super(x0, y0, x, n);
        y_arr[0] = y0;
        ImpEulerPopulate();
    }

    public void ImpEulerPopulate(){
        for(int i = 1; i < N + 1; i++) {
            double k1 = Function.eu_function(x_arr[i - 1], y_arr[i - 1]);
            double k2 = Function.eu_function(x_arr[i - 1] + step, y_arr[i - 1] + step*k1 );
            y_arr[i] = y_arr[i - 1] + (step/2)*(k1 + k2);
        }
    }
}
