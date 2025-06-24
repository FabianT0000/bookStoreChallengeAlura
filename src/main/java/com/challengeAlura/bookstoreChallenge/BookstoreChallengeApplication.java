package com.challengeAlura.bookstoreChallenge;

import com.challengeAlura.bookstoreChallenge.main.MainApp;
import com.challengeAlura.bookstoreChallenge.repository.AuthorRepository;
import com.challengeAlura.bookstoreChallenge.repository.BookRepository;
import com.challengeAlura.bookstoreChallenge.service.ConsumingAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BookstoreChallengeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreChallengeApplication.class, args);
    }

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        MainApp mainApp = new MainApp(authorRepository,bookRepository);
        mainApp.showMenu();

    }
}
