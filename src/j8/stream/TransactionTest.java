package j8.stream;

import j8.stream.model.Currency;
import j8.stream.model.Trader;
import j8.stream.model.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionTest {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300,Currency.EUR),
                new Transaction(raoul, 2012, 1000,Currency.EUR),
                new Transaction(raoul, 2011, 400,Currency.GBP),
                new Transaction(mario, 2012, 710,Currency.USD),
                new Transaction(mario, 2012, 700,Currency.USD),
                new Transaction(alan, 2012, 950,Currency.USD)
        );

        // Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> transaction2011 = transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparingInt(Transaction::getValue)).collect(Collectors.toList());
        // all the unique cities where the traders work
        List<String> cities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());
        // all traders from Cambridge sorted by name
        List<Trader> cambridgeTraders = transactions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equals("Cambridge")).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        //string of all traders’ names sorted alphabetically
        String names = transactions.stream().map(t -> t.getTrader().getName()).sorted().distinct().reduce("", (x, y) -> x + " " + y);
        System.out.println(names);
        //any traders based in Milan
        Trader trader = transactions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equals("Milan")).findAny().orElse(null);
        //all transactions’ values from the traders living in Cambridge.
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).forEach(System.out::println);
        //the highest value of all the transactions
        int max = transactions.stream().map(Transaction::getValue).reduce(0, Math::max);
        //the transaction with the smallest value.
        Transaction smallestTransaction = transactions.stream().reduce((t1, t2) ->t1.getValue() < t2.getValue() ? t1 : t2).orElse(null);
        System.out.println(smallestTransaction);


        //grouping transaction by currrency : imperative style
        Map<Currency,List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction t: transactions) {
            Currency currency = t.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if(transactionsForCurrency==null){
                transactionsForCurrency=new ArrayList<>();
                transactionsByCurrencies.put(currency,transactionsForCurrency);
            }
            transactionsForCurrency.add(t);
        }
        //grouping transaction by currency : stream
        Map<Currency,List<Transaction>> transactionByCurrenciesStream = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));



    }
}
