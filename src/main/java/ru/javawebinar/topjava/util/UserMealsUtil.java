package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Вспомгательный метод для определения превышения калорий (getFilteredWithExceeded) по списку
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredWithExceeded.forEach(System.out::println); // построчно выводим значения списков

//        .toLocalDate(); // преобразует LocalDate в Date (делать сумму по калориям в течении одного дня)
//        .toLocalTime(); // преобразует LocalDate в Time (для фильтра по времени)
    }

    //  Преобразования списка рациона пользователя в список с целевой меткой - превышения/непревышения по калориям, согласно заданному временному диапозону
    // (Метод который нужно реализовать в ДЗ-0)
    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // (число калорий для каждой даты) группируем список UserMeal по датам и суммируем калории
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        // Изменение 1

        return mealList.stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime)) // фильтр по диапозону времени
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), // map преобразует каждый элемент коллекции (в данному случае создаём для каждого элемента элемент UserMealWithExceed)
                        caloriesSumByDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay)) // последний аргумент UserMealWithExceed - превышение калорий в день
                .collect(Collectors.toList()); // преобразуем stream в List
    }
}
