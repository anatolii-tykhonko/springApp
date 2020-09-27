package com.anatolii.springDemo.noXMLConfiguration;

import com.anatolii.springDemo.Coach;
import com.anatolii.springDemo.FortuneService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwimCoach implements Coach {

    private FortuneService fortuneService;
    @Value("${textFileDailyWorkout.filePatch}")
    private String filePatchForDailyWorkout;
    private List<String> allDailyWorkoutTasks;

    public SwimCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @PostConstruct
    private void initialize() {
        try (BufferedReader readerWorkout = new BufferedReader(new FileReader(filePatchForDailyWorkout))) {
            allDailyWorkoutTasks = new ArrayList<>();
            while (readerWorkout.ready()) {
                allDailyWorkoutTasks.add(readerWorkout.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getDailyWorkout() {
        Random randomIndex = new Random();
        int indexDailyWorkoutTasks = randomIndex.nextInt(allDailyWorkoutTasks.size());
        return allDailyWorkoutTasks.get(indexDailyWorkoutTasks);
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
