package exercises.ch17.ex17;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitOutputStream implements AutoCloseable {
    private final File file;
    private byte buffer = 0;
    private int bitsFilled = 0;

    public BitOutputStream(File file) {
        this.file = file;
    }

    public void writeBit(char bit) {
        if (bit != '0' && bit != '1') return;
        if (bitsFilled == 8) {
            writeBuffer();
        }
        buffer = (byte) ((buffer << 1));
        if (bit == '1') buffer++;
        bitsFilled++;
        System.out.println(this);
        System.out.println(buffer);
    }

    public void writeBit(String bit) {
        for (char c : bit.toCharArray()) {
            writeBit(c);
        }
    }

    public void close() {
        writeBuffer();
    }

    private void writeBuffer() {
        try (FileOutputStream stream = new FileOutputStream(file, true)) {
            stream.write(buffer);
            buffer = 0;
            bitsFilled = 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("%8s", Integer.toBinaryString(buffer)).replace(' ', '0');
    }
}
