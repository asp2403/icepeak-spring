package aspopov.batchsampledata.config;


import aspopov.batchsampledata.dto.ModelDto;
import aspopov.batchsampledata.dto.ImageDto;
import aspopov.batchsampledata.dto.SkiDto;
import aspopov.batchsampledata.dto.VendorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
@EnableBatchProcessing
public class JobConfig {
    private static final int CHUNK_SIZE = 10;
    private final Logger logger = LoggerFactory.getLogger("JobLogger");
    private final AppProperties appProperties;

    public static final String JOB_NAME = "populateSampleData";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    public JobConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public Job populateSampleData() throws IOException {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(loadVendors())
                .next(loadModels())
                .next(loadModelLargeImages())
                .next(loadModelSmallImages())
                .next(loadSkiProducts())
                .next(loadSki())
                .build();
    }

    @Bean
    public FlatFileItemReader<VendorDto> vendorReader() {
        return new FlatFileItemReaderBuilder<VendorDto>()
                .name("vendorItemReader")
                .resource(new FileSystemResource(appProperties.getVendorsFile()))
                .lineMapper((s, i) -> {
                    var fieldsValues = s.split(",");
                    var modelDto = new VendorDto(Long.valueOf(fieldsValues[0]), fieldsValues[1]);
                    return modelDto;
                })
                .build();
    }

    @Bean
    public ItemWriter<VendorDto> vendorWriter() {
        return new JdbcBatchItemWriterBuilder<VendorDto>()
                .dataSource(dataSource)
                .sql("insert into vendor(id_vendor, vendor) values (:id, :value)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step loadVendors() {
        return stepBuilderFactory.get("loadVendorsStep")
                .<VendorDto, VendorDto>chunk(CHUNK_SIZE)
                .reader(vendorReader())
                .writer(vendorWriter())
                .listener(new ItemReadListener<VendorDto>() {


                              @Override
                              public void beforeRead() {

                              }

                              @Override
                              public void afterRead(VendorDto vendor) {
                                  logger.info(vendor.getValue());
                              }

                              @Override
                              public void onReadError(@NonNull Exception e) {
                                  logger.info("Ошибка чтения");
                              }
                          }

                )
                .listener(new StepExecutionListener() {
                              @Override
                              public void beforeStep(StepExecution stepExecution) {
                                  logger.info("Импортируем производителей...");
                              }

                              @Override
                              public ExitStatus afterStep(StepExecution stepExecution) {
                                  return null;
                              }

                          }

                )
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public ItemProcessor<ModelDto, ModelDto> modelProcessor() {
        return item -> {
            item.setModelName(item.getModelName().trim());
            item.setDescription(item.getDescription().replaceAll("^\"|\"$", "").trim());
            return item;
        };
    }


    @Bean
    public FlatFileItemReader<ModelDto> modelReader() {
        return new FlatFileItemReaderBuilder<ModelDto>()
                .name("modelItemReader")
                .resource(new FileSystemResource(appProperties.getSkiModelsFile()))
                .lineMapper((s, i) -> {
                    var fieldsValues = s.split(",");
                    var modelDto = new ModelDto(
                            Long.valueOf(fieldsValues[0]),
                            fieldsValues[1],
                            Long.valueOf(fieldsValues[2]),
                            Integer.valueOf(fieldsValues[3]),
                            Integer.valueOf(fieldsValues[4]),
                            Integer.valueOf(fieldsValues[5]),
                            fieldsValues[6]);
                    return modelDto;
                })
                .build();
    }

    @Bean
    public ItemWriter<ModelDto> modelWriter() {
        return new JdbcBatchItemWriterBuilder<ModelDto>()
                .dataSource(dataSource)
                .sql("insert into model(id_model, model, id_vendor, description, id_gender, id_age, price) " +
                        "values (:id, :modelName, :idVendor, :description, :idGender, :idAge, :price)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step loadModels() {
        return stepBuilderFactory.get("loadModelsStep")
                .<ModelDto, ModelDto>chunk(CHUNK_SIZE)
                .reader(modelReader())
                .processor(modelProcessor())
                .writer(modelWriter())
                .listener(new ItemReadListener<ModelDto>() {


                              @Override
                              public void beforeRead() {

                              }

                              @Override
                              public void afterRead(ModelDto modelDto) {
                                  logger.info(modelDto.getModelName());
                              }

                              @Override
                              public void onReadError(@NonNull Exception e) {
                                  logger.info("Ошибка чтения");
                              }
                          }

                )
                .listener(new StepExecutionListener() {
                              @Override
                              public void beforeStep(StepExecution stepExecution) {
                                  logger.info("Импортируем модели...");
                              }

                              @Override
                              public ExitStatus afterStep(StepExecution stepExecution) {
                                  return null;
                              }

                          }

                )
                .allowStartIfComplete(true)
                .build();
    }


    @Bean
    public ItemReader<File> modelImageLargeReader() throws IOException {
        List<File> files = Files.walk(Paths.get(appProperties.getSkiImagesLarge()))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        return new IteratorItemReader<>(files);
    }

    @Bean
    @StepScope
    public ItemProcessor<File, ImageDto> modelImageProcessor() {
        return item -> {
            var imageDto = new ImageDto();
            var fName = item.getName();
            var index = fName.indexOf('.');
            var strId =  fName.substring(0, index);
            imageDto.setId(Long.valueOf(strId));
            imageDto.setImage(Files.readAllBytes(item.toPath()));
            return imageDto;
        };
    }

    @Bean
    public ItemWriter<ImageDto> modelImageLargeWriter() {
        return new JdbcBatchItemWriterBuilder<ImageDto>()
                .dataSource(dataSource)
                .sql("update model set image_large = :image where id_model = :id")
                .beanMapped()
                .build();
    }

    @Bean
    public Step loadModelLargeImages() throws IOException {
        return stepBuilderFactory.get("loadModelsLargeImagesStep")
                .<File, ImageDto>chunk(CHUNK_SIZE)
                .reader(modelImageLargeReader())
                .processor(modelImageProcessor())
                .writer(modelImageLargeWriter())
                .listener(new ItemReadListener<File>() {


                              @Override
                              public void beforeRead() {

                              }

                              @Override
                              public void afterRead(File file) {
                                  logger.info(file.getName());
                              }

                              @Override
                              public void onReadError(@NonNull Exception e) {
                                  logger.info("Ошибка чтения");
                              }
                          }

                )
                .listener(new StepExecutionListener() {
                              @Override
                              public void beforeStep(StepExecution stepExecution) {
                                  logger.info("Импортируем большие изображения моделей...");
                              }

                              @Override
                              public ExitStatus afterStep(StepExecution stepExecution) {
                                  return null;
                              }

                          }

                )
                .allowStartIfComplete(true)
                .build();
    }


    @Bean
    public ItemReader<File> modelImageSmallReader() throws IOException {
        List<File> files = Files.walk(Paths.get(appProperties.getSkiImagesSmall()))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        return new IteratorItemReader<>(files);
    }

    @Bean
    public ItemWriter<ImageDto> modelImageSmallWriter() {
        return new JdbcBatchItemWriterBuilder<ImageDto>()
                .dataSource(dataSource)
                .sql("update model set image_small = :image where id_model = :id")
                .beanMapped()
                .build();
    }

    @Bean
    public Step loadModelSmallImages() throws IOException {
        return stepBuilderFactory.get("loadModelsImagesSmallStep")
                .<File, ImageDto>chunk(CHUNK_SIZE)
                .reader(modelImageSmallReader())
                .processor(modelImageProcessor())
                .writer(modelImageSmallWriter())
                .listener(new ItemReadListener<File>() {


                              @Override
                              public void beforeRead() {

                              }

                              @Override
                              public void afterRead(File file) {
                                  logger.info(file.getName());
                              }

                              @Override
                              public void onReadError(@NonNull Exception e) {
                                  logger.info("Ошибка чтения");
                              }
                          }

                )
                .listener(new StepExecutionListener() {
                              @Override
                              public void beforeStep(StepExecution stepExecution) {
                                  logger.info("Импортируем маленькие изображения моделей...");
                              }

                              @Override
                              public ExitStatus afterStep(StepExecution stepExecution) {
                                  return null;
                              }

                          }

                )
                .allowStartIfComplete(true)
                .build();
    }

    @StepScope
    @Bean
    public FlatFileItemReader<SkiDto> skiProductReader() {
        return new FlatFileItemReaderBuilder<SkiDto>()
                .name("skiProductReader")
                .resource(new FileSystemResource(appProperties.getSkiFile()))
                .lineMapper((s, i) -> {
                    var fieldsValues = s.split(",");
                    var skiDto = new SkiDto(
                            Long.valueOf(fieldsValues[0]),
                            Long.valueOf(fieldsValues[1]),
                            Integer.valueOf(fieldsValues[2]),
                            Integer.valueOf(fieldsValues[3])
                            );
                    return skiDto;
                })
                .build();
    }

    @Bean
    public ItemWriter<SkiDto> skiProductWriter() {
        return new JdbcBatchItemWriterBuilder<SkiDto>()
                .dataSource(dataSource)
                .sql("insert into product(id_product, id_model, qty_available) values (:id, :idModel, :qtyAvailable)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step loadSkiProducts() {
        return stepBuilderFactory.get("loadSkiProductStep")
                .<SkiDto, SkiDto>chunk(CHUNK_SIZE)
                .reader(skiProductReader())
                .writer(skiProductWriter())
                .listener(new ItemReadListener<SkiDto>() {


                              @Override
                              public void beforeRead() {

                              }

                              @Override
                              public void afterRead(SkiDto skiDto) {
                                  logger.info(String.valueOf(skiDto.getId()));
                              }

                              @Override
                              public void onReadError(@NonNull Exception e) {
                                  logger.info("Ошибка чтения");
                              }
                          }

                )
                .listener(new StepExecutionListener() {
                              @Override
                              public void beforeStep(StepExecution stepExecution) {
                                  logger.info("Импортируем продукты (лыжи)...");
                              }

                              @Override
                              public ExitStatus afterStep(StepExecution stepExecution) {
                                  return null;
                              }

                          }

                )
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public ItemWriter<SkiDto> skiWriter() {
        return new JdbcBatchItemWriterBuilder<SkiDto>()
                .dataSource(dataSource)
                .sql("insert into ski(id_product, height) values (:id, :height)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step loadSki() {
        return stepBuilderFactory.get("loadSkiStep")
                .<SkiDto, SkiDto>chunk(CHUNK_SIZE)
                .reader(skiProductReader())
                .writer(skiWriter())
                .listener(new ItemReadListener<SkiDto>() {


                              @Override
                              public void beforeRead() {

                              }

                              @Override
                              public void afterRead(SkiDto skiDto) {
                                  logger.info(String.valueOf(skiDto.getId()));
                              }

                              @Override
                              public void onReadError(@NonNull Exception e) {
                                  logger.info("Ошибка чтения");
                              }
                          }

                )
                .listener(new StepExecutionListener() {
                              @Override
                              public void beforeStep(StepExecution stepExecution) {
                                  logger.info("Импортируем лыжи...");
                              }

                              @Override
                              public ExitStatus afterStep(StepExecution stepExecution) {
                                  return null;
                              }

                          }

                )
                .allowStartIfComplete(true)
                .build();
    }


}
