/**
 * Partition
 */
public class Partition implements Comparable<Partition> {
    int id;
    int start;
    int end;
    int size;
    boolean occupied;

    public Partition(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.size = end - start;
        this.occupied = false;
    }

    public int getId() {
        return id;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public int compareTo(Partition partition) {
        if (this.size < partition.getSize()) {
            return -1;
        } else if (this.size > partition.getSize()) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Partition [end=" + end + ", id=" + id + ", occupied=" + occupied + ", start=" + start + "]";
    }
}