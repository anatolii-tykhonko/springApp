package com.anatolii.springDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class RandomFortuneService implements FortuneService {

    private List<String> randomFortuneWithFile;
    @Value("${textFile.filePatch}")
    private String filePath;

    @PostConstruct
    private void initRandomFortuneWithFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            randomFortuneWithFile = new ArrayList<>();
            while (reader.ready()){
                randomFortuneWithFile.add(reader.readLine());
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getFortune() {
        Random random = new Random();
        int index = random.nextInt(randomFortuneWithFile.size());
        return randomFortuneWithFile.get(index);
    }
}
