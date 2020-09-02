package br.com.clickbus.placesmanager.repository.gateway.impl;


import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.repository.PlaceRepository;
import br.com.clickbus.placesmanager.repository.converter.MergeBetweenPlaceDbAndPlaceConverter;
import br.com.clickbus.placesmanager.repository.converter.PlaceDBToPlaceConverter;
import br.com.clickbus.placesmanager.repository.converter.PlaceToPlaceDBConverter;
import br.com.clickbus.placesmanager.repository.gateway.PlaceGateway;
import br.com.clickbus.placesmanager.repository.model.PlaceDB;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class PlaceGatewayImpl implements PlaceGateway {

    private final PlaceDBToPlaceConverter placeDBToPlaceConverter;
    private final PlaceToPlaceDBConverter placeToPlaceDBConverter;
    private final PlaceRepository placeRepository;
    private final MergeBetweenPlaceDbAndPlaceConverter mergeBetweenPlaceDbAndPlaceConverter;

    @Override
    public Place save(final Place place) {

        final var placeDB = placeToPlaceDBConverter.convert(place);
        final var savedPlaceDB = placeRepository.save(placeDB);
        return placeDBToPlaceConverter.convert(savedPlaceDB);
    }

    @Override
    public Place update(Place place) {
        return placeRepository.findById(place.getId())
                .map(placeDB -> getPlaceDB(place, placeDB))
                .map(placeRepository::save)
                .map(placeDBToPlaceConverter::convert)
                .orElseThrow();
    }

    private PlaceDB getPlaceDB(Place place, PlaceDB placeDB) {
        final var convert = placeToPlaceDBConverter.convert(place);
        return mergeBetweenPlaceDbAndPlaceConverter.convert(placeDB, List.of(convert));
    }

    public Page<Place> listAll(int page, int size) {
        return placeRepository.findAll(PageRequest.of(page, size))
                .map(placeDBToPlaceConverter::convert);
    }

    @Override
    public Optional<Place> findById(String id) {
        return placeRepository.findById(id)
                .map(placeDBToPlaceConverter::convert);
    }

    public void delete(String id) {
        placeRepository.deleteById(id);
    }
}
