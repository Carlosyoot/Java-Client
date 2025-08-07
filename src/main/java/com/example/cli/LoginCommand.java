package com.example.cli;

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

        System.out.print("Autenticando");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }
        System.out.println();

        if ("admin".equals(user) && "123".equals(password)) {
            System.out.println("\u001B[32mLogin bem-sucedido, " + user + "!\u001B[0m");
        } else {
            System.out.println("\u001B[31mLogin inválido.\u001B[0m");
        }
    }
}
