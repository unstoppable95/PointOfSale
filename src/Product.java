

import java.util.Random;

public class Product {
    private String barcode;
    private String name;
    private Double price;


    public Product(String barcode) {
        this.barcode = barcode;
        this.name =barcode+"_product";
        this.price = new Random().nextDouble()+1;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return barcode;
    }

}
