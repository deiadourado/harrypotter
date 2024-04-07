package com.example.Util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.Model.HPCharacter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HarryApiManager {

    // URL of the Harry Potter API
    private static final String API_URL = "https://hp-api.onrender.com/api";

    // HTTP client for sending requests
    private final HttpClient client;

    // Constructor initializes the HTTP client
    public HarryApiManager() {
        this.client = HttpClient.newHttpClient();
    }

    // Method to fetch all characters from the API
    public List<HPCharacter> getCharacters() {
        try {
            String urlString = API_URL + "/characters";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlString)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // If response status code is 200 (OK), parse JSON response and return list of characters
            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                TypeToken<List<HPCharacter>> typeToken = new TypeToken<>() {};
                return gson.fromJson(response.body(), typeToken.getType());
            } else {
                // Handle non-200 response by returning an empty list
                return Collections.emptyList();
            }

        } catch (Exception e) {
            // Log or handle the exception, return an empty list
            return Collections.emptyList();
        }
    }

    // Method to fetch a character by ID from the API
    public Optional<HPCharacter> getCharacterById(String id) {
        try {
            String urlString = API_URL + "/character/" + id;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlString)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // If response status code is 200 (OK), parse JSON response and return the character
            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                HPCharacter[] charactersArray = gson.fromJson(response.body(), HPCharacter[].class);
                return charactersArray.length > 0 ? Optional.of(charactersArray[0]) : Optional.empty();
            } else {
                // Handle non-200 response by returning an empty Optional
                return Optional.empty();
            }

        } catch (Exception e) {
            // Log or handle the exception, return an empty Optional
            return Optional.empty();
        }
    }
}
