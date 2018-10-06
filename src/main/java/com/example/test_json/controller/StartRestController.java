package com.example.test_json.controller;

import com.example.test_json.controller.Dto.ParserDto;
import com.example.test_json.service.ParserJsonServoce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/get")
public class StartRestController {

    @Autowired
    private ParserJsonServoce parserJsonServoce;

    @PostMapping("/one")// парсит json1
    public String method1(@RequestBody String json) throws IOException {
        parserJsonServoce.getFieldFromJson1(json);
        return "ok";
    }
    @PostMapping("/two")// парсит json1
    public String method2(@RequestBody String json) throws IOException {
        parserJsonServoce.getFieldFromJson2(json);
        return "ok";
    }
    @PostMapping("/three")// парсит json1
    public String method3(@RequestBody ParserDto parserDto) throws IOException {
       try{
           parserJsonServoce.getFieldFromJson3(parserDto);
       }catch (Exception e){
           e.printStackTrace();
       }
        return "ok";
    }
    @GetMapping ("/four")// парсит статическую строку
    public String method4() throws IOException {
        parserJsonServoce.getFieldFromJson4();
        return "ok";
    }
    @PostMapping("/five")// парсит json2
    public String method5(@RequestBody String json)throws IOException{
        parserJsonServoce.getFieldFromJson5(json);
        return "ok";

    }
    @GetMapping("/json")// создаёт json
    public Object gsontTestGet() {
        return parserJsonServoce.createJson();
    }
}
