package com.vaadin.artur.datausecases.gridaggregateddata.data.generator;

import com.vaadin.artur.datausecases.gridaggregateddata.data.entity.Product;
import com.vaadin.artur.datausecases.gridaggregateddata.data.entity.Sale;
import com.vaadin.artur.datausecases.gridaggregateddata.data.entity.SaleRow;
import com.vaadin.artur.datausecases.gridaggregateddata.data.service.ProductRepository;
import com.vaadin.artur.datausecases.gridaggregateddata.data.service.SaleRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
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
    public CommandLineRunner loadData(ProductRepository productRepository, SaleRepository saleRepository) {
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
            productGenerator.setData(Product::setId, DataType.ID);
            productGenerator.setData(Product::setName, DataType.FOOD_PRODUCT_NAME);
            productGenerator.setData(Product::setPrice, DataType.PRICE);
            productGenerator.setData(Product::setCategory, DataType.WORD);
            List<Product> productNamePriceCategoryEntities = productRepository
                    .saveAll(productGenerator.create(1000, seed));

            logger.info("... generating sales...");

            ExampleDataGenerator<Sale> saleGenerator = new ExampleDataGenerator<>(Sale.class, REFERENCE_TIME);
            saleGenerator.setData(Sale::setId, DataType.ID);
            List<Sale> sales = saleGenerator.create(500, 1);
            ExampleDataGenerator<SaleRow> saleRowGenerator = new ExampleDataGenerator<>(SaleRow.class, REFERENCE_TIME);
            saleRowGenerator.setData(SaleRow::setId, DataType.ID);
            saleRowGenerator.setData(SaleRow::setSum, DataType.PRICE);

            for (Sale sale : sales) {
                List<SaleRow> saleRows = saleRowGenerator.create(5, sale.getId());
                saleRows.forEach(saleRow -> {
                    Product p = oneOf(productNamePriceCategoryEntities);
                    saleRow.setSale(sale);
                    saleRow.setProduct(p);
                    saleRow.setCount(r.nextInt(20));
                    saleRow.setSum(saleRow.getCount() * p.getPrice());
                });
                sale.setItems(saleRows);
            }
            saleRepository.saveAll(sales);
            logger.info("Generated demo data");
        };
    }

    private static <T> T oneOf(List<T> options) {
        return options.get(r.nextInt(options.size()));
    }
}
