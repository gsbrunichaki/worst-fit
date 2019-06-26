/**
    Simula o gerenciador de memória e é responsável por alocar e
    liberar processos nobloco disponível na memória principal. 
 
    Autores: Gabriel Brunichaki, Paulo Aranha
    Data: 21.06.2019
 **/

 import java.util.ArrayList;

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

    // Recebe uma solicitação e verifica se aloca memória ou libera memória
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

    // Aloca um processo na maior partição livre e cria uma segunda partição livre se necessário
    public void allocate(int processSize) {
        if (this.memory.getFree().size() > 0 && this.memory.getBiggestPartition().getSize() >= processSize) {
            Partition filled = memory.removeFree();
            int oldEnd = filled.getEnd();
            int index = memory.getBlock().indexOf(filled);
            boolean createNewPartition = (filled.getSize() > processSize);

            filled = memory.getBlock().get(index);
            filled.setOccupied(true);
            filled.setIdArea(memory.newIdArea());
            filled.setEnd(filled.getStart() + processSize);

            memory.getBlock().set(index, filled);

            // Se sobrou memória na partição alocada, cria uma nova partição
            if (createNewPartition) {
                Partition newPartition = new Partition(filled.getId() + 1, filled.getEnd(), oldEnd);

                memory.getBlock().add(index + 1, newPartition);
                memory.addFree(newPartition);
            }
        
        // Se não há uma partição livre maior ou igual ao tamanho do processo que requisitou alocação,
        // deixa o processo aguardando por uma partção compatível
        } else {
            this.waiting.add(processSize);
            System.out.println(memory.sumFree() + " livres, " + processSize + " solicitados - fragmentação externa.");
        }
    }

    // Libera processo da memória
    public void free(int partitionId) {
        for (int i = 0; i < memory.getBlock().size(); i++) {
            Partition current = memory.getBlock().get(i);
            int index = i;

            if (current.getIdArea() == partitionId && current.occupied) {
                current.setOccupied(false);
                current.setIdArea(0);

                // Verifica se há uma partição abaixo e se ela está livre
                if (i + 1 < memory.getBlock().size()) {
                    Partition below = memory.getBlock().get(i + 1);

                    // Se a partição abaixo está livre, junta com a partição atual
                    if (!below.isOccupied()) {
                        current.setEnd(below.getEnd());
                        memory.getFree().remove(below);
                        memory.getBlock().remove(below);
                    }
                }

                // Verifica se há uma partição acima e se ela está livre
                if (i > 0) {
                    Partition above = memory.getBlock().get(i - 1);

                    // Se a partição acima está livre, junta com a partição atual
                    if (!above.isOccupied()) {
                        current.setStart(above.getStart());
                        memory.getFree().remove(above);
                        memory.getBlock().remove(above);
                        index--;
                    }
                }

                // Atualiza a partição livre
                memory.getBlock().set(index, current);
                memory.addFree(current);

                break;
            }
        }

        // Verifica se os processos que estão aguardando podem ser alocados
        if (this.waiting.size() > 0) {
            for (int i = 0; i < this.waiting.size(); i++) {
                int currentWaiting = this.waiting.get(i);

                this.waiting.remove(i);
                this.allocate(currentWaiting);
            }
        }
    }

    // Exibe na tela o andamento da aplicação
    public void showExit(Request request) {
        System.out.println(request.getCommand() + " " + request.getValue());
        for (int i = 0; i < memory.getBlock().size(); i++) {
            Partition partition = memory.getBlock().get(i);
            String name = "livre";

            if (partition.getIdArea() > 0) {
                name = "bloco " + partition.getIdArea();
            }

            System.out.println(partition.getStart() + "-" + partition.getEnd() + "     " + name + " (tamanho: " + partition.getSize() + ")" );
        }

        System.out.println("Aguardando: " + this.waiting);
        System.out.println("\n");
    }

    // Método que dá início ao gerenciamento
    public void run() {
        for (int i = 0; i < this.requests.size(); i++) {
            receiveRequest(this.requests.get(i));
            showExit(this.requests.get(i));
        }

    }
}