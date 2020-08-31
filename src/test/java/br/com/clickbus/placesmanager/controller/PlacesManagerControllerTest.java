package br.com.clickbus.placesmanager.controller;

import br.com.clickbus.placesmanager.controller.converter.PlaceResourceRequestCustomToPlaceConverter;
import br.com.clickbus.placesmanager.controller.converter.PlaceResourceRequestToPlaceConverter;
import br.com.clickbus.placesmanager.controller.converter.PlaceToPlaceResourceResponseConverter;
import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.resource.PlaceResource;
import br.com.clickbus.placesmanager.usecase.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PlacesManagerControllerTest {

    @InjectMocks
    private PlacesManagerController placesManagerController;

    @Spy
    private PlaceResourceRequestToPlaceConverter placeResourceRequestToPlaceConverter = new PlaceResourceRequestToPlaceConverter(new ModelMapper());
    @Spy
    private PlaceToPlaceResourceResponseConverter placeToPlaceResourceResponseConverter = new PlaceToPlaceResourceResponseConverter(new ModelMapper());
    @Spy
    private PlaceResourceRequestCustomToPlaceConverter placeResourceRequestCustomToPlaceConverter = new PlaceResourceRequestCustomToPlaceConverter();

    @Mock
    private SavePlaceUseCase savePlaceUseCase;
    @Mock
    private UpdatePlaceUseCase updatePlaceUseCase;
    @Mock
    private ListAllPlaceUseCase listAllPlaceUseCase;
    @Mock
    private GetByIdPlaceUsecase getByIdPlaceUsecase;
    @Mock
    private DeletePlaceUseCase deletePlaceUseCase;

    @Test
    @DisplayName("Saving a place, expected success")
    public void testShouldExecuteControllerSave() {

        final var place = Place
                .builder()
                .id("1234")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .name("name")
                .slug("sao-paulo-sp")
                .createdAt(LocalDateTime.now())
                .build();

        final var request = PlaceResource
                .builder()
                .id("1234")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .name("name")
                .slug("sao-paulo-sp")
                .build();

        when(savePlaceUseCase.execute(any(Place.class))).thenReturn(place);
        final var response = placesManagerController.save(request);

        verify(placeResourceRequestToPlaceConverter, atLeastOnce()).convert(any());
        verify(placeToPlaceResourceResponseConverter, atLeastOnce()).convert(any());
        verify(savePlaceUseCase, atLeastOnce()).execute(any());

        assertNotNull(response);
        assertEquals("Sao Paulo", response.getCity());
    }


    @Test
    @DisplayName("Updating a place, expected success")
    public void testShouldExecuteControllerUpdate() {

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

        final var request = PlaceResource
                .builder()
                .id("1234")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .name("sp")
                .slug("sao-paulo-sp")
                .build();

        when(updatePlaceUseCase.execute(any(Place.class))).thenReturn(place);
        final var response = placesManagerController.update(request, "1234");

        verify(placeToPlaceResourceResponseConverter, atLeastOnce()).convert(any());
        verify(updatePlaceUseCase, atLeastOnce()).execute(any());

        assertNotNull(response);

    }

    @Test
    @DisplayName("List all place, expected success")
    public void testShouldExecuteControllerListAll() {

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

        when(listAllPlaceUseCase.execute(anyInt(), anyInt())).thenReturn(new PageImpl<>(List.of(place)));
        final var response = placesManagerController.listAll(0, 10);

        verify(listAllPlaceUseCase, atLeastOnce()).execute(anyInt(), anyInt());

        assertNotNull(response);
    }


    @Test
    @DisplayName("List by id place, expected success")
    public void testShouldExecuteControllerListById() {

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

        when(getByIdPlaceUsecase.execute(any(String.class))).thenReturn(Optional.ofNullable(place));
        final var response = placesManagerController.getById("1234");

        verify(getByIdPlaceUsecase, atLeastOnce()).execute(any(String.class));
        verify(placeToPlaceResourceResponseConverter, atLeastOnce()).convert(any(Place.class));

        assertNotNull(response);

    }

    @Test
    @DisplayName("Delete place, expected success")
    public void testShouldExecuteControllerDelete() {

        doNothing().when(deletePlaceUseCase).execute(any(String.class));
        placesManagerController.delete("1234");

        verify(deletePlaceUseCase, atLeastOnce()).execute(any());
    }
}