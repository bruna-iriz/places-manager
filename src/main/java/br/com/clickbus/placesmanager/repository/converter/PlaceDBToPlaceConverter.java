package br.com.clickbus.placesmanager.repository.converter;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.repository.model.PlaceDB;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaceDBToPlaceConverter {

    private final ModelMapper modelMapper;

    public Place convert(final PlaceDB placeDB) {
        return modelMapper.map(placeDB, Place.class);
    }
}
