package br.com.clickbus.placesmanager.repository;

import br.com.clickbus.placesmanager.repository.model.PlaceDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceRepository extends MongoRepository<PlaceDB, String> {
}
