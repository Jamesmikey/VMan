package tz.ac.iact.va.config;

import com.mongodb.ReadConcern;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class AppConfig {

    public AppConfig(MongoDatabaseFactory mongoDbFactory) {
        this.mongoDbFactory = mongoDbFactory;
    }

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }

    private final MongoDatabaseFactory mongoDbFactory;

    @Bean
    public MongoTransactionManager transactionManager() {

        TransactionOptions transactionOptions = TransactionOptions.builder().readConcern(ReadConcern.LOCAL).writeConcern(WriteConcern.W1).build();

        return new MongoTransactionManager(mongoDbFactory, transactionOptions);

    }
}
