package com.example.cli;

import com.example.cli.components.Spinner;
import org.jline.reader.LineReader;

public class LoginCommand implements Command {
    private final LineReader reader;

    public LoginCommand(LineReader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(String[] args) {
        String user = reader.readLine("Usuário: ");
        String password = reader.readLine("Senha: ", '*');

        Spinner spinner = new Spinner("Autenticando");
        Thread spinnerThread = new Thread(spinner);
        spinnerThread.start();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignored) {}

        spinner.stop();
        try {
            spinnerThread.join();
        } catch (InterruptedException ignored) {}

        System.out.print("\r\u001B[2K"); 

        if ("admin".equals(user) && "123".equals(password)) {
            System.out.println("\u001B[32m[✔] Login bem-sucedido, " + user + "!\u001B[0m");
        } else {
            System.out.println("\u001B[31m[✖] Login inválido.\u001B[0m");
        }
    }
}
