package ru.homework.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    private WebElement search;

    public SearchPage searchAtStartPage() {
        search.click();
        search.sendKeys(getNameOfProduct() + "\n");
        return app.getSearchPage();
    }

}
