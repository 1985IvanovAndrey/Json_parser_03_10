package com.example.test_json.controller.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ParserDto {

    @JsonProperty("rcc")
    private List<Rcc> rcc;

    @Data
    public static class Sofit {
        @JsonProperty("Status")
        private String Status;
        @JsonProperty("ua_name")
        private String ua_name;
        @JsonProperty("fio_en")
        private String fio_en;
        @JsonProperty("birthday")
        private String birthday;
        @JsonProperty("Sex")
        private String Sex;
        @JsonProperty("Lang")
        private String Lang;
        @JsonProperty("Ser")
        private String Ser;
        @JsonProperty("Num")
        private String Num;
        @JsonProperty("Type")
        private String Type;
        @JsonProperty("date")
        private String date;
        @JsonProperty("Country")
        private String Country;
        @JsonProperty("Place")
        private String Place;
    }

    @Data
    public static class Rcc {
        @JsonProperty("comment")
        private String comment;
        @JsonProperty("type_operation")
        private String type_operation;
        @JsonProperty("token")
        private String token;
        @JsonProperty("ovk")
        private List<String> ovk;
        @JsonProperty("sofit")
        private Sofit sofit;
    }
}
