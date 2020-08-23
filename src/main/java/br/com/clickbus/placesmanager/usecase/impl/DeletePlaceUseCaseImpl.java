package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.usecase.DeletePlaceUseCase;
import br.com.clickbus.placesmanager.usecase.gateway.PlaceGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePlaceUseCaseImpl implements DeletePlaceUseCase {

    private final PlaceGateway placeGateway;

    @Override
    public void execute(String id) {
        placeGateway.delete(id);
    }
}
