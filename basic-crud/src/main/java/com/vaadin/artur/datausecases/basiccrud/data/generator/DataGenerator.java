package com.vaadin.artur.datausecases.basiccrud.data.generator;

import com.vaadin.artur.datausecases.basiccrud.data.entity.Product;
import com.vaadin.artur.datausecases.basiccrud.data.service.ProductRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@SpringComponent
public class DataGenerator {
    private static final LocalDateTime REFERENCE_TIME = LocalDateTime.of(2021, 2, 24, 0, 0, 0);
    private static Random r = new Random(123);

    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (productRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 1000 Product entities...");
            ExampleDataGenerator<Product> productGenerator = new ExampleDataGenerator<>(Product.class, REFERENCE_TIME);
            productGenerator.setData(Product::setName, DataType.FOOD_PRODUCT_NAME);

            List<Product> products = productGenerator.create(1000, seed);
            products.forEach(product -> product.setId(UUID.randomUUID()));
            products.forEach(product -> product.setPrice(new BigDecimal(r.nextDouble() * 1000)));

            productRepository.saveAll(products);
            logger.info("Generated demo data");
        };
    }
}
