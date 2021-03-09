package com.vaadin.artur.datausecases.manytoonecrud.data.generator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.vaadin.artur.datausecases.manytoonecrud.data.entity.CategoryEntity;
import com.vaadin.artur.datausecases.manytoonecrud.data.entity.ProductEntity;
import com.vaadin.artur.datausecases.manytoonecrud.data.service.CategoryRepository;
import com.vaadin.artur.datausecases.manytoonecrud.data.service.ProductRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;

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

            logger.info("... generating 50 Category entities...");
            ExampleDataGenerator<CategoryEntity> categoryGenerator = new ExampleDataGenerator<>(CategoryEntity.class,
                    REFERENCE_TIME);
            categoryGenerator.setData(CategoryEntity::setId, DataType.UUID);
            categoryGenerator.setData(CategoryEntity::setName, DataType.FOOD_PRODUCT_NAME);

            List<CategoryEntity> categories = categoryRepository.saveAll(categoryGenerator.create(50, seed));

            logger.info("... generating 1000 Product entities...");
            ExampleDataGenerator<ProductEntity> productGenerator = new ExampleDataGenerator<>(ProductEntity.class,
                    REFERENCE_TIME);
            productGenerator.setData(ProductEntity::setId, DataType.UUID);
            productGenerator.setData(ProductEntity::setName, DataType.FOOD_PRODUCT_NAME);

            List<ProductEntity> products = productGenerator.create(1000, seed);
            products.forEach(product -> product.setPrice(new BigDecimal(r.nextDouble() * 1000)));
            products.forEach(product -> product.setCategory(oneOf(categories)));

            productRepository.saveAll(products);
            logger.info("Generated demo data");
        };
    }

    private static <T> T oneOf(List<T> options) {
        return options.get(r.nextInt(options.size()));
    }

}
