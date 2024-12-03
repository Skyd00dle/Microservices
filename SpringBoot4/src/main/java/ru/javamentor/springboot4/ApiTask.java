package ru.javamentor.springboot4;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ApiTask {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users";


        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
                null, String.class);
        String sessionId = response.getHeaders().get("set-cookie").get(0);
        System.out.println("SessionID: " + sessionId);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, sessionId);
        headers.setContentType(MediaType.APPLICATION_JSON);


        User user = new User(3L, "James", "Brown", (byte) 25);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> createResponse = restTemplate.exchange(url, HttpMethod.POST,
                entity, String.class);
        String codePart1 = createResponse.getBody();
        System.out.println("Code Part 1: " + codePart1);


        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> entity2 = new HttpEntity<>(user, headers);
        ResponseEntity<String> updateResponse = restTemplate.exchange(url, HttpMethod.PUT,
                entity2, String.class);
        String codePart2 = updateResponse.getBody();
        System.out.println("Code Part 2: " + codePart2);


        ResponseEntity<String> deleteResponse = restTemplate.exchange(url + "/3", HttpMethod.DELETE,
                entity2, String.class);
        String codePart3 = deleteResponse.getBody();
        System.out.println("Code Part 3: " + codePart3);

        String fullCode = codePart1 + codePart2 + codePart3;
        System.out.println("Full code = " + fullCode);

    }
}
