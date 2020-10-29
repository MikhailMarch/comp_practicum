package sample;

public class NumericalMethod {
    protected double [] x_arr;
    protected double [] y_arr;
    protected int x0;
    protected int y0;
    protected int X;
    protected int N;
    protected double step;

    public NumericalMethod(int x0, int y0, int x, int n) {
        this.x0 = x0;
        this.y0 = y0;
        X = x;
        N = n;
        x_arr = new double[N+1];
        y_arr = new double[N+1];
        step = Double.valueOf(X - x0)/N;
        x_arr[0] = x0;
        for(int i = 1; i < N+1; i++){
            x_arr[i] = x_arr[i-1] + step;
        }
    }

    public double getValueOfy(int i){
        if( i >=0 && i <= y_arr.length){
            return y_arr[i];
        } else {
            return 0;
        }
    }

    public double getValueOfx(int i){
        if( i >=0 && i <= x_arr.length){
            return x_arr[i];
        } else {
            return 0;
        }
    }

    public int getSize(){
        return y_arr.length;
    }

}
