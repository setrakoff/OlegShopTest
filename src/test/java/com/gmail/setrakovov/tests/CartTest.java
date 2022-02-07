package com.gmail.setrakovov.tests;

import com.gmail.setrakovov.objects.ProductCard;
import com.gmail.setrakovov.pageobjects.CartPage;
import com.gmail.setrakovov.pageobjects.CatalogPage;
import com.gmail.setrakovov.pageobjects.HeaderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CartTest extends BaseTest {

    @Test(description = "Check the adding and removing products from cart")
    public void checkAddingAndRemovingFromCart() {
        List<ProductCard> productCards = new ArrayList<>();
        HeaderPage headerPage = new HeaderPage(driver);
        String productName;

        CatalogPage catalogPage = headerPage.goToSubItemCatalogPage();
        catalogPage.selectInListSearchResultSort("Сначала дороже");
        catalogPage.switchProductListViewToGrid();
        productCards = catalogPage.addToCartItem(0, productCards);
        Assert.assertEquals(headerPage.countOfProductsInCartForm(), productCards.size(), "Count of products in cart form is '" + productCards.size() + "'");
        Assert.assertTrue(catalogPage.productIsAddedToCart(productCards), "Product 1 is added to cart.");
        productCards = catalogPage.addToCartItem(1, productCards);
        Assert.assertEquals(headerPage.countOfProductsInCartForm(), productCards.size(), "Count of products in cart form is '" + productCards.size() + "'");
        Assert.assertTrue(catalogPage.productIsAddedToCart(productCards), "Product 2 is added to cart.");
        productCards = catalogPage.addToCartItem(2, productCards);
        Assert.assertEquals(headerPage.countOfProductsInCartForm(), productCards.size(), "Count of products in cart form is '" + productCards.size() + "'");
        Assert.assertTrue(catalogPage.productIsAddedToCart(productCards), "Product 3 is added to cart.");

        CartPage cartPage = headerPage.goToCartPage();
        productName = productCards.get(0).getName();
        productCards = cartPage.removeProductFromCart(0, productCards);
        Assert.assertEquals(cartPage.countOfProducts(), productCards.size(), "Count of products in cart is '" + productCards.size() + "'");
        Assert.assertTrue(cartPage.productIsRemovedFromCart(productName), "Product 1 is removed from cart.");

    }

    @Test(description = "Check if it wouldn't possible to proceed to the payment if the cart is empty")
    public void checkProceedButtonIfCartIsEmpty(){
        List<ProductCard> productCards = new ArrayList<>();
        HeaderPage headerPage = new HeaderPage(driver);

        CatalogPage catalogPage = headerPage.goToSubItemCatalogPage();
        catalogPage.switchProductListViewToGrid();
        productCards = catalogPage.addToCartItem(0, productCards);
        Assert.assertTrue(catalogPage.productIsAddedToCart(productCards), "Product 1 is added to cart.");

        CartPage cartPage = headerPage.goToCartPage();
        Assert.assertTrue(cartPage.isAvailableToProceedCheckout(), "Proceed payment button is available");
        productCards = cartPage.removeProductFromCart(0, productCards);
        Assert.assertEquals(cartPage.countOfProducts(), productCards.size(), "Count of products in cart is '" + productCards.size() + "'");
        Assert.assertFalse(cartPage.isAvailableToProceedCheckout(), "Proceed payment button is not available");
    }

}
