package ru.homework.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.homework.framework.product.Product;
import org.junit.Assert;


import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{
    public static List<Product> listOfProductsInCart = new ArrayList<>();

    @FindBy(xpath = "//div[@class='cart-items__product']")
    private List<WebElement> list1;

    @FindBy(xpath = "//div[@class='cart-items__product-name']/a")
    private WebElement productName;

    @FindBy(xpath = "")
    private WebElement productPrice;

    @FindBy(xpath = "")
    private WebElement productWarranty;

    public String getProductName(WebElement element) {
        return element.findElement(By.xpath("//div[@class='cart-items__product-name']/a")).getText();
    }

    public int getProductPrice(WebElement element) {
        return stringPriceToInt(element.findElement(By.xpath("//span[@class='price__current']")));

    }

    public String getProductWarranty(WebElement element) {
        return element.findElement(By.xpath("//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']")).getText();

    }

    public CartPage checkWarranty(String nameOfProduct, String monthsOfWarranty) {
        for (WebElement element: list1
             ) {
            if (getProductName(element).toLowerCase().contains(nameOfProduct.toLowerCase())) {
                Assert.assertTrue("Гарантия на " + monthsOfWarranty + " месяца для товара с именем " +
                        nameOfProduct + "не выбрана", getProductWarranty(element).contains(monthsOfWarranty));
            }
        }
        return this;
    }

    @FindBy(xpath = "//div[@class='total-amount']//span[@class='price__current']")
    private WebElement cartPrice;
    public CartPage checkProductsPriceAndPriceOfCart() {
        int sum = 0;
        for (WebElement element: list1                          //добавляется 4 плойки
             ) {                                                //исправить условие if или поменять местами циклы
            for (Product product: Product.listOfProducts
                 ) {
                if (getProductName(element).toLowerCase().contains(product.getName().toLowerCase()))
                    Assert.assertEquals("Цена товара с названием " + getProductName(element) +
                            "не совпадает с сохраненной раннее", product.getPrice(), getProductPrice(element));
                    sum +=getProductPrice(element);
            }

        }
        Assert.assertEquals("Цена сохраненных товаров не совпадает с суммой в корзине", sum, stringPriceToInt(cartPrice));
        return this;
    }
}
