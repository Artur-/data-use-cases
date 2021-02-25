package com.vaadin.artur.datausecases.data.generator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.vaadin.artur.datausecases.data.entity.Employee;
import com.vaadin.artur.datausecases.data.entity.PersonWithArticles;
import com.vaadin.artur.datausecases.data.entity.PersonWithRelatives;
import com.vaadin.artur.datausecases.data.entity.ProductNamePriceCategory;
import com.vaadin.artur.datausecases.data.entity.ProductWithNamePrice;
import com.vaadin.artur.datausecases.data.entity.ProductWithNotes;
import com.vaadin.artur.datausecases.data.entity.ProductWithTags;
import com.vaadin.artur.datausecases.data.entity.Sale;
import com.vaadin.artur.datausecases.data.entity.SaleRow;
import com.vaadin.artur.datausecases.data.entity.User;
import com.vaadin.artur.datausecases.data.service.EmployeeRepository;
import com.vaadin.artur.datausecases.data.service.PersonWithArticlesRepository;
import com.vaadin.artur.datausecases.data.service.PersonWithRelativesRepository;
import com.vaadin.artur.datausecases.data.service.ProductNamePriceCategoryRepository;
import com.vaadin.artur.datausecases.data.service.ProductWithNamePriceRepository;
import com.vaadin.artur.datausecases.data.service.ProductWithNotesRepository;
import com.vaadin.artur.datausecases.data.service.ProductWithTagsRepository;
import com.vaadin.artur.datausecases.data.service.UserRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.pro.licensechecker.Product;

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
        public CommandLineRunner loadData(ProductWithNamePriceRepository productWithNamePriceRepository,
                        ProductNamePriceCategoryRepository productNamePriceCategoryRepository,
                        EmployeeRepository employeeRepository, UserRepository userRepository,
                        ProductWithNotesRepository productWithNotesRepository,
                        ProductWithTagsRepository productWithTagsRepository,
                        PersonWithArticlesRepository personWithArticlesRepository,
                        PersonWithRelativesRepository personWithRelativesRepository) {
                return args -> {
                        Logger logger = LoggerFactory.getLogger(getClass());
                        if (productWithNamePriceRepository.count() != 0L) {
                                logger.info("Using existing database");
                                return;
                        }
                        int seed = 123;

                        logger.info("Generating demo data");

                        logger.info("... generating 100 Product With Name Price entities...");
                        ExampleDataGenerator<ProductWithNamePrice> productWithNamePriceRepositoryGenerator = new ExampleDataGenerator<>(
                                        ProductWithNamePrice.class, LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        productWithNamePriceRepositoryGenerator.setData(ProductWithNamePrice::setId, DataType.ID);
                        productWithNamePriceRepositoryGenerator.setData(ProductWithNamePrice::setName,
                                        DataType.FOOD_PRODUCT_NAME);
                        productWithNamePriceRepositoryGenerator.setData(ProductWithNamePrice::setPrice,
                                        DataType.NUMBER_UP_TO_100);
                        productWithNamePriceRepository
                                        .saveAll(productWithNamePriceRepositoryGenerator.create(100, seed));

                        logger.info("... generating 100 Product Name Price Category entities...");
                        ExampleDataGenerator<ProductNamePriceCategory> productNamePriceCategoryRepositoryGenerator = new ExampleDataGenerator<>(
                                        ProductNamePriceCategory.class, LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        productNamePriceCategoryRepositoryGenerator.setData(ProductNamePriceCategory::setId,
                                        DataType.ID);
                        productNamePriceCategoryRepositoryGenerator.setData(ProductNamePriceCategory::setName,
                                        DataType.FOOD_PRODUCT_NAME);
                        productNamePriceCategoryRepositoryGenerator.setData(ProductNamePriceCategory::setPrice,
                                        DataType.PRICE);
                        productNamePriceCategoryRepositoryGenerator.setData(ProductNamePriceCategory::setCategory,
                                        DataType.WORD);
                        List<ProductNamePriceCategory> productNamePriceCategoryEntities = productNamePriceCategoryRepository
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
                                        ProductNamePriceCategory p = oneOf(productNamePriceCategoryEntities);
                                        saleRow.setSale(sale);
                                        saleRow.setProduct(p);
                                        saleRow.setSum(saleRow.getCount() * p.getPrice());
                                });
                        }

                        logger.info("... generating 100 Employee entities...");
                        ExampleDataGenerator<Employee> employeeRepositoryGenerator = new ExampleDataGenerator<>(
                                        Employee.class, LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        employeeRepositoryGenerator.setData(Employee::setId, DataType.ID);
                        employeeRepositoryGenerator.setData(Employee::setName, DataType.FULL_NAME);
                        employeeRepositoryGenerator.setData(Employee::setDepartment, DataType.WORD);
                        employeeRepository.saveAll(employeeRepositoryGenerator.create(100, seed));

                        logger.info("... generating 100 User entities...");
                        ExampleDataGenerator<User> userRepositoryGenerator = new ExampleDataGenerator<>(User.class,
                                        LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        userRepositoryGenerator.setData(User::setId, DataType.ID);
                        userRepositoryGenerator.setData(User::setUsername, DataType.FIRST_NAME);
                        userRepositoryGenerator.setData(User::setRoles, DataType.WORD);
                        userRepositoryGenerator.setData(User::setPasswordHash, DataType.WORD);
                        userRepository.saveAll(userRepositoryGenerator.create(100, seed));

                        logger.info("... generating 100 Product With Notes entities...");
                        ExampleDataGenerator<ProductWithNotes> productWithNotesRepositoryGenerator = new ExampleDataGenerator<>(
                                        ProductWithNotes.class, LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        productWithNotesRepositoryGenerator.setData(ProductWithNotes::setId, DataType.ID);
                        productWithNotesRepositoryGenerator.setData(ProductWithNotes::setName, DataType.WORD);
                        productWithNotesRepositoryGenerator.setData(ProductWithNotes::setNotes, DataType.WORD);
                        productWithNotesRepository.saveAll(productWithNotesRepositoryGenerator.create(100, seed));

                        logger.info("... generating 100 Product With Tags entities...");
                        ExampleDataGenerator<ProductWithTags> productWithTagsRepositoryGenerator = new ExampleDataGenerator<>(
                                        ProductWithTags.class, LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        productWithTagsRepositoryGenerator.setData(ProductWithTags::setId, DataType.ID);
                        productWithTagsRepositoryGenerator.setData(ProductWithTags::setName, DataType.WORD);
                        productWithTagsRepositoryGenerator.setData(ProductWithTags::setTags, DataType.WORD);
                        productWithTagsRepository.saveAll(productWithTagsRepositoryGenerator.create(100, seed));

                        logger.info("... generating 100 Person With Articles entities...");
                        ExampleDataGenerator<PersonWithArticles> personWithArticlesRepositoryGenerator = new ExampleDataGenerator<>(
                                        PersonWithArticles.class, LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        personWithArticlesRepositoryGenerator.setData(PersonWithArticles::setId, DataType.ID);
                        personWithArticlesRepositoryGenerator.setData(PersonWithArticles::setName, DataType.WORD);
                        personWithArticlesRepositoryGenerator.setData(PersonWithArticles::setLikes, DataType.WORD);
                        personWithArticlesRepository.saveAll(personWithArticlesRepositoryGenerator.create(100, seed));

                        logger.info("... generating 100 Person With Relatives entities...");
                        ExampleDataGenerator<PersonWithRelatives> personWithRelativesRepositoryGenerator = new ExampleDataGenerator<>(
                                        PersonWithRelatives.class, LocalDateTime.of(2021, 2, 24, 0, 0, 0));
                        personWithRelativesRepositoryGenerator.setData(PersonWithRelatives::setId, DataType.ID);
                        personWithRelativesRepositoryGenerator.setData(PersonWithRelatives::setName, DataType.WORD);
                        personWithRelativesRepositoryGenerator.setData(PersonWithRelatives::setRelatives,
                                        DataType.WORD);
                        personWithRelativesRepository.saveAll(personWithRelativesRepositoryGenerator.create(100, seed));

                        logger.info("Generated demo data");
                };
        }

        private static <T> T oneOf(List<T> options) {
                return options.get(r.nextInt(options.size()));
        }
}
