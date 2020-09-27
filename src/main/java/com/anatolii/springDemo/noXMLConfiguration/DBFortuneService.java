package com.anatolii.springDemo.noXMLConfiguration;

import com.anatolii.springDemo.FortuneService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBFortuneService implements FortuneService {
    @Value("${textFileFortuneStatus.filePatch}")
    private String filePatchForFortuneStatus;
    private List<String> allFortuneStatus;

    @PostConstruct
    private void initialize() {
        try (BufferedReader readerFortune = new BufferedReader(new FileReader(filePatchForFortuneStatus))) {
            allFortuneStatus = new ArrayList<>();
            while (readerFortune.ready()) {
                allFortuneStatus.add(readerFortune.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getFortune() {
        Random randomIndex = new Random();
        int indexFortuneStatus = randomIndex.nextInt(allFortuneStatus.size());
        return allFortuneStatus.get(indexFortuneStatus);
    }
}
