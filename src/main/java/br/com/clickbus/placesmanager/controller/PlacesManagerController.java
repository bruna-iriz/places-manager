package br.com.clickbus.placesmanager.controller;

import br.com.clickbus.placesmanager.controller.converter.PlaceResourceRequestCustomToPlaceConverter;
import br.com.clickbus.placesmanager.controller.converter.PlaceResourceRequestToPlaceConverter;
import br.com.clickbus.placesmanager.controller.converter.PlaceToPlaceResourceResponseConverter;
import br.com.clickbus.placesmanager.controller.exception.PlaceManagerResponseException;
import br.com.clickbus.placesmanager.resource.PlaceResource;
import br.com.clickbus.placesmanager.usecase.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static java.util.Optional.ofNullable;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("v1/places")
public class PlacesManagerController {

    private final PlaceResourceRequestToPlaceConverter placeResourceRequestToPlaceConverter;
    private final PlaceToPlaceResourceResponseConverter placeToPlaceResourceResponseConverter;
    private final PlaceResourceRequestCustomToPlaceConverter placeResourceRequestCustomToPlaceConverter;
    private final SavePlaceUseCase savePlaceUseCase;
    private final UpdatePlaceUseCase updatePlaceUseCase;
    private final ListAllPlaceUseCase listAllPlaceUseCase;
    private final GetByIdPlaceUsecase getByIdPlaceUsecase;
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


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PlaceResource update(@RequestBody final PlaceResource request, @PathVariable String id) {
        log.info("[PLACE-MANAGER][PUT][REQUEST] {}", request);
        final var response = ofNullable(request)
                .map(placeRequested -> placeResourceRequestCustomToPlaceConverter.convert(placeRequested, id))
                .map(updatePlaceUseCase::execute)
                .map(placeToPlaceResourceResponseConverter::convert)
                .orElseThrow(() -> new PlaceManagerResponseException("[PLACE-MANAGER][PUT][RESPONSE]: Fail to update Place of id" + request.getId() + " in the base."));
        return response;
    }


    @GetMapping
    public Page<PlaceResource> listAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        log.info("[PLACE-MANAGER][LIST-ALL][REQUEST]: page: {} size {}", page, size);
        return listAllPlaceUseCase.execute(page, size)
                .map(placeToPlaceResourceResponseConverter::convert);
    }

    @GetMapping("/{id}")
    public PlaceResource getById(@PathVariable final String id) {
        log.info("[PLACE-MANAGER][GET-BY-ID][REQUEST] {} ", id);
        return ofNullable(id)
                .flatMap(getByIdPlaceUsecase::execute)
                .map(placeToPlaceResourceResponseConverter::convert)
                .orElseThrow(() -> new PlaceManagerResponseException("[PLACE-MANAGER][GET-BY-ID][RESPONSE]: Fail to find Place of id" + id + " in the base."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String id) {
        log.info("[PLACE-MANAGER][DELETE][REQUEST] {}", id);
        deletePlaceUseCase.execute(id);
    }
}
