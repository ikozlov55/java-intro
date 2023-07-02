package exercises.ch15;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class Exercise15_36 {
    public static void run() {
        int size = 10;
        int maxSize = 80;
        int increment = 5;
        int runs = 10_000;
        while (size <= maxSize) {
            LatticeSimulation simulation = new LatticeSimulation(size, size);
            int deadEnds = 0;
            for (int i = 0; i < runs; i++) {
                if (simulation.drawPath() == SimulationResult.DEAD_END) deadEnds++;
            }
            double probability = ((double) deadEnds / runs) * 100;
            System.out.printf(Locale.US,
                    "For a lattice of size %d, the probability of dead-end paths is %.2f%%\n",
                    size, probability);
            size += increment;
        }
    }
}

class LatticeSimulation {
    private final int rows;
    private final int cols;
    private boolean[][] points;

    public LatticeSimulation(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.points = new boolean[rows + 1][cols + 1];

    }

    public SimulationResult drawPath() {
        int x = cols / 2;
        int y = rows / 2;
        points = new boolean[rows + 1][cols + 1];
        points[x][y] = true;
        while (true) {
            if (reachedBorder(x, y)) {
                return SimulationResult.SUCCESS;
            } else if (inDeadEnd(x, y)) {
                return SimulationResult.DEAD_END;
            }
            List<int[]> options = Stream.of(
                    new int[]{x - 1, y},
                    new int[]{x + 1, y},
                    new int[]{x, y - 1},
                    new int[]{x, y + 1}
            ).filter(p -> !points[p[0]][p[1]]).toList();

            int nextIndex = (int) (Math.random() * options.size());
            int nextX = options.get(nextIndex)[0];
            int nextY = options.get(nextIndex)[1];
            x = nextX;
            y = nextY;
            points[x][y] = true;
        }
    }

    private boolean reachedBorder(int x, int y) {
        return x == 0 || x == cols || y == 0 || y == rows;
    }

    private boolean inDeadEnd(int x, int y) {
        return points[x - 1][y] && points[x + 1][y] && points[x][y - 1] && points[x][y + 1];
    }
}

enum SimulationResult {
    SUCCESS, DEAD_END
}