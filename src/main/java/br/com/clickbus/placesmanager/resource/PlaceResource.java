package br.com.clickbus.placesmanager.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResource {
    private String id;
    private String name;
    private String slug;
    private String city;
    private String state;

}
