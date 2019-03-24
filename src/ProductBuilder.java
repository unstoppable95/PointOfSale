public class ProductBuilder {

    private String barcode;
    private String name;
    private Double price;

    public ProductBuilder(String barcode) {
        this.barcode = barcode;
        this.name = "prod_" + barcode;
        this.price =  convertToPrice(barcode);
    }

    private Double convertToPrice(String s ){
        Double tmp=0.0;
        for(char ch : s.toCharArray()){
            tmp+=Double.valueOf(ch);
        }
        return  tmp;

    }

    public Product createProduct(){
        Product product = new Product();
        product.setBarcode(barcode);
        product.setName(name);
        product.setPrice(price);
        return product;
    }
}
