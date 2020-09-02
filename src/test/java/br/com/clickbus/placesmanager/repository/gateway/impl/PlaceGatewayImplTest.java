package br.com.clickbus.placesmanager.repository.gateway.impl;

import br.com.clickbus.placesmanager.domain.Place;
import br.com.clickbus.placesmanager.repository.PlaceRepository;
import br.com.clickbus.placesmanager.repository.converter.MergeBetweenPlaceDbAndPlaceConverter;
import br.com.clickbus.placesmanager.repository.converter.PlaceDBToPlaceConverter;
import br.com.clickbus.placesmanager.repository.converter.PlaceToPlaceDBConverter;
import br.com.clickbus.placesmanager.repository.model.PlaceDB;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PlaceGatewayImplTest {

    @InjectMocks
    private PlaceGatewayImpl placeGateway;
    @Mock
    private PlaceRepository placeRepository;
    @Spy
    private PlaceDBToPlaceConverter placeDBToPlaceConverter = new PlaceDBToPlaceConverter(new ModelMapper());
    ;
    @Spy
    private PlaceToPlaceDBConverter placeToPlaceDBConverter = new PlaceToPlaceDBConverter(new ModelMapper());
    ;
    @Spy
    private MergeBetweenPlaceDbAndPlaceConverter mergeBetweenPlaceDbAndPlaceConverter;


    @Test
    @DisplayName("Saving place content")
    public void testShouldSavePlaceContent() {

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

        final var placeDB = PlaceDB
                .builder()
                .id("1234")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .name("name")
                .slug("sao-paulo-sp")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(placeRepository.save(any(PlaceDB.class))).thenReturn(placeDB);

        final var response = placeGateway.save(place);

        verify(placeRepository, atLeastOnce()).save(any(PlaceDB.class));
        verify(placeToPlaceDBConverter, atLeastOnce()).convert(any(Place.class));
        verify(placeDBToPlaceConverter, atLeastOnce()).convert(any(PlaceDB.class));

        assertAll("SavePlaceContent",
                () -> assertEquals("1234", response.getId()),
                () -> assertEquals("Sao Paulo", response.getCity()));
    }


    @Test
    @DisplayName("Update place content")
    public void testShouldUpdatePlaceContent() {

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

        final var placeDB = PlaceDB
                .builder()
                .id("1234")
                .city("Recife")
                .state("Pernambuco")
                .name("name")
                .slug("recife-pe")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(placeRepository.findById(anyString())).thenReturn(Optional.of(placeDB));
        when(placeRepository.save(any())).thenReturn(placeDB);

        final var response = placeGateway.update(place);

        verify(placeRepository, atLeastOnce()).save(any(PlaceDB.class));
        verify(placeToPlaceDBConverter, atLeastOnce()).convert(any(Place.class));
        verify(placeDBToPlaceConverter, atLeastOnce()).convert(any(PlaceDB.class));
        verify(mergeBetweenPlaceDbAndPlaceConverter, atLeastOnce()).convert(any(PlaceDB.class),anyList());

        assertAll("UpdatePlaceContent",
                () -> assertEquals("1234", response.getId()),
                () -> assertEquals("Recife", response.getCity()));
    }

    @Test
    @DisplayName("List all place content")
    public void testShouldListAllPlaceContent() {

        final var placeDB = PlaceDB
                .builder()
                .id("1234")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .name("name")
                .slug("sao-paulo-sp")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(placeRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(placeDB)));
        placeGateway.listAll(0, 10);

        verify(placeRepository, atLeastOnce()).findAll(any(PageRequest.class));
        verify(placeDBToPlaceConverter, atLeastOnce()).convert(any(PlaceDB.class));

    }

    @Test
    @DisplayName("List place content search by id")
    public void testShouldFindByIdPlaceContent() {

        final var placeDB = PlaceDB
                .builder()
                .id("1234")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .name("name")
                .slug("sao-paulo-sp")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(placeRepository.findById(anyString())).thenReturn(Optional.of((placeDB)));

        final var response = placeGateway.findById("1234");

        verify(placeRepository, atLeastOnce()).findById(anyString());
        verify(placeDBToPlaceConverter, atLeastOnce()).convert(any(PlaceDB.class));

        assertAll("FindByIdContent",
                () -> assertEquals("1234", response.get().getId()),
                () -> assertEquals("Sao Paulo", response.get().getCity()));
    }

    @Test
    @DisplayName("Delete place content search by id")
    public void testShouldDeletePlaceContent(){

        placeGateway.delete("1234");
        verify(placeRepository, atLeastOnce()).deleteById("1234");
    }
}