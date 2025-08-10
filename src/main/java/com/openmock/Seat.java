package com.openmock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "seatClass",
        "numSeats"
})
@Getter
@Setter
@AllArgsConstructor
public class Seat {
    @JsonProperty("seatClass")
    private String seatClass;
    @JsonProperty("numSeats")
    private int numSeats;
}
