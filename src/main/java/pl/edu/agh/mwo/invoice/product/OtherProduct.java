package pl.edu.agh.mwo.invoice.product;

import org.hamcrest.Matchers;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

public class OtherProduct extends Product {
    public OtherProduct(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"));
    }
}
