package com.broccolism.stream.model;

public class Question {
    private final int number;
    private final String question;

    private Question(int number, String question) {
        this.number = number;
        this.question = question;
    }

    public static Question of(int number, String question) {
        return new Question(number, question);
    }

    public String toString() {
        return String.format("=[Q%d]==============================\n%s\n---", number, question);
    }
}
