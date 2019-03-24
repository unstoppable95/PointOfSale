import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {

    private Receipt receipt;

    @Before
    public void setUp(){
        receipt=new Receipt();
    }

    @Test
    public void checkEmptyListSum(){
        assertEquals(Double.valueOf("0.0"), receipt.getSum());
    }

    @Test
    public void checkNonEmptyListSum(){
        Product p = new ProductBuilder("milk").createProduct();

        receipt.addProduct(p);

        assertEquals(p.getPrice(),receipt.getSum());

    }
}