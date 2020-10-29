package sample;

public class Function {
    public static double eu_function(double x, double y){
        return (2-y*y)/(2*x*x*y);
    }
    public static double exact_function(double x, double C){
        if(2 - Math.exp(1 / x - C) < 0)
            return 0;
        return Math.sqrt(2 - Math.exp(1 / x - C));
    }
    public static double neg_exact_function(double x, double C){
        if(2 - Math.exp(1 / x - C) < 0)
            return 0;
        return -Math.sqrt(2 - Math.exp(1 / x - C));
    }

}
