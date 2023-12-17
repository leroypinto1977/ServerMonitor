package com.leroysamuel.serverwhiz.mongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface YourEntityRepository extends MongoRepository<YourEntity, String> {
    // additional custom queries can be added here
}