package com.challengeAlura.bookstoreChallenge.repository;

import com.challengeAlura.bookstoreChallenge.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitleAndAuthorName(String Bookname, String Authorname);

    @Query("SELECT b FROM Book b WHERE b.language = :code")
    List<Book> findByIdioma(@Param("code") String code);

}
