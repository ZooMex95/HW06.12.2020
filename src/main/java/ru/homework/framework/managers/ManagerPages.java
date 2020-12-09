package ru.homework.framework.managers;

import ru.homework.framework.pages.*;
import ru.homework.framework.product.Product;

public class ManagerPages {
    private static ManagerPages managerPages;

    BasePage basePage;

    SearchPage searchPage;

    StartPage startPage;

    ProductPage productPage;

    CartPage cartPage;


    private ManagerPages() {

    }

    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    public BasePage getBasePage() {
        if (basePage == null) {
            basePage = new BasePage();
        }
        return basePage;
    }

    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }
}
