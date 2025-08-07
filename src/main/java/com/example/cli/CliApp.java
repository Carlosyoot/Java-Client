package com.example.cli;

import org.jline.reader.*;
import org.jline.terminal.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CliApp {
    private static final Map<String, Command> commands = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .encoding("UTF-8")
                .build();

        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        commands.put("help", new HelpCommand(commands));
        commands.put("login", new LoginCommand(reader));
        commands.put("exit", new ExitCommand());

        terminal.writer().println("\u001B[1;36mCLI-App interativo iniciado (UTF-8 + teclas).\u001B[0m");

        while (true) {
            String line;
            try {
                line = reader.readLine("> ");
            } catch (UserInterruptException | EndOfFileException e) {
                break;
            }

            if (line == null || line.trim().isEmpty()) continue;

            String[] parts = line.trim().split("\\s+");
            String cmd = parts[0];
            String[] argsCmd = new String[parts.length - 1];
            System.arraycopy(parts, 1, argsCmd, 0, argsCmd.length);

            Command command = commands.get(cmd);
            if (command != null) {
                command.execute(argsCmd);
            } else {
                terminal.writer().println("\u001B[31mComando inválido. Digite 'help'.\u001B[0m");
            }
        }
    }
}
