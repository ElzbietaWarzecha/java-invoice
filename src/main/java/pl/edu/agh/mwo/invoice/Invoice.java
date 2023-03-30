package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Invoice {
    private String number;

    private Map<Product, Integer> products = new LinkedHashMap<Product, Integer>();

    private List<String> invoiceToPrint = new ArrayList<>();

    public Invoice() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");
        this.number = localDateTime.format(format);
    }

    public String getNumber() {
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
        }
        else {
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
            this.invoiceToPrint.add("Nazwa: " + product.getName() + ", liczba sztuk: "
                    + products.get(product) + ", cena: " + product.getPrice());
            counter++;
        }
        this.invoiceToPrint.add("Liczba pozycji: " + counter);
    }
}
