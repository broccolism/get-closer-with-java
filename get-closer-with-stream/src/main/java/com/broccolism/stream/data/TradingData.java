package com.broccolism.stream.data;

import java.util.Arrays;
import java.util.List;

import com.broccolism.stream.model.Trader;
import com.broccolism.stream.model.Transaction;

public class TradingData {
    public static final Trader raoul = new Trader("Raoul", "Cambridge");
    public static final Trader mario = new Trader("Mario", "Milan");
    public static final Trader alan = new Trader("Alan", "Cambridge");
    public static final Trader brain = new Trader("Brian", "Cambridge");

    public static final List<Transaction> transactions = Arrays.asList(
        new Transaction(brain, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );
}
