package com.omnibase;

import org.springframework.boot.CommandLineRunner;

public class AppStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application has started using CommandLineRunner");
    }
}
