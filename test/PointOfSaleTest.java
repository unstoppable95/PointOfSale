import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PointOfSaleTest {

    public static final String INVALID_BARCODE = "INVALID_BARCODE";
    public static final String EXIT = "EXIT";
    public static final String PRODUCT_NOT_FOUND = "PRODUCT_NOT_FOUND";
    public static final String CORRECT_BARCODE = "MILK";

    @Mock
    BarcodesScanner barCodesScanner;

    @Mock
    Printer printer;

    @Mock
    LcdDisplay lcdDisplay;

    PointOfSale pointOfSale ;

    @Before
    public void setUp(){
        Mockito.when(barCodesScanner.scanProductBarcode()).thenReturn(CORRECT_BARCODE);
        pointOfSale=new PointOfSale(lcdDisplay,printer,barCodesScanner);
    }

    @Test
    public void existsInDatabase(){
        assertEquals(new Product(CORRECT_BARCODE).getBarcode(), barCodesScanner.scanProductBarcode());
    }

    @Test
    public void DisplayOnLcd(){
        Product p=new Product(CORRECT_BARCODE);
        pointOfSale.scanProduct();
        Mockito.verify(lcdDisplay).printCommunication(p.toString());
        Mockito.verifyNoMoreInteractions(lcdDisplay);
    }

    @Test
    public void ProductNotFound(){

    }


    @Test
    public void InvalidBarcode(){

    }





}