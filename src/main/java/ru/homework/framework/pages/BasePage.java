package ru.homework.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.homework.framework.managers.ManagerPages;
import ru.homework.framework.product.Product;

import static ru.homework.framework.managers.DriverManager.getDriver;

public class BasePage {

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    protected ManagerPages app = ManagerPages.getManagerPages();

    public static String nameOfProduct;

    public StartPage setNameOfProduct(String nameOfProduct) {
        BasePage.nameOfProduct = nameOfProduct;
        return app.getStartPage();
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }


    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public int stringPriceToInt(WebElement element) {
        String price = element.getText();
        price = price.replaceAll("[^0-9]","");
        return Integer.parseInt(price);
    }

    public void waitChangePriceWithWarranty(WebElement element) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element,
                String.valueOf(Product.listOfProducts.get(0).getPrice()))));
    }

    public void waitChangeText(WebElement element) {
        String str = element.getText();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, str)));
    }

    public void waitUntilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
