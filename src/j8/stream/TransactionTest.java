package j8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionTest {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
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
    }
}
