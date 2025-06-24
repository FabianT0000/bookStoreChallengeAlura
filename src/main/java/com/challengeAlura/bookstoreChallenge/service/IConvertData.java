package com.challengeAlura.bookstoreChallenge.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> clase);
}
