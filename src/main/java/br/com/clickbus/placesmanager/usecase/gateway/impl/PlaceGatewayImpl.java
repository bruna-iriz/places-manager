package br.com.clickbus.placesmanager.usecase.gateway.impl;


import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.repository.PlaceRepository;
import br.com.clickbus.placesmanager.repository.converter.PlaceDBToPlaceConverter;
import br.com.clickbus.placesmanager.repository.converter.PlaceToPlaceDBConverter;
import br.com.clickbus.placesmanager.usecase.gateway.PlaceGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PlaceGatewayImpl implements PlaceGateway {

    private final PlaceDBToPlaceConverter placeDBToPlaceConverter;
    private final PlaceToPlaceDBConverter placeToPlaceDBConverter;
    private final PlaceRepository placeRepository;


    @Override
    public Place save(final Place place) {

        final var placeDB = placeToPlaceDBConverter.convert(place);
        final var savedPlaceDB = placeRepository.save(placeDB);
        return placeDBToPlaceConverter.convert(savedPlaceDB);
    }
}
