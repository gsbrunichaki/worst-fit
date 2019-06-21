import java.util.ArrayList;

/**
 * Memory
 */
public class Memory {
    int begin;
    int end;
    ArrayList<Partition> block;
    ArrayList<Partition> free;

    public Memory(int begin, int end) {
        this.begin = begin;
        this.end = end;
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