package com.gmail.setrakovov.tests;

import com.gmail.setrakovov.pageobjects.CartPage;
import com.gmail.setrakovov.pageobjects.CatalogPage;
import com.gmail.setrakovov.pageobjects.HeaderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartAndRemoveOneTest extends BaseTest {

    @Test
    public void addThreeSmartphonesToCart() {
        HeaderPage headerPage = new HeaderPage(driver);
        Integer countOfProductsInCart;

        CatalogPage catalogPage = headerPage.goToSubItemCatalogPage();
        catalogPage.selectInListSearchResultSort("Сначала дороже");
        catalogPage.switchProductListViewToGrid();
        countOfProductsInCart = catalogPage.addToCartItem(1);
        Assert.assertEquals(headerPage.countOfProductsInCartForm(), countOfProductsInCart, "Count of products in cart form is '" + countOfProductsInCart + "'");
        Assert.assertTrue(catalogPage.productIsAddedToCart(), "Product 1 is added to cart.");
        countOfProductsInCart = catalogPage.addToCartItem(2);
        Assert.assertEquals(headerPage.countOfProductsInCartForm(), countOfProductsInCart, "Count of products in cart form is '" + countOfProductsInCart + "'");
        Assert.assertTrue(catalogPage.productIsAddedToCart(), "Product 2 is added to cart.");
        countOfProductsInCart = catalogPage.addToCartItem(3);
        Assert.assertEquals(headerPage.countOfProductsInCartForm(), countOfProductsInCart, "Count of products in cart form is '" + countOfProductsInCart + "'");
        Assert.assertTrue(catalogPage.productIsAddedToCart(), "Product 3 is added to cart.");

        CartPage cartPage = headerPage.goToCartPage();
        countOfProductsInCart = cartPage.removeProductFromCart(1);
        Assert.assertEquals(cartPage.countOfProducts(), countOfProductsInCart, "Count of products in cart is '" + countOfProductsInCart + "'");
        Assert.assertTrue(cartPage.productIsRemovedFromCart(1), "Product 1 is removed from cart.");

    }

}
