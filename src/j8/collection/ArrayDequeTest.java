package j8.collection;


import java.util.ArrayDeque;
import java.util.Deque;

class SplQueue{
    private Deque<String> splQ = new ArrayDeque<>();

    void addInQueue(String custmer){
        splQ.addLast(custmer);
    }

    void removeFront(){
        splQ.removeFirst();
    }
    void removeBack(){
        splQ.removeLast();
    }
    void printQueue(){
        System.out.println("Special queue contains: "+splQ);
    }
}

public class ArrayDequeTest {
    public static void main(String[] args) {
        SplQueue splQueue = new SplQueue();
        splQueue.addInQueue("HAMZA");
        splQueue.addInQueue("AHMED");
        splQueue.addInQueue("AYOUB");
        splQueue.addInQueue("IKRAM");
        splQueue.addInQueue("MAROUN");

        splQueue.printQueue();
        splQueue.removeFront();
        splQueue.removeBack();
        splQueue.printQueue();


    }

}
