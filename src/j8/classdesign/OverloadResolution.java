package j8.classdesign;

public class OverloadResolution {


    public static void aMethod (int val) { System.out.println ("int"); }
    public static void aMethod (short val) { System.out.println ("short"); }
    public static void aMethod (Object val) { System.out.println ("object"); }
    public static void aMethod (String val) { System.out.println ("String"); }
    public static int aMethod(int a,int b){
        System.out.println("INT*INT");
        return 0;
    }

    public static void main(String[] args) {

        //if there is no method definition that takes tha same args
        //the compiler resolves it with a method that takes  the closest type in size

        byte a = 9;
        aMethod(a);
        aMethod(9);
        Integer i = 10;

        aMethod(i);
        aMethod("10");
    }
}
