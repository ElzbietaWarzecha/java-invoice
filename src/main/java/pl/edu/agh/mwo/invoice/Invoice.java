package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Invoice {
    private Map<Product, Integer> products = new LinkedHashMap<Product, Integer>();

    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    private List<String> invoiceToPrint = new ArrayList<>();

    public int getNumber() {
        return number;
    }

    public List<String> getInvoiceToPrint() {
        return invoiceToPrint;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        int counter = 0;
        for (Product prod : products.keySet()) {
            if (prod.getName().equals(product.getName())) {
                counter++;
            }
        }
        if (counter > 0) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public void prepareInvoiceToPrint() {
        this.invoiceToPrint.add("Numer faktury: " + this.number);
        int counter = 0;
        for (Product product : products.keySet()) {
            int quantity = products.get(product);
            BigDecimal q = new BigDecimal(quantity);
            this.invoiceToPrint.add("Nazwa: " + product.getName()
                    + ", liczba sztuk: " + quantity
                    + ", cena netto: " + product.getPrice().multiply(q)
                    + ", cena brutto: " + product.getPriceWithTax().multiply(q));
            counter++;
        }
        this.invoiceToPrint.add("Liczba pozycji: " + counter);
    }

    @Override
    public String toString() {
        String printMe = "";
        for (String s : this.invoiceToPrint
        ) {
            printMe = printMe + s + "\n";
        }
        return printMe;
    }

}
