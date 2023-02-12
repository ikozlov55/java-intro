package exercises.ch9.ex2;


public class Stock {
    String symbol;
    String name;
    public double previousClosingPrice;
    public double currentPrice;

    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public double getChangePercent() {
        double diff = currentPrice - previousClosingPrice;
        return roundPercent((diff / previousClosingPrice) * 100);
    }

    private double roundPercent(double value) {
        return (double) Math.round(value * 100) / 100;
    }
}
