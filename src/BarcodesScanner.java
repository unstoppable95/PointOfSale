public class BarcodesScanner implements CodesReader {

    private CodesReader codesReader;
    private BarcodesScannerListener barCodesScannerListener;

    public BarcodesScanner(CodesReader codesReader, BarcodesScannerListener barCodesScannerListener) {
        this.codesReader = codesReader;
        this.barCodesScannerListener = barCodesScannerListener;
    }

    @Override
    public String readBarcode() {
        String barcode=codesReader.readBarcode();
        barCodesScannerListener.onScanProduct(barcode);
        return barcode;
    }
}
