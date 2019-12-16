package com.okanciftci.cukatify.persistence;

import com.okanciftci.cukatify.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
