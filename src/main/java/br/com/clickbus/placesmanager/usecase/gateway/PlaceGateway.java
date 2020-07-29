package br.com.clickbus.placesmanager.usecase.gateway;


import br.com.clickbus.placesmanager.domain.Place;

public interface PlaceGateway {
    Place save(Place place);
}
