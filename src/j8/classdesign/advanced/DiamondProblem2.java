package j8.classdesign.advanced;


interface BaseInterface {
    default void foo(){
        System.out.println("BASE INTERFASE");
    }
}

class BaseClass{
    public void foo(){
        System.out.println("BASE CLASS");
    }
}

public class DiamondProblem2 extends BaseClass implements BaseInterface {

    public static void main(String[] args) {
        //prints BASE CLASS because class wins
        new DiamondProblem2().foo();

    }
}
