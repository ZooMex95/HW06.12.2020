package ru.homework.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.homework.framework.product.Product;
import org.junit.Assert;

import java.util.List;

public class CartPage extends BasePage{

    @FindBy(xpath = "//div[@class='cart-items__product-caption']")
    private List<WebElement> listOfProductsInCart;

    @FindBy(xpath = "//div[@class='total-amount']//span[@class='price__current']")
    private WebElement cartPrice;

    @FindBy(xpath = "//span/span[normalize-space()='Вернуть удалённый товар']")
    private WebElement returnDeletedProductButton;


    public CartPage checkWarranty(String nameOfProduct, String monthsOfWarranty) {
        Assert.assertTrue("Гарантия на " + monthsOfWarranty + " месяца для товара с именем " +
                nameOfProduct + "не выбрана", getProductWarranty(findElementInListByName(nameOfProduct)).contains(monthsOfWarranty));
        return this;
    }

    public CartPage checkProductsPriceAndPriceOfCart() {
        int sum = 0;
        for (Product product: Product.listOfProducts
             ) {
            WebElement currentElement = findElementInListByName(product.getName());
            if (currentElement != null){
                Assert.assertEquals("Цена товара с названием " + getProductName(currentElement) +
                    " не совпадает с сохраненной раннее", product.getPrice(), getProductPrice(currentElement));
                sum += getProductPrice(currentElement);
            }
        }

        Assert.assertEquals("Цена товаров не совпадает с суммой в корзине", sum, stringPriceToInt(cartPrice));
        return this;
    }

    public CartPage deleteProductFromCart(String nameOfProduct) {
        WebElement deleteProductButton = findElementInListByName(nameOfProduct);
        deleteProductButton.findElement(By.xpath("./div/div/button[text()='Удалить']")).click();
        return this;
    }

    public CartPage checkIsProductInCart(String nameOfProduct) {
        int oldCartPrice = stringPriceToInt(cartPrice);
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//div[@class='cart-items__product-caption']"), listOfProductsInCart.size()));

        Assert.assertNull(nameOfProduct + " не удалился", findElementInListByName(nameOfProduct));
        Assert.assertEquals("Цена корзины после удаления " + nameOfProduct + " не верна",
                oldCartPrice - priceOfProductFromListByName(nameOfProduct), stringPriceToInt(cartPrice));
        return this;
    }

    public CartPage addQuantityOfProduct(int quantity, String nameOfProduct) {
        WebElement addQuantityButton = findElementInListByName(nameOfProduct.toLowerCase())
                .findElement(By.xpath("./../div/div/div/button[@data-commerce-action='CART_ADD']"));
        for (int i = 0; i < quantity; i++) {
            addQuantityButton.click();
            waitChangeText(cartPrice);
        }

        Assert.assertEquals("Сумма корзины не совпадает с " + nameOfProduct + " в количестве: " + quantity,
                priceOfProductFromListByName(nameOfProduct) * (quantity + 1), stringPriceToInt(cartPrice));
        return this;
    }

    public CartPage returnDeletedProduct(String nameOfDeletedProduct) {
        int priceOfCartBeforeReturn = stringPriceToInt(cartPrice);
        int countOfProducts = listOfProductsInCart.size();
        returnDeletedProductButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='cart-items__product-caption']"), countOfProducts));

        Assert.assertEquals("Сумма корзины не увеличилась на стоимость возвращенного товара",
                priceOfCartBeforeReturn + priceOfProductFromListByName(nameOfDeletedProduct), stringPriceToInt(cartPrice));
        Assert.assertNotNull("Удаленный товар не вернулся", findElementInListByName(nameOfDeletedProduct.toLowerCase()));
        return this;
    }


    private String getProductName(WebElement element) {
        return element.findElement(By.xpath("./div[@class='cart-items__product-name']/a")).getText().toLowerCase(); //*
    }

    private int getProductPrice(WebElement element) {
        return stringPriceToInt(element.findElement(By.xpath("./../div/div/div/div/span[@class='price__current']")));

    }

    private String getProductWarranty(WebElement element) {
        return element.findElement(By.xpath("//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']")).getText();

    }

    private WebElement findElementInListByName(String nameOfProduct){
        WebElement el = null;
        for (WebElement element: listOfProductsInCart
        ) {
            if (getProductName(element).contains(nameOfProduct.toLowerCase())) {
                el = element;
            }
        }
        return el;
    }

    private int priceOfProductFromListByName(String nameOfProduct) {
        int priceOfProduct = 0;
        for (Product product: Product.listOfProducts
        ) {
            if (product.getName().equals(nameOfProduct.toLowerCase())){
                priceOfProduct = product.getPrice();
            }
        }
        return priceOfProduct;
    }

}
