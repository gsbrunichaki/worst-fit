import java.io.BufferedReader;
import java.io.FileReader;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        try {
            FileReader textFile = new FileReader("test1.txt");
            BufferedReader readFile = new BufferedReader(textFile);

            // Ignora a primeira linha
            readFile.readLine();

            Memory memory = new Memory(readFile.readLine(), readFile.readLine());

            Manager manager = new Manager(memory);

            while (readFile.ready()) {
                lineToRequest(manager, readFile.readLine());
            }

            manager.receiveRequest(new Request("S", "250"));
            manager.receiveRequest(new Request("S", "100"));
            manager.receiveRequest(new Request("S", "200"));
            manager.receiveRequest(new Request("S", "400"));

            manager.test();

            textFile.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }
}