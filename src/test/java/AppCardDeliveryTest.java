import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppCardDeliveryTest {

    @Test
    void shouldSubmitRequest()  throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement testContent = $("form");

        testContent.$("input.input__control[placeholder=Город]").setValue("Кемерово");

        while(!testContent.$("input.input__control[type=tel]").is(Condition.empty)) {
            testContent.$("input.input__control[type=tel]").sendKeys(Keys.BACK_SPACE);
        }
        Date date = new Date();
        long dateInMillis = date.getTime() + 259200000L;
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        date.setTime(dateInMillis);
        String testDate = format.format(date);
        testContent.$("input.input__control[type=tel]").sendKeys(testDate);

        testContent.$("input.input__control[name=name]").setValue("Ярослав Мжельских");

        testContent.$("input.input__control[name=phone]").setValue("+01234567890");

        testContent.$("label.checkbox").click();

        testContent.$("button.button").click();

        $(withText("Успешно")).waitUntil(Condition.appears, 11000);
    }
}
