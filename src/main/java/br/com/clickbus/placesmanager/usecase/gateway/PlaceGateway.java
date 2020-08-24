package br.com.clickbus.placesmanager.usecase.gateway;


import br.com.clickbus.placesmanager.domain.Place;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PlaceGateway {
    Place save(Place place);
    void delete(String id);
    Page<Place> listAll(int page, int size);
    Optional<Place> findById(String id);
}
