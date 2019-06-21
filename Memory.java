import java.util.ArrayList;

/**
 * Memory
 */
public class Memory {
    int begin;
    int end;
    ArrayList<Partition> block;
    ArrayList<Partition> free;

    public Memory(String begin, String end) {
        this.begin = Integer.parseInt(begin);
        this.end = Integer.parseInt(end);
    }

    public ArrayList<Partition> getBlock() {
        return this.block;
    }

    public void setBlock(ArrayList<Partition> block) {
        this.block = block;
    }

    public ArrayList<Partition> getFree() {
        return this.free;
    }

    public void setFree(ArrayList<Partition> free) {
        this.free = free;
    }

    
    
}