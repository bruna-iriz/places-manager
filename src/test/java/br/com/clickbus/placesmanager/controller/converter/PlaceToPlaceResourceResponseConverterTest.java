package br.com.clickbus.placesmanager.controller.converter;

import br.com.clickbus.placesmanager.domain.Place;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class PlaceToPlaceResourceResponseConverterTest {

    @InjectMocks
    private PlaceToPlaceResourceResponseConverter placeToPlaceResourceResponseConverter;
    @Spy
    private ModelMapper modelMapper;

    @Test
    @DisplayName("Convert Place Request to Place")
    public void testingModelMapperConverter() {

        final var place = Place
                .builder()
                .id("1234")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .name("name")
                .slug("sao-paulo-sp")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        placeToPlaceResourceResponseConverter.convert(place);
        verify(modelMapper, atLeastOnce()).map(any(), any());


    }
}