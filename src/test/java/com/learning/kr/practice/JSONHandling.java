package com.learning.kr.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JSONHandling {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Students students = new Students();
        students.setName("Kalyan");
        students.setEmail("kalyan@gmail.com");
        students.setAddress("Dattapulia Nadia");

        String jsonString = mapper.writeValueAsString(students);
        System.out.println(jsonString);

        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);
        System.out.println(prettyJson);

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("students.json"), students);


        Map<String, Object> studentsMap = mapper.readValue(new File(
                "students.json"), new TypeReference<Map<String, Object>>() {
        });

        System.out.println(studentsMap);
        System.out.println(studentsMap.get("name"));

    }

}
