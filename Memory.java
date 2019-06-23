import java.util.ArrayList;
import java.util.Collections;

/**
 * Memory
 */
public class Memory {
    int begin;
    int end;
    ArrayList<Partition> block;
    ArrayList<Partition> free;

    public Memory(String begin, String end) {
        this.block = new ArrayList<Partition>();
        this.free = new ArrayList<Partition>();

        this.begin = Integer.parseInt(begin);
        this.end = Integer.parseInt(end);

        Partition firstPartition = new Partition(1, this.begin, this.end);

        this.free.add(firstPartition);
        this.block.add(firstPartition);
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

    public void addFree(Partition partition) {
        this.free.add(partition);
        Collections.sort(this.free, Collections.reverseOrder());
    }

    public Partition getBiggestPartition() {
        return this.free.get(0);
    }

    public Partition removeFree() {
        Partition partition = this.free.get(0);

        this.free.remove(0);
        return partition;
    }

    
    
}