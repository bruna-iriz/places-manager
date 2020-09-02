package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.usecase.ListAllPlaceUseCase;
import br.com.clickbus.placesmanager.repository.gateway.PlaceGateway;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListAllPlaceUseCaseImpl implements ListAllPlaceUseCase {

    private final PlaceGateway placeGateway;

    @Override
    public Page<Place> execute(Integer page, Integer size) {
        return placeGateway.listAll(page, size);
    }
}
