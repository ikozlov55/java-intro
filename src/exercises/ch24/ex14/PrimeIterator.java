package exercises.ch24.ex14;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class PrimeIterator<E extends Integer> implements Iterator<E> {
    private int limit;
    private int currentNumber;
    private int squareRoot;
    private final List<Integer> primes;
    private Optional<Integer> nextValue;

    public PrimeIterator(int limit) {
        this.limit = limit;
        this.currentNumber = 2;
        this.squareRoot = 1;
        this.primes = new ArrayList<>();
        this.nextValue = Optional.empty();
    }

    @Override
    public boolean hasNext() {
        E temp = next();
        if (temp != null) {
            nextValue = Optional.of(temp);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public E next() {
        if (nextValue.isPresent()) {
            int temp = nextValue.get();
            nextValue = Optional.empty();
            return (E) Integer.valueOf(temp);
        }
        while (currentNumber <= limit) {
            boolean isPrime = true;
            if (squareRoot * squareRoot < currentNumber) squareRoot++;
            for (int k = 0; k < primes.size() && primes.get(k) <= squareRoot; k++) {
                if (currentNumber % primes.get(k) == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(currentNumber);
                E result = (E) Integer.valueOf(currentNumber);
                currentNumber++;
                return result;
            }
            currentNumber++;
        }
        return null;
    }
}
