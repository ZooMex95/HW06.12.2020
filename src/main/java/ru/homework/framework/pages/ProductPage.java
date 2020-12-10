package ru.homework.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.homework.framework.product.Product;

import java.util.List;

public class ProductPage extends BasePage {


    @FindBy(xpath = "//div[@class='product-card-price__current-wrap']/span[contains(i,'₽')]")
    private WebElement priceOfProduct;

    public ProductPage rememberPrice() {
        waitUntilElementToBeVisible(priceOfProduct);
        new Product(getNameOfProduct(),
                stringPriceToInt(priceOfProduct), false);
        return this;
    }

    @FindBy(xpath = "//div[@class='product-warranty__head']/../select/option")
    private List<WebElement> selectionWarranty;

    public ProductPage selectWarranty(String warranty) {
        for (WebElement element: selectionWarranty
             ) {
            if (element.getText().equals("2 года")) {
                element.click();
            }
        }
        return this;
    }

    @FindBy(xpath = "//div[@class='product-card-price__current-wrap']/span[contains(i,'₽')]")
    private WebElement newPriceOfProduct;

    public ProductPage rememberNewPrice() {
        waitChangePriceWithWarranty(newPriceOfProduct);
        new Product(getNameOfProduct() + " with warranty",
                + stringPriceToInt(newPriceOfProduct), true);
        return this;
    }

    @FindBy(xpath = "//div[@class='primary-btn']/button")
    private WebElement buyButton;

    public ProductPage clickBuyButton() {
        buyButton.click();
        return this;
    }


    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    private WebElement search;

    public ProductPage searchFromProductPage(String name) {
        nameOfProduct = name;
        search.click();
        search.sendKeys(nameOfProduct + "\n");
        return this;
    }

    @FindBy(xpath = "//span[@class='cart-link__lbl']")
    private WebElement checkCartPrice;

    public ProductPage checkCart() {
        waitChangeText(checkCartPrice);
        int sumOfProducts = Product.listOfProducts.get(1).getPrice() + Product.listOfProducts.get(2).getPrice();
        Assert.assertEquals("Цена корзины не совпадает с суммой покупок", stringPriceToInt(checkCartPrice), sumOfProducts);
        return this;
    }

    @FindBy(xpath = "//a[@class='ui-link cart-link']")
    private WebElement clickCart;

    public CartPage clickCartButton() {
        clickCart.click();
        return app.getCartPage();
    }

}
