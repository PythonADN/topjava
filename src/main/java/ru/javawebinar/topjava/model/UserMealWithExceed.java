package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

// бин - Еда пользователя + целевая метка (превышение/непревышение по калориям в день)
public class UserMealWithExceed {
    private final LocalDateTime dateTime; // время
    private final String description; // описание
    private final int calories; // калории
    private final boolean exceed; // превышение лимита калорий (целевая метка)

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}
