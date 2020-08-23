package br.com.clickbus.placesmanager.usecase;

import br.com.clickbus.placesmanager.domain.Place;
import org.springframework.data.domain.Page;

public interface ListAllPlaceUseCase {
    Page<Place> execute(Integer page, Integer size);
}
