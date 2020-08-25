package br.com.clickbus.placesmanager.controller.converter;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.resource.PlaceResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaceResourceRequestCustomToPlaceConverter {

    public Place convert(PlaceResource placeResource, String id) {
        return Place
                .builder()
                .id(id)
                .name(placeResource.getName())
                .slug(placeResource.getSlug())
                .city(placeResource.getCity())
                .state(placeResource.getState())
                .build();
    }
}
