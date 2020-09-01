package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.usecase.GetByIdPlaceUsecase;
import br.com.clickbus.placesmanager.usecase.gateway.PlaceGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetByIdPlaceUseCaseImpl implements GetByIdPlaceUsecase {

    private final PlaceGateway placeGateway;

    @Override
    public Optional<Place> execute(String id) {
        return placeGateway.findById(id);
    }
}
