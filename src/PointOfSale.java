

public class PointOfSale {

    private LcdDisplay lcdDisplay;
    private Receipt receipt;
    private Printer printer;
    private BarCodesScanner barCodesScanner;

    public enum BarCodes {INVALID_BARCODE , EXIT, PRODUCT_NOT_FOUND }

    public PointOfSale(LcdDisplay lcdDisplay, Printer printer, BarCodesScanner barCodesScanner) {
        this.lcdDisplay = lcdDisplay;
        this.receipt = new Receipt();
        this.printer = printer;
        this.barCodesScanner = barCodesScanner;
    }

    public void scanProduct() {
        String barcode=barCodesScanner.scanProductBarcode();
        if(barcode==null || barcode.isEmpty()){
            lcdDisplay.printCommunication(BarCodes.INVALID_BARCODE.name());
        }
        else if (barcode.equals(BarCodes.EXIT.name())){
                printer.printReceipt(receipt.toString());
                lcdDisplay.printTotalSum(receipt.getSum());
        }
        else {
            Product p = new Product(barcode);
            if (p!=null){
                receipt.addProduct(p);
                lcdDisplay.printCommunication(p.toString());
            }
            else {
                lcdDisplay.printCommunication(BarCodes.PRODUCT_NOT_FOUND.name());
            }
        }

    }



    public LcdDisplay getLcdDisplay() {
        return lcdDisplay;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Printer getPrinter() {
        return printer;
    }

    public BarCodesScanner getBarCodesScanner() {
        return barCodesScanner;
    }
}
