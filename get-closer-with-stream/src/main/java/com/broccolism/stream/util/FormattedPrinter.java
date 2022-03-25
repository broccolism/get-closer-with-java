package com.broccolism.stream.util;

import com.broccolism.stream.model.Question;

public class FormattedPrinter {
    private static void printAnswer(Object answer) {
        System.out.println(answer != null ? answer : "(no answer)");
        System.out.println("====================================\n");
    }

    public void printQnA(Question question, Object answer) {
        printQuestion(question);
        printAnswer(answer);
    }

    private void printQuestion(Question question) {
        System.out.println(question);
    }
}
