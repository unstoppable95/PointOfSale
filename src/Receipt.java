

import java.util.ArrayList;

public class Receipt {

    private double sum=0.0;
    private ArrayList<Product> listOfProducts;

    public Receipt() {
        listOfProducts = new ArrayList<>();
    }

    public void addProduct(Product p){
        listOfProducts.add(p);
        sum+=p.getPrice();
    }

    public double getSum() {
        return sum;
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    @Override
    public String toString(){
        String content = "";
        for (Product p: listOfProducts){
            content+=p.toString()+"\n";
        }
        content += "Sum: " + sum;

        return content;
    }
}
