package com.leroysamuel.serverwhiz.mongoDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entities")
public class YourEntityController {
    @Autowired
    private YourEntityService entityService;

    @GetMapping
    public List<YourEntity> getAllEntities() {
        return entityService.getAllEntities();
    }

    @GetMapping("/{id}")
    public YourEntity getEntityById(@PathVariable String id) {
        return entityService.getEntityById(id);
    }

    @PostMapping
    public YourEntity createEntity(@RequestBody YourEntity entity) {
        return entityService.createEntity(entity);
    }

    @PutMapping("/{id}")
    public YourEntity updateEntity(@PathVariable String id, @RequestBody YourEntity updatedEntity) {
        return entityService.updateEntity(id, updatedEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteEntity(@PathVariable String id) {
        entityService.deleteEntity(id);
    }
}
