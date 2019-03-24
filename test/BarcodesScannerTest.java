
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class BarcodesScannerTest {

    private static final String correctBarcode = "example";

    @Mock
    private CodesReader codesReader;

    @Mock
    private BarcodesScannerListener barcodesScannerListener;

    private BarcodesScanner barcodeScanner;

    @Before
    public void setUp(){
       barcodeScanner=new BarcodesScanner(codesReader,barcodesScannerListener);

        Mockito.when(codesReader.readBarcode()).thenReturn(correctBarcode);
    }

    @Test
    public void checkRead(){
        barcodeScanner.readBarcode();
        verify(barcodesScannerListener).onScanProduct(correctBarcode);
    }

}