package sample;

public class ErrorCalculator {
    private double[] x_values;
    private double[] y_values;
    public ErrorCalculator(Exact_solution ex, NumericalMethod method){
        x_values = new double[ex.getSize()];
        y_values = new double[ex.getSize()];
        if(ex.getSize() == method.getSize()){
            for(int i = 0; i < ex.getSize();i ++){
                x_values[i] = ex.x_array()[i];
                y_values[i] = Math.abs(ex.y_array()[i] - method.getValueOfy(i));
            }
        }
    }

    public double getValueOfy(int i){
        if( i >=0 && i <= y_values.length){
            return y_values[i];
        } else {
            return 0;
        }
    }

    public double getValueOfx(int i){
        if( i >=0 && i <= x_values.length){
            return x_values[i];
        } else {
            return 0;
        }
    }

    public int getSize(){
        return y_values.length;
    }

    public double findMaxError(){
        double max = y_values[0];
        for(int i = 1; i < y_values.length; i++){
            if(y_values[i] > max)
                max = y_values[i];
        }
        return max;
    }
}
