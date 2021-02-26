package com.vaadin.artur.datausecases.data.generator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.vaadin.artur.datausecases.data.entity.Product;
import com.vaadin.artur.datausecases.data.entity.Sale;
import com.vaadin.artur.datausecases.data.entity.SaleRow;
import com.vaadin.artur.datausecases.data.service.ProductNamePriceCategoryRepository;
import com.vaadin.artur.datausecases.data.service.SaleRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@SpringComponent
public class DataGenerator {

        private static Random r = new Random(123);

        @Bean
        public CommandLineRunner loadData(ProductNamePriceCategoryRepository productNamePriceCategoryRepository,
                        SaleRepository saleRepository) {
                return args -> {
                        Logger logger = LoggerFactory.getLogger(getClass());
                        if (productNamePriceCategoryRepository.count() != 0L) {
                                logger.info("Using existing database");
                                return;
                        }
                        int seed = 123;

                        logger.info("Generating demo data");

                        logger.info("... generating 100 Product Name Price Category entities...");
                        ExampleDataGenerator<Product> productNamePriceCategoryRepositoryGenerator = new ExampleDataGenerator<>(
                                        Product.class, LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        productNamePriceCategoryRepositoryGenerator.setData(Product::setId, DataType.ID);
                        productNamePriceCategoryRepositoryGenerator.setData(Product::setName,
                                        DataType.FOOD_PRODUCT_NAME);
                        productNamePriceCategoryRepositoryGenerator.setData(Product::setPrice, DataType.PRICE);
                        productNamePriceCategoryRepositoryGenerator.setData(Product::setCategory, DataType.WORD);
                        List<Product> productNamePriceCategoryEntities = productNamePriceCategoryRepository
                                        .saveAll(productNamePriceCategoryRepositoryGenerator.create(100, seed));

                        logger.info("... generating sales...");

                        ExampleDataGenerator<Sale> saleGenerator = new ExampleDataGenerator<>(Sale.class,
                                        LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        saleGenerator.setData(Sale::setId, DataType.ID);
                        List<Sale> sales = saleGenerator.create(100, 1);
                        ExampleDataGenerator<SaleRow> saleRowGenerator = new ExampleDataGenerator<>(SaleRow.class,
                                        LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        saleRowGenerator.setData(SaleRow::setId, DataType.ID);
                        saleRowGenerator.setData(SaleRow::setSum, DataType.PRICE);

                        for (Sale sale : sales) {
                                List<SaleRow> saleRows = saleRowGenerator.create(10, sale.getId());
                                saleRows.forEach(saleRow -> {
                                        Product p = oneOf(productNamePriceCategoryEntities);
                                        saleRow.setSale(sale);
                                        saleRow.setProduct(p);
                                        saleRow.setSum(saleRow.getCount() * p.getPrice());
                                });
                        }
                        saleRepository.saveAll(sales);
                        logger.info("Generated demo data");
                };
        }

        private static <T> T oneOf(List<T> options) {
                return options.get(r.nextInt(options.size()));
        }
}
