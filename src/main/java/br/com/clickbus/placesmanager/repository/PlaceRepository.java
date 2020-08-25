package br.com.clickbus.placesmanager.repository;

import br.com.clickbus.placesmanager.repository.model.PlaceDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlaceRepository extends MongoRepository<PlaceDB, String> {
    Optional<PlaceDB> findById(String id);
}
