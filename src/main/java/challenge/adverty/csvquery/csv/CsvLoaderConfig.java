package challenge.adverty.csvquery.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class CsvLoaderConfig {

    @Bean
    CommandLineRunner populateDatabase(CsvRepository csvRepository) {
        return args -> {
//            csvRepository.deleteAll();
            if (0 != csvRepository.count()) {
                return;
            }
            System.out.println("Reloading CSV data");
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
            long index = 0;
            while (mappingIterator.hasNext()) {
                if (0 == index % 500) {
                    System.out.print("\rProcessing row: " + index);
                }
                csvRepository.insert(mappingIterator.nextValue());
                index ++;
            }
            System.out.println("\nImported " + index + " rows");

            mappingIterator.close();

        };
    }
}
