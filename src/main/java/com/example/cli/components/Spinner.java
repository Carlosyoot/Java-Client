package com.example.cli.components;

public class Spinner implements Runnable {
    private final String[] frames = { "⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏" };
    private volatile boolean running = true;
    private final String message;

    public Spinner(String message) {
        this.message = message;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        int i = 0;
        while (running) {
            System.out.print("\r" + message + " " + frames[i % frames.length]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
            i++;
        }
    }
}
