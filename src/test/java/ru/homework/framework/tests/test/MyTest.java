package ru.homework.framework.tests.test;


import org.junit.Test;


import ru.homework.framework.product.Product;
import ru.homework.framework.tests.base.BaseTest;


public class MyTest extends BaseTest {
    @Test
    public void test() throws InterruptedException {

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
                .checkProductsPriceAndPriceOfCart();
        Thread.sleep(3000);
        Product.listOfProducts.forEach(l->System.out.println(l.getName()));










       /* String firstPriceXPath = "//div[@class='product-card-price__current-wrap']/span[contains(i,'₽')]";
        WebElement ps4Price = driver.findElement(By.xpath(firstPriceXPath));
        wait.until(ExpectedConditions.visibilityOf(ps4Price));                      //DONE
        int firstPrice = Integer.parseInt(ps4Price.getText().replaceAll("[^0-9]", ""));
        System.out.println("Без гарантии" + firstPrice);

        String warrantyXPath = "//div[@class='product-warranty__head']/../select";
        WebElement warranty = driver.findElement(By.xpath(warrantyXPath));
        warranty.click();

        String war2yearXPath = "//div[@class='product-warranty__head']/../select/option[text()='2 года']";
        WebElement war2 = driver.findElement(By.xpath(war2yearXPath));
        war2.click();

       // wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(ps4Price,firstPrice)));
        //Цена с гарантией
        int secondPrice = Integer.parseInt(ps4Price.getText().replaceAll("[^0-9]", ""));
        System.out.println("с гарантией" + secondPrice);

        String buyButtonXPath = "//div[@class='primary-btn']/button";
        WebElement buyButton = driver.findElement(By.xpath(buyButtonXPath));
        buyButton.click();

        //поиск детроит
        WebElement searchDetroit = driver.findElement(By.xpath("//input[@placeholder='Поиск по сайту']"));
        searchDetroit.click();
        //Thread.sleep(5000);
        searchDetroit.sendKeys("Detroit\n");
        //запомнить цену
        WebElement detroit = driver.findElement(By.xpath(firstPriceXPath));
        wait.until(ExpectedConditions.visibilityOf(detroit));
        int detroitPrice = Integer.parseInt(detroit.getText().replaceAll("[^0-9]", ""));
        System.out.println("цена детроита" + detroitPrice);

        //купить
        WebElement buyDetroit = driver.findElement(By.xpath(buyButtonXPath));
        buyDetroit.click();
        //Thread.sleep(4000);

        //проверить цену корзины
        WebElement priceOfCart = driver.findElement(By.xpath("//span[@class='cart-link__lbl']"));
        int priceOfCartInt = Integer.parseInt(priceOfCart.getText().replaceAll("[^0-9]", ""));
        while(priceOfCartInt == secondPrice) {
            Thread.sleep(500);
            priceOfCartInt = Integer.parseInt(priceOfCart.getText().replaceAll("[^0-9]", ""));
        }
        Assert.assertEquals("Стоимость корзины не равна сумме покупок",secondPrice + detroitPrice,priceOfCartInt);


        //перейти в корзину
        String cartButtonXpath = "//a[@class='ui-link cart-link']";
        WebElement cartButton = driver.findElement(By.xpath(cartButtonXpath));
        cartButton.click();
//-----------------------------------------------------------------------------------
        //проверка гарантии
        String checkWarrantyXPath = "//div[@data-commerce-target='basket_additional_warranty_24']/span";
        WebElement checkWarranty = driver.findElement(By.xpath(checkWarrantyXPath));
        boolean check = false;
        if (checkWarranty.getAttribute("className").equals("base-ui-radio-button__icon base-ui-radio-button__icon_checked")) {
            check = true;
        }
        Assert.assertTrue("Гарантия не выбрана", check);

        //проверить цену каждого товара и сумму
        List<WebElement> list = driver.findElements(By.xpath("//span[@class='price__current']"));
        //String detroitPriceXPath = "";

        WebElement ps4PriceInCart = list.get(0);
        int ps4PriceInCartInt =  Integer.parseInt(ps4PriceInCart.getText().replaceAll("[^0-9]", ""));
        Assert.assertEquals("Цена PS4 не совпадает", firstPrice, ps4PriceInCartInt);
        WebElement detroitPriceInCart = list.get(1);
        int detroitPriceInCartInt = Integer.parseInt(detroitPriceInCart.getText().replaceAll("[^0-9]", ""));
        Assert.assertEquals("Цена Detroit не совпадает", detroitPrice, detroitPriceInCartInt);
        //String ps4PriceXPath = "";
        WebElement summ = list.get(2);
        int sum =  Integer.parseInt(summ.getText().replaceAll("[^0-9]", ""));
        Assert.assertEquals("Сумма не совпадает", firstPrice + detroitPrice, sum);

        //удалить детроит
        String deleteDetroitXPath = "//div[contains(a,'Detroit')]/../div/div/button[text()='Удалить']";
        WebElement deleteDetroit = driver.findElement(By.xpath(deleteDetroitXPath));
        deleteDetroit.click();

        Thread.sleep(2000);
        String tryToFindDetroitXPath = "//div[contains(a,'Detroit')]";
        List<WebElement> listWithoutDetroit = driver.findElements(By.xpath(tryToFindDetroitXPath));
        Assert.assertEquals("Детроит присутствует в корзине", true, listWithoutDetroit.isEmpty());
        String sumXpath = "//div[@class='total-amount__label']/div/div/span[@class='price__current']";
        WebElement sumWithoutDetroit = driver.findElement(By.xpath(sumXpath));
        int sumWithoutDetroitInt = Integer.parseInt(sumWithoutDetroit.getText().replaceAll("[^0-9]", ""));
        Assert.assertEquals("Сумма не уменьшилась", sum-detroitPrice, sumWithoutDetroitInt);

        String addPs4XPath = "//button[@data-commerce-action='CART_ADD']";
        WebElement addPs4 = driver.findElement(By.xpath(addPs4XPath));
        Thread.sleep(15000);
        addPs4.click();
        Thread.sleep(15000);
        addPs4.click();
        Thread.sleep(3000);
        WebElement sumOfPs = driver.findElement(By.xpath(sumXpath));
        int sumOfPsInt = Integer.parseInt(sumOfPs.getText().replaceAll("[^0-9]", ""));
        Assert.assertEquals("Сумма не совпадает", firstPrice * 3,sumOfPsInt);

        String returnRemovedXPath = "//div[@class='group-tabs-menu']/span/span[normalize-space()='Вернуть удалённый товар']";
        WebElement returnRemoved = driver.findElement(By.xpath(returnRemovedXPath));
        returnRemoved.click();
        Thread.sleep(2000);
        List<WebElement> listAfterReturn = driver.findElements(By.xpath(tryToFindDetroitXPath));

        Assert.assertEquals("Detroit не вернулся в корзину", false, listAfterReturn.isEmpty());
        WebElement sumAfterReturn = driver.findElement(By.xpath(sumXpath));
        int sumAfterReturnInt = Integer.parseInt(sumAfterReturn.getText().replaceAll("[^0-9]", ""));
        Assert.assertEquals("Сумма не изменилась после возврата удаленного товара", firstPrice*3+detroitPrice, sumAfterReturnInt);




*/

    }
}
