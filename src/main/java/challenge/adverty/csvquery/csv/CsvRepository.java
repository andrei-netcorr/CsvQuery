package challenge.adverty.csvquery.csv;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CsvRepository extends MongoRepository<CsvRecord, String> {
}
