package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.repository.gateway.impl.PlaceGatewayImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GetByIdPlaceUseCaseImplTest {

    @InjectMocks
    private GetByIdPlaceUseCaseImpl getByIdPlaceUseCase;
    @Mock
    PlaceGatewayImpl placeGateway;

    @Test
    @DisplayName("List place by Id")
    public void testShouldGetByIdPlace() {

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

        when(getByIdPlaceUseCase.execute(any(String.class))).thenReturn(ofNullable(place));
        final var response = getByIdPlaceUseCase.execute("1234");
        verify(placeGateway, atLeastOnce()).findById(anyString());

        assertNotNull(response);
        assertEquals("1234", response.stream().findFirst().get().getId());
    }
}