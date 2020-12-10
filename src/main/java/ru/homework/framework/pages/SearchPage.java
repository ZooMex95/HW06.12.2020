package ru.homework.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//div[@class='product-info__title']/div/a")
    private List<WebElement> listOfProductAtSearch;

    public ProductPage clickProduct() {
        for (WebElement element : listOfProductAtSearch) {
            if (element.getText().toLowerCase().contains(getNameOfProduct().toLowerCase())) {
                element.click();
                break;
            }
        }
        return app.getProductPage();
    }

}
