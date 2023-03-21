package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardDeliveryTest {

    private String returnDateAddDays(int count) {
        String date;
        date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDate.now().plusDays(count));
        return date;


    }

    @BeforeEach
    void createUrl() {
        open("http://localhost:9999/");

    }

    @Test
    void orderingCardCorrectDataTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
    }

    @Test
    void orderingCardCorrectDataOnlyNameTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
    }

    @Test
    void orderingCardCorrectDataNameWithDashTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов-Петров Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
    }

    @Test
    void orderingCardInCorrectCityTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Майский");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='city'] .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", actual);
    }

    @Test
    void orderingCardEngCityTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Moscow");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='city'] .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", actual);
    }


    @Test
    void orderingCardNumberCityTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("123");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='city'] .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", actual);
    }


    @Test
    void orderingCardEmptyCityTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='city'] .input__sub").getText();

        assertEquals("Поле обязательно для заполнения", actual);
    }

    @Test
    void orderingCardDate4DaysTest() {
        String date = returnDateAddDays(4);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
    }

  /*  @Test
    void orderingCardEmptyDateTest() {



        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();


        String actual = $("[data-test-id='date'] .input__sub").getText();

       assertEquals("Неверно введена дата", actual);


    }
*/


    @Test
    void orderingCardEngNameTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Ivan");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='name'] .input__sub").getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual);
    }

    @Test
    void orderingCardNameWithSpecialCharacterTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("'");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='name'] .input__sub").getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual);
    }

    @Test
    void orderingCardEmptyNameTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='name'] .input__sub").getText();

        assertEquals("Поле обязательно для заполнения", actual);
    }

    @Test
    void orderingCardNameOnlySpacingTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys(" ");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='name'] .input__sub").getText();

        assertEquals("Поле обязательно для заполнения", actual);
    }


    @Test
    void orderingCardEmptyPhoneTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='phone'] .input__sub").getText();

        assertEquals("Поле обязательно для заполнения", actual);
    }

    @Test
    void orderingCardPhoneWithoutPlusTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("89181234578");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='phone'] .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual);
    }


    @Test
    void orderingCardPhoneShortTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+8918123457");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='phone'] .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual);
    }

    @Test
    void orderingCardPhoneLongTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+891811122334");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='phone'] .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual);
    }

    @Test
    void orderingCardPhone12DigitsWithoutPlusTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("891811122334");
        $(".checkbox_size_m").click();

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $("[data-test-id='phone'] .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual);
    }


    @Test
    void orderingCardNoClickTest() {
        String date = returnDateAddDays(3);

        $("[data-test-id='city'] input.input__control").sendKeys("Барнаул");

        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.BACK_SPACE, date));

        $("[data-test-id='name'] input.input__control").sendKeys("Иванов Иван");
        $("[data-test-id='phone'] input.input__control").sendKeys("+79101234567");

        $$("button").find(Condition.exactText("Забронировать")).click();

        String actual = $(".checkbox__text").getCssValue("color");

        assertEquals("rgba(255, 92, 92, 1)", actual);


    }


}
