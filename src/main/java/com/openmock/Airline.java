package com.openmock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/// An airline is a company that provides a regular service of air transportion for passengers or
/// freight (cargo). Airlines use aircraft to supply these services. Many passenger airlines also
/// carry cargo in the belly of their aircraft, while dedicated cargo airlines focus solely on
/// freight transport. Generally, airline companies are recognized with an air operating certificate
/// or license issued by a governmental aviation body. Airlines may be scheduled or charter operators.
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "code",
        "web",
        "lounge",
        "reservationsPhone",
        "frequentFlyerProgram",
        "alliance",
        "magazine",
        "aircrafts",
        "popularDestinations"
})
public class Airline {
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("web")
    private String web;
    @JsonProperty("lounge")
    private String lounge;
    @JsonProperty("reservationsPhone")
    private String reservationsPhone;
    @JsonProperty("frequentFlyerProgram")
    private String frequentFlyerProgram;
    @JsonProperty("alliance")
    private String alliance;
    @JsonProperty("magazine")
    private String magazine;
    @JsonProperty("aircrafts")
    private List<Aircraft> aircrafts = new LinkedList<>();
    @JsonProperty("popularDestinations")
    private List<String> popularDestinations = new LinkedList<>();

    public void addAircraft(Aircraft aircraft){
        aircrafts.add(aircraft);
    }

    public void addPopularDestination(String destination){
        popularDestinations.add(destination);
    }
}
