package br.com.clickbus.placesmanager.controller.converter;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.resource.PlaceResource;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaceResourceRequestToPlaceConverter {

    public final ModelMapper modelMapper;

    public Place convert(PlaceResource placeResource) {
        return modelMapper.map(placeResource, Place.class);
    }
}
