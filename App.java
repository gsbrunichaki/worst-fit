/**
    inicializa a aplicação. Além disso, é responsável por instanciar os objetos "Memória"e "Gerenciador"
    e fazer a leitura do arquivo de texto, adicionando os comandos de alocação e liberação na lista de
    solicitações do gerenciador.

    Autores: Gabriel Brunichaki, Paulo Aranha
    Data: 21.06.2019
 **/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        try {
            FileReader textFile = new FileReader("test2.txt");
            BufferedReader readFile = new BufferedReader(textFile);

            // Ignora a primeira linha
            readFile.readLine();

            Memory memory = new Memory(readFile.readLine(), readFile.readLine());
            Manager manager = new Manager(memory);

            while (readFile.ready()) {
                lineToRequest(manager, readFile.readLine());
            }

            textFile.close();

            manager.run();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public static void lineToRequest(Manager manager, String line) {
        String s[] = line.split(" ");

        manager.addRequest(new Request(s[0], s[1]));
    }
}