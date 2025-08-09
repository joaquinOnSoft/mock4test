package com.openmock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "name",
        "amenities",
        "seats"
})
public class Aircraft {
    @JsonProperty("type")
    private AircraftType type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("amenities")
    private List<Amenity>amenities = new LinkedList<>();
    @JsonProperty("seats")
    private List<Seat>seats = new LinkedList<>();


    public void addAmenity(Amenity amenity){
        amenities.add(amenity);
    }

    @JsonProperty("amenities")
    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public void addSeat(Seat seat){
        seats.add(seat);
    }

    public void addSeat(String seatClass, int numSeats){
        seats.add(new Seat(seatClass, numSeats));
    }

}
