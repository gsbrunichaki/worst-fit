/**
    Simula a memória principal que é gerenciada pelo Manager.
 
    Autores: Gabriel Brunichaki, Paulo Aranha
    Data: 21.06.2019
 **/

import java.util.ArrayList;
import java.util.Collections;

public class Memory {
    int begin;
    int end;
    int lastIdArea;
    ArrayList<Partition> block;
    ArrayList<Partition> free;

    public Memory(String begin, String end) {
        this.block = new ArrayList<Partition>();
        this.free = new ArrayList<Partition>();

        this.begin = Integer.parseInt(begin);
        this.end = Integer.parseInt(end);
        this.lastIdArea = 0;

        Partition firstPartition = new Partition(1, this.begin, this.end);

        this.block.add(firstPartition);
        this.free.add(firstPartition);
    }

    public int getLastIdArea() {
        return this.lastIdArea;
    }

    public int newIdArea() {
        return ++this.lastIdArea;
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

    public int sumFree() {
        int sum = 0;

        for (int i = 0; i < this.free.size(); i++) {
            sum += this.free.get(i).getSize();
        }

        return sum;
    }    
}