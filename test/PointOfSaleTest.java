import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PointOfSaleTest {

    public static final String INVALID_BARCODE = "Warning, invalid barcode";
    public static final String PRODUCT_NOT_FOUND = "Sorry, product not found";
    public static final String EXIT = "Exit";
    public static final String CORRECT_BARCODE = "milk";

    @Mock
    CodesReader codesReader;

    @Mock
    ProductDao productDao;

    @Mock
    Printer printer;

    @Mock
    LcdDisplay lcdDisplay;

    PointOfSale pointOfSale ;

    @Before
    public void setUp(){
      Mockito.when(codesReader.readBarcode()).thenReturn(CORRECT_BARCODE);
      Mockito.when(productDao.getProductByBarcode(CORRECT_BARCODE)).thenReturn(new ProductBuilder(CORRECT_BARCODE).createProduct());
      pointOfSale=new PointOfSale(lcdDisplay,printer,productDao);

    }

    @Test
    public void existsInDatabase(){
        assertEquals(new ProductBuilder(codesReader.readBarcode()).createProduct().getName(),productDao.getProductByBarcode(codesReader.readBarcode()).getName());

    }

    @Test
    public void displayOnLcd(){
        Product p=new ProductBuilder(codesReader.readBarcode()).createProduct();
        pointOfSale.onScanProduct(codesReader.readBarcode());
        Mockito.verify(lcdDisplay).printCommunication(p.toString());
        Mockito.verifyNoMoreInteractions(lcdDisplay);
    }

    @Test
    public void productNotFound(){
        pointOfSale.onScanProduct("badBarcode");
        Mockito.verify(lcdDisplay).printCommunication(PRODUCT_NOT_FOUND);
        Mockito.verifyNoMoreInteractions(lcdDisplay);
    }


    @Test
    public void invalidBarcodeEmpty(){
        pointOfSale.onScanProduct("");
        Mockito.verify(lcdDisplay).printCommunication(INVALID_BARCODE);
        Mockito.verifyNoMoreInteractions(lcdDisplay);
    }

    @Test
    public void invalidBarcodeNull(){
        pointOfSale.onScanProduct(null);
        Mockito.verify(lcdDisplay).printCommunication(INVALID_BARCODE);
        Mockito.verifyNoMoreInteractions(lcdDisplay);
    }

    @Test
    public void printReceipt(){
        pointOfSale.onScanProduct(codesReader.readBarcode());
        Product p = productDao.getProductByBarcode(codesReader.readBarcode());
        pointOfSale.onScanProduct(EXIT);

        Mockito.verify(lcdDisplay).printCommunication(p.toString());
       // Mockito.verify(lcdDisplay).printCommunication(pointOfSale.getReceipt().toString());
        Mockito.verify(printer).printReceipt(pointOfSale.getReceipt().toString());
        //Mockito.verifyNoMoreInteractions(lcdDisplay);
        Mockito.verifyNoMoreInteractions(printer);
    }


    @Test
    public void printEmptyReceipt(){

        pointOfSale.onScanProduct(EXIT);

       // Mockito.verify(lcdDisplay).printCommunication(p.toString());
        // Mockito.verify(lcdDisplay).printCommunication(pointOfSale.getReceipt().toString());
        Mockito.verify(printer).printReceipt("Sum: 0.0");
        //Mockito.verifyNoMoreInteractions(lcdDisplay);
        Mockito.verifyNoMoreInteractions(printer);
    }


}