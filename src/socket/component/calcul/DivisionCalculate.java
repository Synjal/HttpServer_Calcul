package socket.calcul;

public class DivisionCalculate implements Calculate {
    @Override
    public double calculate(int a, int b) {
        return (double) a / b;
    }
}
