package com.jordanportfolio.travelbooking.dao;

import com.jordanportfolio.travelbooking.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "divisions", path = "divisions")
public interface DivisionRepository extends JpaRepository<Division, Long> {

    // Evidence-based: fetch existing divisions in a deterministic order
    List<Division> findTop5ByOrderByIdAsc();
}
