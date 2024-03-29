/**
    Responsável especificamente por representar as requisições recebidas pelo gerenciador.

    Autores: Gabriel Brunichaki, Paulo Aranha
    Data: 21.06.2019
 **/

public class Request {
    private char command;
    private int value;

    public Request(String command, String value) {
        this.command = command.charAt(0);
        this.value = Integer.parseInt(value);
    }

    public char getCommand() {
        return command;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Request [command=" + command + ", value=" + value + "]";
    }

}