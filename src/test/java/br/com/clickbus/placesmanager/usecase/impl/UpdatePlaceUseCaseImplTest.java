package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.usecase.gateway.impl.PlaceGatewayImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UpdatePlaceUseCaseImplTest {

    @InjectMocks
    UpdatePlaceUseCaseImpl updatePlaceUseCase;
    @Mock
    PlaceGatewayImpl placeGateway;

    @Test
    @DisplayName("Update place by Id")
    public void testShouldUpdatePlace() {

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

        when(placeGateway.update(any(Place.class))).thenReturn(place);
        updatePlaceUseCase.execute(place);

        verify(placeGateway, atLeastOnce()).update(any());
    }
}