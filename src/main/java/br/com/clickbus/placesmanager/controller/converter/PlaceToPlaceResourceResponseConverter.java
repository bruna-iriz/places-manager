package br.com.clickbus.placesmanager.controller.converter;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.resource.PlaceResource;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaceToPlaceResourceResponseConverter {

    private final ModelMapper modelMapper;

    public PlaceResource convert(Place place){
        return modelMapper.map(place, PlaceResource.class);
    }
}
