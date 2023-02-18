package exercises.ch10.ex8;


public class Tax {

    public static int SINGLE_FILER = 0;
    public static int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW_ER = 1;
    public static int MARRIED_SEPARATELY = 2;
    public static int HEAD_OF_HOUSEHOLD = 3;
    private int filingStatus = SINGLE_FILER;
    private int[][] brackets = new int[][]{
            {8350, 33950, 82250, 171550, 372950},
            {16700, 67900, 137050, 20885, 372950},
            {8350, 33950, 68525, 104425, 186475},
            {11950, 45500, 117450, 190200, 372950}
    };
    private double[] rates = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};
    private double taxableIncome;

    public Tax() {
    }

    public Tax(int filingStatus, int[][] brackets, double[] rates, double taxableIncome) {
        this.filingStatus = filingStatus;
        this.brackets = brackets;
        this.rates = rates;
        this.taxableIncome = taxableIncome;
    }

    public int getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(int filingStatus) {
        this.filingStatus = filingStatus;
    }

    public int[][] getBrackets() {
        return brackets;
    }

    public void setBrackets(int[][] brackets) {
        this.brackets = brackets;
    }

    public double[] getRates() {
        return rates;
    }

    public void setRates(double[] rates) {
        this.rates = rates;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTax() {
        int[] m = brackets[filingStatus];
        if (taxableIncome <= m[0]) {
            return taxableIncome * rates[0];
        } else if (taxableIncome <= m[1]) {
            return m[0] * rates[0] +
                    (taxableIncome - m[0]) * rates[1];
        } else if (taxableIncome <= m[2]) {
            return m[0] * rates[0] +
                    (m[1] - m[0]) * rates[1] +
                    (taxableIncome - m[1]) * rates[2];
        } else if (taxableIncome <= m[3]) {
            return m[0] * rates[0] +
                    (m[1] - m[0]) * rates[1] +
                    (m[2] - m[1]) * rates[2] +
                    (taxableIncome - m[2]) * rates[3];
        } else if (taxableIncome <= m[4]) {
            return m[0] * rates[0] +
                    (m[1] - m[0]) * rates[1] +
                    (m[2] - m[1]) * rates[2] +
                    (m[3] - m[2]) * rates[3] +
                    (taxableIncome - m[3]) * rates[4];
        } else {
            return m[0] * rates[0] +
                    (m[1] - m[0]) * rates[1] +
                    (m[2] - m[1]) * rates[2] +
                    (m[3] - m[2]) * rates[3] +
                    (m[4] - m[3]) * rates[4] +
                    (taxableIncome - m[4]) * rates[5];
        }
    }
}
