package com.leroysamuel.serverwhiz.mongoDB;

// YourEntityService.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YourEntityService {
    @Autowired
    private YourEntityRepository repository;

    public List<YourEntity> getAllEntities() {
        return repository.findAll();
    }

    public YourEntity getEntityById(String id) {
        return repository.findById(id).orElse(null);
    }

    public YourEntity createEntity(YourEntity entity) {
        return repository.save(entity);
    }

    public YourEntity updateEntity(String id, YourEntity updatedEntity) {
        if (repository.existsById(id)) {
            updatedEntity.setId(id);
            return repository.save(updatedEntity);
        }
        return null;
    }

    public void deleteEntity(String id) {
        repository.deleteById(id);
    }
}
