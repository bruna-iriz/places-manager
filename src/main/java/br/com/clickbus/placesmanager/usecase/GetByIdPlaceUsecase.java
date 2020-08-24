package br.com.clickbus.placesmanager.usecase;

import br.com.clickbus.placesmanager.domain.Place;

import java.util.Optional;

public interface GetByIdPlaceUsecase {
    Optional<Place> execute(String id);
}
