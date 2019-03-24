

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
            invalidBarcode();
        }
        else if (barcode.equals(EXIT)){
                exit();
        }
        else {
            Product p = productDao.getProductByBarcode(barcode);
            if (p!=null){
                productExist(p);
            }
            else {
                productNotFound();
            }
        }

    }

    private void invalidBarcode(){
        lcdDisplay.printCommunication(INVALID_BARCODE);
    }

    private void exit(){
        printer.printReceipt(receipt.toString());
        lcdDisplay.printTotalSum(receipt.getSum());
    }

    private void productExist(Product p){
        receipt.addProduct(p);
        lcdDisplay.printCommunication(p.toString());
    }

    private void productNotFound(){
        lcdDisplay.printCommunication(PRODUCT_NOT_FOUND);
    }

    public Receipt getReceipt() {
        return receipt;
    }
}
