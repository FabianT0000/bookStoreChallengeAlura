package com.challengeAlura.bookstoreChallenge.model;


import com.challengeAlura.bookstoreChallenge.dto.AuthorsData;
import com.challengeAlura.bookstoreChallenge.dto.BooksData;
import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne
    private Author author;

    private String language;
    private Double downloadCount;

    public Book(){}

    public Book(BooksData booksData,Author author) {
        System.out.println(author);
        this.title = String.valueOf(booksData.title());
        this.author =author;
        this.language = booksData.language().get(0);
        this.downloadCount = booksData.downloadCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return
                ", Titulo='" + title + '\'' +
                ", Autor=" + author +
                ", Idioma = " + language + '\'' +
                ", Cantidad de descargas = " + downloadCount ;
    }
}
