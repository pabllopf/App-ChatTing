package pabllopf.ejemplo;

public class Conversor {
    private double grados;

    public Conversor(double grados) {
        this.grados = grados;
    }
    
    public double aFahrenheit(){
        return ((grados * 1.8) + 32.0);
    }
    
    public double getGrados(){
        return ((grados - 32.0) / 1.8);
    }
}
