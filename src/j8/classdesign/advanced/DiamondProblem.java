package j8.classdesign.advanced;

//if two super interfaces define methods with same signature, the compiler
//will issue an error so we have to resolve the conflict manually

interface Interface1{
    default void show(){
        System.out.println("I'm the interface 1");
    }
}

interface Interface2{
    default void show(){
        System.out.println("I'm the interface 2");
    }
}

public class DiamondProblem implements Interface1,Interface2{

    public static void main(String[] args) {
        new DiamondProblem().show();
    }

    @Override
    public void show() {
        Interface2.super.show();
    }
}
