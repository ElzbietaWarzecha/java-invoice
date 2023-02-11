package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    public void addProduct(Product product) {
        // TODO: implement
    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
    }

    public BigDecimal getSubtotal() {
        BigDecimal sum = new BigDecimal(0);
        if (this.products != null) {
            for (Product product : products) {
                sum.add(product.getPriceWithTax());
            }
        }
        return sum;
    }

    public BigDecimal getTax() {
        return new BigDecimal(0);
    }

    public BigDecimal getTotal() {
        return new BigDecimal(0);
    }
}
