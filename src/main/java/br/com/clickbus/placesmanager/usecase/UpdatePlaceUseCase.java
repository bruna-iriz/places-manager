package br.com.clickbus.placesmanager.usecase;

import br.com.clickbus.placesmanager.domain.Place;

public interface UpdatePlaceUseCase {
    Place execute(Place place);
}
