package ex4_dataStream;

import java.io.DataOutput;
import java.io.IOException;

public class CheckedDataOutput {
    private Checksum cksum;
    private DataOutput out;
    public CheckedDataOutput(DataOutput out, Checksum cksum) {
        this.cksum = cksum;
        this.out = out;
    }
    public void write(int b) throws IOException {
        out.write(b);
        cksum.update(b);
    }
    public void write(byte[] b) throws IOException {
        out.write(b, 0, b.length);
        cksum.update(b, 0, b.length);
    }
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        cksum.update(b, off, len);
    }
    public Checksum getChecksum() {
        return cksum;
    }
}
