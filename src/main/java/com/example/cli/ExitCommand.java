package com.example.cli;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Encerrando o CLI.");
        System.exit(0);
    }
}
