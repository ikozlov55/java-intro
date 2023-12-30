package exercises.ch22.ex14;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeNumbersCalculator {

    public static ArrayList<Integer> getPrimes(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        int number = 2;
        while (number <= n) {
            boolean isPrime = true;
            for (int divisor = 2; divisor <= (int) Math.sqrt(number); divisor++) {
                if (number % divisor == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(number);
            }
            number++;
        }
        return primes;
    }

    public static ArrayList<Integer> getPrimesEfficient(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        int number = 2;
        int squareRoot = 1;
        while (number <= n) {
            boolean isPrime = true;
            if (squareRoot * squareRoot < number) squareRoot++;
            for (int k = 0; k < primes.size() && primes.get(k) <= squareRoot; k++) {
                if (number % primes.get(k) == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(number);
            }
            number++;
        }
        return primes;
    }

    public static ArrayList<Integer> getPrimesSieveOfEratosthenes(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);

        for (int i = 2; i <= n / i; i++) {
            if (primes[i]) {
                for (int j = i; j <= n / i; j++) {
                    primes[i * j] = false;
                }
            }
        }
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                result.add(i);
            }
        }

        return result;
    }
}

