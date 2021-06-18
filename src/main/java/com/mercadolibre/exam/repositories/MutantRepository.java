package com.mercadolibre.exam.repositories;

import java.util.ArrayList;

import com.mercadolibre.exam.models.MutantModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends CrudRepository<MutantModel, Long> {
    public abstract MutantModel findByDna(String dna);

    public abstract ArrayList<MutantModel> findByIsMutant(Boolean isMutant);
}