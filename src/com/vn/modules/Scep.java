package com.vn.modules;

import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scep extends Request {
    static void main(String[] args) throws IOException, InterruptedException {
        Scep search = new Scep();
        Scanner sc = new Scanner(System.in);
        ArrayList<JsonObject> addresses = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (addresses.isEmpty()) {
                    System.out.println("No addresses to save!");
                    return;
                }
                System.out.println("Running Shutdown Hook");
                try (FileWriter writer = new FileWriter("Ceps.json")) {
                    writer.write(gson.toJson(addresses));
                    System.out.println("File updated!");
                } catch (IOException e) {
                    System.out.println("Error to save file: " + e.getMessage());
                }
            }
        });

        while(true) {
            System.out.println("""
                    **** Ceps Consultation ****
                    Enter your cep:
                    """);
            String cep = sc.nextLine().replace("-", "");

            if (cep.equalsIgnoreCase("Close")){
                if (addresses.isEmpty()) {
                    System.out.println("The field cannot be empty! try again: ");
                    continue;
                }
                break;
            }
            if (cep.equalsIgnoreCase("list")) {
                if (addresses.isEmpty()) {
                    System.out.println("No addresses consulted yet!");
                } else {
                    addresses.forEach(a -> System.out.println(
                            a.get("cep").getAsString() + " - " + a.get("logradouro").getAsString()
                    ));
                }
                continue;
            }
            if (!cep.matches("\\d{8}")) {
                System.out.println("Invalidated Cep! Enter exactly 8 numeric digits. ");
                continue;
            }
            try {
                String address = search.getJson("https://viacep.com.br/ws/" + cep + "/json/");
                JsonObject json = JsonParser.parseString(address).getAsJsonObject();
                if (json.has("erro")) {
                    System.out.println("CEP not found! Enter again: ");
                    continue;
                }
                System.out.println(address);
                boolean duplicated = addresses.stream()
                        .anyMatch(a -> a.get("cep").getAsString().equals(json.get("cep").getAsString()));
                if (duplicated) {
                    System.out.println("CEP already registered!");
                    continue;
                }
                addresses.add(json);
            } catch (Exception e) {
                System.out.println("Connection error! Try again: ");
            }
        }
        sc.close();
    }
}
