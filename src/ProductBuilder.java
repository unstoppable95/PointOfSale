public class ProductBuilder {

    private String barcode;
    private String name;
    private Double price;

    public ProductBuilder(String barcode, String name, Double price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }

    public Product createProduct(){
        Product product = new Product();
        product.setBarcode(barcode);
        product.setName(name);
        product.setPrice(price);
        return product;
    }
}
