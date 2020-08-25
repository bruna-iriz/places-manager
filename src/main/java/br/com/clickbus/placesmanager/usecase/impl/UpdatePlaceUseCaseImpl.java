package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.usecase.UpdatePlaceUseCase;
import br.com.clickbus.placesmanager.usecase.gateway.PlaceGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePlaceUseCaseImpl implements UpdatePlaceUseCase {

    private final PlaceGateway placeGateway;

    @Override
    public Place execute(Place place) {
        return placeGateway.update(place);
    }
}
