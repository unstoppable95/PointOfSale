import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public  class ProductBuilderTest {

    private ProductBuilder productBuilder;
    private String exampleBarcode = "123";

    @Before
    public void setUp(){
        productBuilder=new ProductBuilder(exampleBarcode);
    }

    @Test
    public void checkConvertToPrice(){
        Double tmp = 0.0;
        for (char ch: exampleBarcode.toCharArray()) {
            tmp+=Double.valueOf(ch);
        }
        assertEquals(tmp , productBuilder.createProduct().getPrice());
    }

    @Test
    public void checkCreateProduct(){

        assertTrue(productBuilder.createProduct() instanceof Product);
    }
}