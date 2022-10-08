package com.antonioscardace.app;

import com.antonioscardace.app.Daemon.Checker;

public class App {
    public static void main(String[] args) {
        try {
            Checker c = new Checker();
            c.Run();
        }
        catch (Exception e) {
            System.out.println("------");
            System.out.println("Exception..." + e.getMessage());
            System.out.println("------");
        }
    }
}