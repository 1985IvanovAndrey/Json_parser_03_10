package com.example.test_json.service;

import com.example.test_json.controller.Dto.ParserDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.ir.RuntimeNode;
import lombok.extern.log4j.Log4j2;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.misc.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ParserJsonServoce {

    private static final String JSON_URL = "http://localhost:8081/get";

    public void getFieldFromJson1(String json) throws IOException {
        log.info(json);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(json);
        ArrayNode rcc = (ArrayNode) node.get("rcc");
        for (JsonNode jsonNode : rcc) {
            String country = jsonNode.get("sofit").get("Country").toString();
            System.out.println("sofit->Country->" + country);
            String birthday = jsonNode.get("sofit").get("birthday").toString();
            System.out.println("sofit->birthday->" + birthday);
            String type = jsonNode.get("sofit").get("Type").toString();
            System.out.println("sofit->type->" + type);
            String comment = jsonNode.get("comment").toString();
            System.out.println("comment->" + comment);
            ArrayNode ovk = (ArrayNode) jsonNode.get("ovk");
            for (JsonNode node1 : ovk) {
                System.out.println("ovk->" + node1);
            }
        }
    }

    public void getFieldFromJson2(String json) {
        log.info(json);
        JsonElement jsonElement = new JsonParser().parse(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("rcc");
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject1 = element.getAsJsonObject();
            String country = jsonObject1.getAsJsonObject().get("sofit").getAsJsonObject().get("Country").getAsString();
            System.out.println("sofit->Country->" + country);
            String place = jsonObject1.getAsJsonObject().get("sofit").getAsJsonObject().get("Place").getAsString();
            System.out.println("sofit->Place->" + place);
            String comment = jsonObject1.getAsJsonObject().get("comment").getAsString();
            System.out.println("comment->" + comment);
            String typeOperation = jsonObject1.getAsJsonObject().get("type_operation").getAsString();
            System.out.println("type_operation->" + typeOperation);
            JsonArray ovk = jsonObject1.getAsJsonArray("ovk");
            for (JsonElement jsonElement1 : ovk) {
                System.out.println("ovk->" + jsonElement1);
            }
        }
    }

    public void getFieldFromJson3(ParserDto parserDto) {
        try {
            List<ParserDto.Rcc> rccList = parserDto.getRcc();
            for (ParserDto.Rcc rcc : rccList) {
                System.out.println("sofit->Country->" + rcc.getSofit().getCountry());
                System.out.println("sofit->Date->" + rcc.getSofit().getDate());
                System.out.println("sofit->Place->" + rcc.getSofit().getPlace());
                System.out.println("sofit->comment->" + rcc.getComment());
                System.out.println("sofit->token->" + rcc.getToken());

                for (String s : rcc.getOvk()) {
                    System.out.println("ovk->" + s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getFieldFromJson4(String json) throws IOException {
        String json1 = "{ \"f1\":\"Hello\",\"f2\":{\"f3:\":\"World\"}}";
        JsonElement jsonElement=new JsonParser().parse(json1);
        JsonObject jsonObject=jsonElement.getAsJsonObject();
        System.out.println(jsonObject.get("f2").getAsJsonObject().get("f3:"));

    }
}



