package com.example.cli;

import java.util.Map;

public class HelpCommand implements Command {
    private final Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Comandos disponíveis:");
        for (String name : commands.keySet()) {
            System.out.println("- " + name);
        }
    }
}
