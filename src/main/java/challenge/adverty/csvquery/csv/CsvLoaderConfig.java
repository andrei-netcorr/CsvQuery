package challenge.adverty.csvquery.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class CsvLoaderConfig {

    @Bean
    CommandLineRunner populateDatabase(CsvRepository csvRepository) {
        return args -> {
            URL resource = CsvLoaderConfig.class.getClassLoader().getResource("in_data.csv");

            CsvMapper csvMapper = new CsvMapper();

            CsvSchema csvSchema = csvMapper
                    .schemaFor(CsvRecord.class)
                    .withHeader()
                    .withColumnSeparator(',')
                    .withComments();

            MappingIterator<CsvRecord> mappingIterator = csvMapper
                    .readerWithSchemaFor(CsvRecord.class)
                    .with(csvSchema)
                    .readValues(resource);
            while (mappingIterator.hasNext()) {
                System.out.println(mappingIterator.nextValue());
            }

            mappingIterator.close();

        };
    }
}
