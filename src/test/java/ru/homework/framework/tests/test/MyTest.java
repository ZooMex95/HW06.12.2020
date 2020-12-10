package ru.homework.framework.tests.test;


import org.junit.Test;

import ru.homework.framework.tests.base.BaseTest;


public class MyTest extends BaseTest {
    @Test
    public void test() {

        app.getStartPage()
                .setNameOfProduct("playstation")
                .searchAtStartPage()
                .clickProduct()
                .rememberPrice()
                .selectWarranty("2 года")
                .rememberNewPrice()
                .clickBuyButton()
                .searchFromProductPage("Detroit")
                .rememberPrice()
                .clickBuyButton()
                .checkCart()
                .clickCartButton()
                .checkWarranty("playstation", "24")
                .checkProductsPriceAndPriceOfCart()
                .deleteProductFromCart("Detroit")
                .checkIsProductInCart("Detroit")
                .addQuantityOfProduct(2,"playstation")
                .returnDeletedProduct("detroit");


    }
}
