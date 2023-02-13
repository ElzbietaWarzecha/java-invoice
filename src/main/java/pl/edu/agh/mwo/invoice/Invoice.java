package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Products quantity has to be a positive value");
        }
        products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Products quantity has to be a positive value");
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal sum = new BigDecimal(0);
        if (this.products != null) {
            for (Product product : products.keySet()) {
                sum = sum.add(product.getPrice().multiply(BigDecimal.valueOf(products.get(product))));
            }
        }
        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal sum = new BigDecimal(0);
        if (this.products != null) {
            for (Product product : products.keySet()) {
                sum = sum.add(product.getTaxPercent().multiply(BigDecimal.valueOf(products.get(product))).multiply(product.getPrice()));
            }
        }
        return sum;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal(0);
        if (this.products != null) {
            for (Product product : products.keySet()) {
                sum = sum.add(product.getPriceWithTax().multiply(BigDecimal.valueOf(products.get(product))));
            }
        }
        return sum;
    }
}
