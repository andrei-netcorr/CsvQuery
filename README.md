I presumed the following:
1. The CSV might be much larger than 24k rows, so the solution would have to scale.
This ruled out simply using Java Streams API or CSVJDBC.
2. Seeing what your business deals in, namely aggregating many heterogeneous data sources, 
   I presumed the CSV structure would be simple and rigid so there is no need for RDBMS like Postgres.
Thus Mongo seemed to be the faster method though all have their merits and drawbacks.

### TODO
- CommandLineRunner to import data
- Service, DAO & model for csv record
- Add fancy query to service and expose in controller
- logical operators and nested conditions