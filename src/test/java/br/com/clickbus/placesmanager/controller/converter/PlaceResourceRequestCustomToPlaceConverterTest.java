package br.com.clickbus.placesmanager.controller.converter;

import br.com.clickbus.placesmanager.resource.PlaceResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class PlaceResourceRequestCustomToPlaceConverterTest {

    @InjectMocks
    private PlaceResourceRequestCustomToPlaceConverter placeResourceRequestCustomToPlaceConverter;

    @Test
    @DisplayName("Convert Place Request Custom to Place")
    public void test() {

        final var placeResource = PlaceResource
                .builder()
                .id("1234")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .name("name")
                .slug("sao-paulo-sp")
                .build();

        var id = "1234";
        placeResourceRequestCustomToPlaceConverter.convert(placeResource, id);
    }
}