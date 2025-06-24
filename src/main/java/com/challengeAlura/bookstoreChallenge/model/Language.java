package com.challengeAlura.bookstoreChallenge.model;

public enum Language {

    ES("es","español"),
    EN("en","ingles"),
    FR("fr","frances"),
    PT("pt","portugues"),
    DE("de","aleman");

    private final String code;
    private final String name;

    Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    // GET CODE BY NAME
    public static String GetCodeByName(String name) {
        for (Language language : values()) {
            if (language.getName().equalsIgnoreCase(name)) {
                return language.getCode();
            }
        }
        throw new IllegalArgumentException("Idioma no reconocido: " + name);
    }

    }
