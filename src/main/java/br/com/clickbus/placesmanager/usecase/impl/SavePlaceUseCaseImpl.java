package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.usecase.SavePlaceUseCase;
import br.com.clickbus.placesmanager.repository.gateway.PlaceGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SavePlaceUseCaseImpl implements SavePlaceUseCase {

    private final PlaceGateway placeGateway;

    @Override
    public Place execute(Place place) {
        return placeGateway.save(place);
    }
}
