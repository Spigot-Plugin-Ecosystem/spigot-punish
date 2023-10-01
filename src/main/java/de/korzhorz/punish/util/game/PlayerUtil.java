package de.korzhorz.punish.util.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.URL;

public class PlayerUtil {
    public static String getUuid(String playerName) throws IllegalArgumentException, IOException {
        String baseApiURL = "https://api.mojang.com/users/profiles/minecraft/";
        String url = baseApiURL + playerName;

        try {
            try(InputStream inputStream = new URL(url).openStream()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                int cp;
                while((cp = bufferedReader.read()) != -1) {
                    stringBuilder.append((char) cp);
                }
                String json = stringBuilder.toString();
                JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

                if(!(jsonObject.has("id") && jsonObject.has("name"))) {
                    throw new IllegalArgumentException("Invalid player name");
                }

                return jsonObject.get("id").getAsString();
            } catch(FileNotFoundException e) {
                e.printStackTrace();
                throw new IOException("Cannot access Mojang API");
            }
        } catch(IOException e) {
            e.printStackTrace();
            throw new IOException("Cannot access Mojang API");
        }
    }
}
