package exercises.ch22.ex08;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PrimeNumbersWriter {
    private File dataFile;
    long n = 10_000_000_000L;
    final int batchSize = 10_000;

    public PrimeNumbersWriter(File dataFile) {
        this.dataFile = dataFile;
    }

    public void start() {
        try (RandomAccessFile file = new RandomAccessFile(dataFile, "rw")) {
            long start = 2;
            if (file.length() > 0) {
                file.seek(file.length() - 8);
                start = file.readLong();
            }
            System.out.printf("Searching primes starting from: %d\n", start);
            for (long i = start; i < n; i++) {
                file.seek(0);
                boolean searching = true;
                while (searching) {
                    long[] batch = readBatch(file);
                    for (long prime : batch) {
                        if (prime == 0) {
                            System.out.printf("New prime found: %d\n", i);
                            file.seek(file.length());
                            file.writeLong(i);
                            searching = false;
                            break;
                        }
                        if (i % prime == 0) {
                            searching = false;
                            break;
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        printPrimes();
    }

    public long getNumberOfPrimes(long max) {
        long result = 0;
        try (RandomAccessFile file = new RandomAccessFile(dataFile, "r")) {
            file.seek(0);
            long prime = file.readLong();
            while (prime <= max) {
                prime = file.readLong();
                result++;
            }
        } catch (IOException ignored) {
        }
        return result;
    }

    private long[] readBatch(RandomAccessFile file) {
        long[] batch = new long[batchSize];
        try {
            for (int i = 0; i < batchSize && file.length() > i * 8L; i++) {
                batch[i] = file.readLong();
            }
        } catch (IOException ignored) {
        }

        return batch;
    }

    private void printPrimes() {
        try (RandomAccessFile file = new RandomAccessFile(dataFile, "rw")) {
            file.seek(0);
            int i = 0;
            while (file.getFilePointer() < file.length()) {
                System.out.printf("%d ", file.readLong());
                i++;
                if (i % 10 == 0) {
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
