package j8.classdesign.advanced;

public enum PrinterType {
    DOTMATRIX(5), INKJET(10), LASER(50);
    private int capacity;

    PrinterType(int i) {
        this.capacity = i;
    }

    public int getCapacity() {
        return capacity;
    }

}


