package br.com.clickbus.placesmanager.controller;

import br.com.clickbus.placesmanager.controller.converter.PlaceResourceRequestToPlaceConverter;
import br.com.clickbus.placesmanager.controller.converter.PlaceToPlaceResourceResponseConverter;
import br.com.clickbus.placesmanager.resource.PlaceResource;
import br.com.clickbus.placesmanager.usecase.DeletePlaceUseCase;
import br.com.clickbus.placesmanager.usecase.ListAllPlaceUseCase;
import br.com.clickbus.placesmanager.usecase.SavePlaceUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("v1/places")
public class PlacesManagerController {

    private final PlaceResourceRequestToPlaceConverter placeResourceRequestToPlaceConverter;
    private final PlaceToPlaceResourceResponseConverter placeToPlaceResourceResponseConverter;
    private final SavePlaceUseCase savePlaceUseCase;
    private final ListAllPlaceUseCase listAllPlaceUseCase;
    private final DeletePlaceUseCase deletePlaceUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceResource save(@RequestBody final PlaceResource request) {
        log.info("[PLACE-MANAGER][POST][REQUEST] {} ", request);
        final var place = placeResourceRequestToPlaceConverter.convert(request);
        final var placeSaved = savePlaceUseCase.execute(place);
        final var placeResponse = placeToPlaceResourceResponseConverter.convert(place);
        log.info("[PLACE-MANAGER][POST][RESPONSE] {}", placeSaved);
        return placeResponse;
    }

    @GetMapping
    public Page<PlaceResource> listAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        log.info("[PLACE-MANAGER][LIST-ALL][REQUEST]: page: {} size {}", page, size);
        return listAllPlaceUseCase.execute(page, size)
                .map(placeToPlaceResourceResponseConverter::convert);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String id) {
        log.info("[PLACE-MANAGER][DELETE][REQUEST] {}", id);
        deletePlaceUseCase.execute(id);
    }
}
