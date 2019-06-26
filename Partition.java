/**
    Representa uma partição variável dentro do bloco de memória disponível. 
 
    Autores: Gabriel Brunichaki, Paulo Aranha
    Data: 21.06.2019
 **/

public class Partition implements Comparable<Partition> {
    int id;
    int start;
    int end;
    int size;
    int idArea;
    boolean occupied;

    public Partition(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.size = end - start;
        this.idArea = 0;
        this.occupied = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
        this.size = this.end - this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int end) {
        this.end = end;
        this.size = this.end - this.start;
    }

    public int getSize() {
        return size;
    }

    public int getIdArea() {
        return this.idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
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
        String name = "livre";
        if (this.idArea > 0) { name = "bloco " + this.idArea; }
        return "\n" + this.start + " - " + this.end + " " + name + " (tamanho " + this.size + ")";
    }
}