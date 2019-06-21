/**
 * Partition
 */
public class Partition {
    int id;
    int start;
    int end;
    boolean occupied;

    public Partition(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
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

    public boolean isOccupied() {
        return this.occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "Partition [end=" + end + ", id=" + id + ", occupied=" + occupied + ", start=" + start + "]";
    }
}