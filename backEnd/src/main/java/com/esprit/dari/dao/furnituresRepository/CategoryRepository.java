package com.esprit.dari.dao.furnituresRepository;

import com.esprit.dari.entities.furnituresEntities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
