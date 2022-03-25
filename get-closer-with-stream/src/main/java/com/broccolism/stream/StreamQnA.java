package com.broccolism.stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.List;

import com.broccolism.stream.data.TradingData;
import com.broccolism.stream.model.Question;
import com.broccolism.stream.model.Trader;
import com.broccolism.stream.model.Transaction;
import com.broccolism.stream.util.FormattedPrinter;

public class StreamQnA {
    public static void main(String[] args) {
        final List<Transaction> transactions = TradingData.transactions;
        final FormattedPrinter printer = new FormattedPrinter();

        printer.printQnA(Question.of(1, "2011년에 일어난 모든 트랜잭션을 찾아 값 기준, 오름차순으로 정리하시오."),
            transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList()));

        printer.printQnA(Question.of(2, "거래자가 근무하는 모든 도시를 중복 없이 나열하시오."),
            transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList()));

        printer.printQnA(Question.of(3, "케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오."),
            transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList()));

        printer.printQnA(Question.of(4, "모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오."),
            transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(toList()));

        printer.printQnA(Question.of(5, "밀라노에 거래자가 있는가?"),
            transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .anyMatch(city -> city.equals("Milan")));

        printer.printQnA(Question.of(6, "케임브리지에 거주하는 거래자의 모든 트랜잭션 값을 출력하시오."),
            transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(toList()));

        printer.printQnA(Question.of(7, "전체 트랜잭션 중 최댓값은 얼마인가?"),
            transactions.stream()
                .max(comparing(Transaction::getValue))
                .get()
                .getValue());

        printer.printQnA(Question.of(8, "전체 트랜잭션 중 최솟값은 얼마인가?"),
            transactions.stream()
                .min(comparing(Transaction::getValue))
                .get()
                .getValue());
    }
}
