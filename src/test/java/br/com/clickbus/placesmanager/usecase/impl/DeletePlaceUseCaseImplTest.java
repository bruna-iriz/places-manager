package br.com.clickbus.placesmanager.usecase.impl;

import br.com.clickbus.placesmanager.repository.gateway.impl.PlaceGatewayImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DeletePlaceUseCaseImplTest {

    @InjectMocks
    private DeletePlaceUseCaseImpl deletePlaceUseCase;
    @Mock
    private PlaceGatewayImpl placeGateway;

    @Test
    @DisplayName("Delete place by Id")
    public void testShouldDeletePlace() {

        doNothing().when(placeGateway).delete(anyString());
        deletePlaceUseCase.execute("1234");

        verify(placeGateway, atLeastOnce()).delete(any());
    }
}