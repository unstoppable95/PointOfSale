

public class PointOfSale implements BarcodesScannerListener {

    private LcdDisplay lcdDisplay;
    private Receipt receipt;
    private Printer printer;
    private ProductDao productDao;

    public static final String INVALID_BARCODE = "Warning, invalid barcode";
    public static final String PRODUCT_NOT_FOUND = "Sorry, product not found";
    public static final String EXIT = "Exit";

    public PointOfSale(LcdDisplay lcdDisplay, Printer printer,ProductDao productDao) {
        this.lcdDisplay = lcdDisplay;
        this.receipt = new Receipt();
        this.printer = printer;
        this.productDao=productDao;
    }

    @Override
    public void onScanProduct(String barcode) {

        if(barcode==null || barcode.isEmpty()){
            lcdDisplay.printCommunication(INVALID_BARCODE);
        }
        else if (barcode.equals(EXIT)){
                printer.printReceipt(receipt.toString());
                lcdDisplay.printTotalSum(receipt.getSum());
        }
        else {
            Product p = productDao.getProductByBarcode(barcode);
            if (p!=null){
                receipt.addProduct(p);
                lcdDisplay.printCommunication(p.toString());
            }
            else {
                lcdDisplay.printCommunication(PRODUCT_NOT_FOUND);
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

   
}
