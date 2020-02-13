import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Date;

class AppCardDeliveryTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement testContent = $("form");
        testContent.$("input.input__control[placeholder=Город]").setValue("Кемерово");
        testContent.$("input.input__control[type=tel]").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        testContent.$("input.input__control[type=tel]").sendKeys(testDateGenerator("ddMMyyyy"));
        testContent.$("input.input__control[name=name]").setValue("Ярослав Мжельских");
        testContent.$("input.input__control[name=phone]").setValue("+01234567890");
        testContent.$("label.checkbox").click();
        testContent.$("button.button").click();
        $(withText("Успешно")).waitUntil(Condition.appears, 11000);
    }

    String testDateGenerator(String dataPattern){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(dataPattern);
        long dateInMillis = date.getTime() + 259200000L;
        date.setTime(dateInMillis);
        return format.format(date);
    }
}
