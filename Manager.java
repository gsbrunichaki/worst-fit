import java.util.ArrayList;

/**
 * Manager
 */
public class Manager {
    ArrayList<Request> requests;
    ArrayList<Partition> waiting;
    Memory memory;

    public Manager(Memory memory) {
        this.requests = new ArrayList<Request>();
        this.waiting = new ArrayList<Partition>();
        this.memory = memory;
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public void receiveRequest(Request request) {
        switch (request.getCommand()) {
            case 'S':
                
                break;
            case 'L':
            
                break;
            default:
                System.err.println("O comando " + request.getCommand() + " não foi identificado.");
                break;
        }
    }
}