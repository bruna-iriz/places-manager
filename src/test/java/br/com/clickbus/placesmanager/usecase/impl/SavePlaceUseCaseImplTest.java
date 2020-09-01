package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.usecase.gateway.PlaceGateway;
import br.com.clickbus.placesmanager.usecase.gateway.impl.PlaceGatewayImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SavePlaceUseCaseImplTest {

    @InjectMocks
    private SavePlaceUseCaseImpl savePlaceUseCase;
    @Mock
    PlaceGatewayImpl placeGateway;

    @Test
    @DisplayName("Saving a place")
    public void testShouldLSavePlace() {

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

        when(placeGateway.save(any())).thenReturn(place);
        final var response = savePlaceUseCase.execute(place);
        verify(placeGateway, atLeastOnce()).save(any(Place.class));

        assertAll("savePlace",
                () -> assertEquals("1234", response.getId()),
                () -> assertEquals("Sao Paulo", response.getCity()));
    }
}