package br.com.clickbus.placesmanager.repository.converter;

import br.com.clickbus.placesmanager.repository.model.PlaceDB;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Optional.ofNullable;

@Component
@NoArgsConstructor
public class MergeBetweenPlaceDbAndPlaceConverter {

    public PlaceDB convert(PlaceDB place, final List<PlaceDB> placesContent) {
        return placesContent
                .stream()
                .filter(placeContent -> placeContent.getId().equalsIgnoreCase(place.getId()))
                .map(placeContent -> mergePlace(placeContent, place))
                .findAny()
                .get();
    }

    private PlaceDB mergePlace(PlaceDB placeContent, PlaceDB place) {
        final var id = ofNullable(placeContent.getId())
                .orElse(place.getId());
        return place
                .toBuilder()
                .id(id)
                .city(placeContent.getCity())
                .name(placeContent.getName())
                .state(placeContent.getState())
                .slug(placeContent.getSlug())
                .build();
    }
}
