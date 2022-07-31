package aspopov.batchsampledata.config;


import aspopov.batchsampledata.dto.ModelDto;
import aspopov.batchsampledata.dto.VendorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;


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
    public Job populateSampleData() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(loadVendors())
                .next(loadModels())
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
                            fieldsValues[6],
                            1);
                    return modelDto;
                })
                .build();
    }

    @Bean
    public ItemWriter<ModelDto> modelWriter() {
        return new JdbcBatchItemWriterBuilder<ModelDto>()
                .dataSource(dataSource)
                .sql("insert into model(id_model, model, id_vendor, description, id_gender, id_age, price, id_product_type) " +
                        "values (:id, :modelName, :idVendor, :description, :idGender, :idAge, :price, :idProductType)")
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


}
