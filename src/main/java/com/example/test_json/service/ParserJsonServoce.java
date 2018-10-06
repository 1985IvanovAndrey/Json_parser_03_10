package com.example.test_json.service;

import com.example.test_json.controller.Dto.ParserDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sun.javafx.collections.MappingChange;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Log4j2
public class ParserJsonServoce {

    @Autowired
    private Gson gson;

    //private static final String JSON_URL = "http://localhost:8081/get";

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

    public void getFieldFromJson4() throws IOException {
        String json1 = "{ \"f1\":\"Hello\",\"f2\":{\"f3:\":\"World\"}}";
        JsonElement jsonElement = new JsonParser().parse(json1);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        System.out.println(jsonObject.get("f2").getAsJsonObject().get("f3:").getAsString());

    }

    public void getFieldFromJson5(String json) throws IOException {
        log.info(json);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(json);
        String one = node.get("address").get("friend_addr").get("street").asText();
        System.out.println("address->friend_addr->street=" + one);

        List<Integer> codesLiist = new ArrayList<>();
        ArrayNode codes = (ArrayNode) node.get("address").get("friend_addr").get("codes");
        for (JsonNode code : codes) {
            codesLiist.add(code.asInt());
        }
        System.out.println("First collection->" + codesLiist);

        List<Integer> phoneNumbersList = new ArrayList<>();
        ArrayNode phoneNumbers = (ArrayNode) node.get("phoneNumbers");
        for (JsonNode phoneNumber : phoneNumbers) {
            phoneNumbersList.add(phoneNumber.asInt());
        }
        System.out.println("Second collection->" + phoneNumbersList);

        List<String> citiesList = new ArrayList<>();
        ArrayNode cities = (ArrayNode) node.get("cities");
        for (JsonNode city : cities) {
            citiesList.add(city.asText());
        }
        System.out.println("Third collection->" + citiesList);
    }

    public Object createJson() {
        //1.Структуры данных
        Map<String, String> samsungMap = new HashMap<>();
        samsungMap.put("Country", "Korea");
        samsungMap.put("Model", "Galaxy S7");
        samsungMap.put("Quality", "Good");

        Map<String, String> appleMap = new HashMap<>();
        appleMap.put("City", "California");
        appleMap.put("TopModel", "Iphone Xs");
        appleMap.put("FeedBack", "The best");


        Map<String, String> sofitMap = new HashMap<>();
        sofitMap.put("Place", "Соборным МВД УВД");
        sofitMap.put("Date", "2009-08-02");
        sofitMap.put("Fio_en", "dianorev");

        List<String> stringList = new ArrayList<>(Arrays.asList("id", "tittle"));
        List<String> carList = new ArrayList<>(Arrays.asList("BMW", "Mercedes", "Porsche"));
        List<String>caseList=new ArrayList<>(Arrays.asList("White","Black","Green","Yellow"));

        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("position", "first");


        //  2. Генерируем джейсон
        JsonObject parentObject = new JsonObject();
        JsonArray jsonArrayRcc = new JsonArray();
        JsonObject firstObject = new JsonObject();
        JsonObject secondObject = new JsonObject();
        JsonObject thirdObject = new JsonObject();
        JsonObject jsonObject = new JsonObject();
        JsonObject statusObject = new JsonObject();
        JsonObject samsungObject = new JsonObject();
        JsonObject appleObject = new JsonObject();

        for (Map.Entry<String, String> entry : appleMap.entrySet()) {
            appleObject.addProperty(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, String> entry : samsungMap.entrySet()) {
            samsungObject.addProperty(entry.getKey(), entry.getValue());
        }

        JsonElement stringListJsonArray = gson.toJsonTree(stringList, new TypeToken<List<String>>() {
        }.getType());
        JsonElement carListJsonArray = gson.toJsonTree(carList, new TypeToken<List<String>>() {
        }.getType());
        JsonElement caselistJsonArray=gson.toJsonTree(caseList,new TypeToken<List<String>>() {
        }.getType());

        for (Map.Entry<String, String> entry : sofitMap.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : statusMap.entrySet()) {
            statusObject.addProperty(entry.getKey(), entry.getValue());
        }

        firstObject.add("status", statusObject);
        firstObject.add("sofit", jsonObject);
        firstObject.add("ovk", stringListJsonArray);
        firstObject.addProperty("type_operation", "human_add");
        firstObject.addProperty("comment", "add human person from space");
        secondObject.add("samsung", samsungObject);
        secondObject.add("car", carListJsonArray);
        secondObject.addProperty("form", "square");
        secondObject.addProperty("warning", "hot");
        thirdObject.add("apple",appleObject);
        thirdObject.add("case",caselistJsonArray);
        thirdObject.addProperty("condition","new");
        thirdObject.addProperty("size","normal");

        jsonArrayRcc.add(firstObject);
        jsonArrayRcc.add(secondObject);
        jsonArrayRcc.add(thirdObject);

        parentObject.add("rcc", jsonArrayRcc);

        return parentObject;
    }

}



