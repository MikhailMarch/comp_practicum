package sample;

public class EulerMethod extends NumericalMethod {
    public EulerMethod(int x0, int y0, int x, int n) {
        super(x0, y0, x, n);
        y_arr[0] = y0;
        EulerPopulate();
    }


    public void EulerPopulate(){
        for(int i = 1; i < N+1; i ++){
            y_arr[i] = y_arr[i - 1] + step * Function.eu_function(x_arr[i - 1], y_arr[i - 1]);
        }
    }
}
