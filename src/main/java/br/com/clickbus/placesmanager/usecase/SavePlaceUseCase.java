package br.com.clickbus.placesmanager.usecase;


import br.com.clickbus.placesmanager.domain.Place;

public interface SavePlaceUseCase {
    Place execute(Place place);
}
