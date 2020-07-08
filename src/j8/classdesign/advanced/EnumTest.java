package j8.classdesign.advanced;

public class EnumTest {
    PrinterType printerType ;

    public EnumTest(PrinterType printerType) {
        this.printerType = printerType;
    }

    public void feature(){
        switch (printerType){
            case INKJET:
                System.out.println("INKJET PRINTER"); break;
            case DOTMATRIX:
                System.out.println("DOTMATRIX PRINTER"); break;
            case LASER:
                System.out.println("LASER PRINTER"); break;
        }
    }

    public static void main(String[] args) {
        EnumTest enumTest = new EnumTest(PrinterType.DOTMATRIX);
        enumTest.feature();
        PrinterType printerType = PrinterType.valueOf("LASER");
        enumTest= new EnumTest(printerType);
        enumTest.feature();

    }
}
