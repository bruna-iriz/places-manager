package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.repository.gateway.PlaceGateway;
import br.com.clickbus.placesmanager.repository.gateway.impl.PlaceGatewayImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ListAllPlaceUseCaseImplTest {

    @InjectMocks
    private ListAllPlaceUseCaseImpl listAllPlaceUseCase;
    @Mock
    PlaceGatewayImpl placeGateway;

    @Test
    @DisplayName("List all places")
    public void testShouldListAllPlaces() {

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

        when(placeGateway.listAll(anyInt(), anyInt())).thenReturn(new PageImpl<>(List.of(place)));
        final var response = listAllPlaceUseCase.execute(0, 0);

        assertNotNull(response);
        assertAll("listAllPlace",
                () -> assertEquals("1234", response.stream().findFirst().get().getId()),
                () -> assertEquals("Sao Paulo", response.stream().findFirst().get().getCity()));
    }
}