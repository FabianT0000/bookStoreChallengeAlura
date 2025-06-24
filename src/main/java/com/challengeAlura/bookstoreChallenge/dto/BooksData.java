package com.challengeAlura.bookstoreChallenge.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData(
        String title,
        @JsonAlias("authors")List<AuthorsData> author,
        @JsonAlias("languages") List<String> language,
        @JsonAlias("download_count") Double downloadCount
        ) {
}
