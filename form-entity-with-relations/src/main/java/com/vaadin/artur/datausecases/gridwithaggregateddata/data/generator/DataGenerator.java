package com.vaadin.artur.datausecases.gridwithaggregateddata.data.generator;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity.Category;
import com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity.Product;
import com.vaadin.artur.datausecases.gridwithaggregateddata.data.service.CategoryRepository;
import com.vaadin.artur.datausecases.gridwithaggregateddata.data.service.ProductRepository;
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
    public CommandLineRunner loadData(ProductRepository productRepository, CategoryRepository categoryRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (productRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Category entities...");
            ExampleDataGenerator<Category> categoryGenerator = new ExampleDataGenerator<>(
                Category.class,
                REFERENCE_TIME
            );
            // categoryGenerator.setData(Category::setId, DataType.UUID);
            categoryGenerator.setData(Category::setName, DataType.TWO_WORDS);
            List<Category> cat = categoryGenerator.create(100, seed);
            cat.forEach(c -> c.setId(UUID.randomUUID().toString()));
            List<Category> categories = categoryRepository.saveAll(cat);

            logger.info("... generating 1000 Product entities...");
            ExampleDataGenerator<Product> productGenerator = new ExampleDataGenerator<>(Product.class, REFERENCE_TIME);
            // productGenerator.setData(Product::setId, DataType.UUID);
            productGenerator.setData(Product::setName, DataType.FOOD_PRODUCT_NAME);

            List<Product> products = productGenerator.create(1000, seed);
            products.forEach(product -> product.setId(UUID.randomUUID().toString()));
            products.forEach(product -> product.setCategory(oneOf(categories)));
            products.forEach(product -> product.setPrice(new BigDecimal(r.nextDouble() * 1000)));

            productRepository.saveAll(products);
            logger.info("Generated demo data");
        };
    }

    private static <T> T oneOf(List<T> options) {
        return options.get(r.nextInt(options.size()));
    }
}
