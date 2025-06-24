package com.challengeAlura.bookstoreChallenge.main;

import com.challengeAlura.bookstoreChallenge.dto.AuthorsData;
import com.challengeAlura.bookstoreChallenge.dto.BooksData;
import com.challengeAlura.bookstoreChallenge.dto.Data;
import com.challengeAlura.bookstoreChallenge.model.Author;
import com.challengeAlura.bookstoreChallenge.model.Book;
import com.challengeAlura.bookstoreChallenge.model.Language;
import com.challengeAlura.bookstoreChallenge.repository.AuthorRepository;
import com.challengeAlura.bookstoreChallenge.repository.BookRepository;
import com.challengeAlura.bookstoreChallenge.service.ConsumingAPI;
import com.challengeAlura.bookstoreChallenge.service.ConvertData;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainApp {
    private Scanner read = new Scanner(System.in);
    private ConsumingAPI consumingAPI = new ConsumingAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvertData convertData = new ConvertData();
    private Author authorPresent;
    private Optional<BooksData> searchedBook;

    private List<Book> books;
    private int dateSearch = 0;
    private String languageSearch;

    private Author newAuthor;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public MainApp(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var option = -1;

        while (option != 0) {
            var menu = """
                    Elige la opción que deseas:
                    
                    
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                option = Integer.parseInt(read.nextLine());
            } catch (Exception e) {
                System.out.println("Solo se permiten números");
            }

            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    viewRegisteredBooks();
                    break;
                case 3:
                    viewRegisteredAuthors();
                    break;
                case 4:
                    LivingAuthorsByYear();
                    break;
                case 5:
                    viewBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }


    }

    private void searchBookByTitle() {
        BooksData data = getDataFromApi();
        AuthorsData authorsData = data.author().stream()
                .findFirst()
                .map(d -> new AuthorsData(d.name(), d.birthYear(), d.deathYear()))
                .orElse(null);

        Optional<Author> verifyAuthorDb = authorRepository.findByName(authorsData.name());
        Optional<Book> verifyBookDb = bookRepository.findByTitleAndAuthorName(data.title(), authorsData.name());

        if (verifyAuthorDb.isPresent()) {
            System.out.println("El director ya existe");
            authorPresent = verifyAuthorDb.get();
            verifyAuthorDb
                    .ifPresent(d ->
                    {
                        books = d.getBooks();
                        List<String> booksString = books.stream().map(Book::getTitle).collect(Collectors.toList());
                        System.out.println(
                                "--------------AUTOR---------------" +
                                        "\nNombre : " + d.getName() +
                                        "\nFecha de nacimiento : " + d.getBirthYear() +
                                        "\nFecha de fallecimiento : " + d.getDeathYear() +
                                        "\nLibros : " + booksString +
                                        "\n----------------------------------"
                        );
                    });
            if (verifyBookDb.isPresent()) {
                System.out.println("El libro ya existe");
                verifyBookDb.ifPresent(d ->
                        System.out.println(
                                "--------------LIBRO---------------" +
                                        "\nTitulo : " + d.getTitle() +
                                        "\nAutor : " + d.getAuthor().getName() +
                                        "\nIdioma: " + d.getLanguage() +
                                        "\nDescargas: " + d.getDownloadCount() +
                                        "\n----------------------------------")
                );
            } else {
                try {
                    Book newBook = new Book(data, authorPresent);
                    bookRepository.save(newBook);
                } catch (Exception e) {
                    System.out.println("Error al guarda libro");
                }
            }
        } else {
            newAuthor = new Author(authorsData);
            authorRepository.save(newAuthor);
            try {
                Book newBook = new Book(data, newAuthor);
                bookRepository.save(newBook);
            } catch (Exception e) {
                System.out.println("El libro ya existe en la base de datos");
            }
        }
    }


    private BooksData getDataFromApi() {
        System.out.println("Ingrese el nombre del libro que desea buscar : ");
        String bookName = read.nextLine();
        var json = consumingAPI.getData(URL_BASE + "?search=" + bookName.replace(" ", "+"));
        Data searchedData = convertData.getData(json, Data.class);
        searchedBook = searchedData.results().stream()
                .filter(book -> book.title().toUpperCase().contains(bookName.toUpperCase()))
                .findFirst();

        if (searchedBook.isPresent()) {
            System.out.println("Libro encontrado");
            System.out.println(searchedBook.get());
            return searchedBook.get();
        } else {
            System.out.println("No se encontro el libro");
        }
        return null;

    }


    private void viewRegisteredBooks() {
        var registerBookdBd = bookRepository.findAll();
        registerBookdBd.forEach(d ->
                System.out.println(
                        "--------------LIBRO---------------" +
                                "\nTitulo : " + d.getTitle() +
                                "\nAutor : " + d.getAuthor().getName() +
                                "\nIdioma: " + d.getLanguage() +
                                "\nDescargas: " + d.getDownloadCount() +
                                "\n----------------------------------\n")
        );
    }


    private void viewRegisteredAuthors() {
        var registerBdAuthors = authorRepository.findAll();
        registerBdAuthors.forEach(d ->
        {
            books = d.getBooks();
            List<String> booksString = books.stream().map(Book::getTitle).collect(Collectors.toList());
            System.out.println(
                    "--------------AUTOR--------------" +
                            "\nNombre : " + d.getName() +
                            "\nFecha de nacimiento : " + d.getBirthYear() +
                            "\nFecha de fallecimiento : " + d.getDeathYear() +
                            "\nLibros : " + booksString +
                            "\n----------------------------------\n");
        });
    }

    private void LivingAuthorsByYear() {
        try {
            System.out.println("Ingresar una fecha para mostrar autores vivos: ");
            dateSearch = Integer.parseInt(read.nextLine());
        } catch (Exception e) {
            System.out.println("Solo se permiten fechas númericas");
        }
        var results = authorRepository.authorsByYear(dateSearch);
        if (results.isEmpty()) {
            System.out.println("No hay ningun autor vivo en este año");
        } else {
            System.out.println("hay un result");
            System.out.println(results.size());
            results.forEach(d ->
                    {
                        books = d.getBooks();
                        List<String> booksString = books.stream().map(Book::getTitle).collect(Collectors.toList());
                        System.out.println(
                                "--------------AUTOR--------------" +
                                        "\nNombre : " + d.getName() +
                                        "\nFecha de nacimiento : " + d.getBirthYear() +
                                        "\nFecha de fallecimiento : " + d.getDeathYear() +
                                        "\nLibros : " + booksString +
                                        "\n----------------------------------\n");
                    }
            );
        }
    }

    private void viewBooksByLanguage() {
        var menu = """
                    -Español
                    -Ingles
                    -Frances
                    -Portugués
                    -Aleman
                    ------------------------------------------------
                    Escribe el idioma  de los libros que deseas ver :
                    ------------------------------------------------
                    """;
        try {
            System.out.println(menu);
            languageSearch = read.nextLine();
            var data = bookRepository.findByIdioma(Language.GetCodeByName(languageSearch));
            if (data.isEmpty()){
                System.out.println("No se encuentran libros en el idioma: "+languageSearch);
            }
            else {
                data.forEach(d ->
                        System.out.println(
                                "--------------LIBRO---------------" +
                                        "\nTitulo : " + d.getTitle() +
                                        "\nAutor : " + d.getAuthor().getName() +
                                        "\nIdioma: " + d.getLanguage() +
                                        "\nDescargas: " + d.getDownloadCount() +
                                        "\n----------------------------------\n")
                );
            }
    } catch(
    Exception e) {
        System.out.println("Verifique el idioma ingresado");
    }
}
}
