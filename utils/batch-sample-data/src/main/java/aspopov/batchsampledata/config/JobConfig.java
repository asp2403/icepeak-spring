package aspopov.batchsampledata.config;

import aspopov.batchsampledata.domain.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class JobConfig {
    private static final int CHUNK_SIZE = 10;
    private final Logger logger = LoggerFactory.getLogger("JobLogger");

    public static final String JOB_NAME = "populateSampleData";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job populateSampleData() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(loadVendors())
                .build();
    }

    @Bean
    public FlatFileItemReader<Vendor> vendorReader() {
        return null;
    }

    @Bean
    public ItemWriter<Vendor> vendorWriter() {
        return null;
    }

    @Bean
    public Step loadVendors() {
        return stepBuilderFactory.get("importAuthorsStep")
                .<Vendor, Vendor>chunk(CHUNK_SIZE)
                .reader(vendorReader())
                .writer(vendorWriter())
                .listener(new ItemReadListener<Vendor>() {


                              @Override
                              public void beforeRead() {

                              }

                              @Override
                              public void afterRead(Vendor vendor) {
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
                .build();
    }


}
