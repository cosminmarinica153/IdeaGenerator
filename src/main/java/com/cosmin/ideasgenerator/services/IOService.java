package com.cosmin.ideasgenerator.services;

import com.cosmin.ideasgenerator.models.ApiData;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@Service
public class IOService {
    private final int API_LIST_SIZE = 731;
    private final int PICK_COUNT = 5;

    public ApiData[] readApiList() throws IOException {
        String filePath = "src/main/resources/apis_list.txt";
        ApiData[] apiList = new ApiData[API_LIST_SIZE];

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String lineStr;
            for (int i = 0; i < API_LIST_SIZE; i++) {
                lineStr = br.readLine();

                if(lineStr == null) {
                    System.out.println("Line " + i + " is empty");
                    break;
                };

                String[] line = lineStr.split("___");

                if (line.length >= 3){
                    ApiData data = new ApiData(line[0], line[1], line[2]);
                    apiList[i] = data;
                } else
                    System.out.println("Line " + i + " is not formatted correctly");

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading API list");
        }

        return apiList;
    }

    public ApiData[] pickApiList() {
        ApiData[] pickedApi = new ApiData[PICK_COUNT];
        ApiData[] apiArr;
        List<Integer> chosenIndexes = new ArrayList<>();
        Random rand = new Random();

        try {
            apiArr = readApiList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < PICK_COUNT; i++) {
            int tempVal = rand.nextInt(API_LIST_SIZE);
            if (chosenIndexes.contains(tempVal)) {
                i--;
                continue;
            }

            chosenIndexes.add(tempVal);
            pickedApi[i] = apiArr[tempVal];
        }

        return pickedApi;
    }
}
