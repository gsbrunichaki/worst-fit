import java.util.ArrayList;

/**
 * Manager
 */
public class Manager {
    ArrayList<Request> requests;
    ArrayList<Integer> waiting;
    Memory memory;

    public Manager(Memory memory) {
        this.requests = new ArrayList<Request>();
        this.waiting = new ArrayList<Integer>();
        this.memory = memory;
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public void receiveRequest(Request request) {
        switch (request.getCommand()) {
            case 'S':
                allocate(request.getValue());
                break;
            case 'L':
                free(request.getValue());
                break;
            default:
                System.err.println("O comando " + request.getCommand() + " não foi identificado.");
                break;
        }
    }

    public void allocate(int processSize) {
        if (this.memory.getBiggestPartition().getSize() >= processSize) {
            Partition filled = memory.removeFree();
            boolean createNewPartition = (filled.getSize() > processSize);
            int oldEnd = filled.getEnd();
            int index = memory.getBlock().indexOf(filled);

            filled = memory.getBlock().get(index);
            filled.setOccupied(true);
            filled.setEnd(filled.getStart() + processSize);
            
            memory.getBlock().set(index, filled);
            
            if (createNewPartition) {
                Partition newPartition = new Partition(filled.getId() + 1, filled.getEnd(), oldEnd);
    
                memory.getBlock().add(index + 1, newPartition);
                memory.getFree().add(newPartition);
            }

            // System.out.println(memory.getBlock());
        } else {
            this.waiting.add(processSize);
        }
    }

    public void free(int partitionId) {
        for (int i = 0; i < memory.getBlock().size(); i++) {
            Partition current = memory.getBlock().get(i);

            if (current.getId() == partitionId && current.occupied) {
                current.setOccupied(false);

                // Verificar se acima ou abaixo existe partição livre

                break;
            }
        }
    }

    public void run() {
        for (int i = 0; i < this.requests.size(); i++) {
            System.out.println(this.requests.get(i));
        }
    }
}