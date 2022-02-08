package com.gmail.setrakovov.pageobjects;

import com.gmail.setrakovov.objects.ProductCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BasePage{

    /**
     * Constructor
     */
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitURLContains("/cart");
        waitPageLoaded();
        waitVisibility(headerPage);
    }

    @FindBy(xpath = "//div/span[@class='c-header-checkout__logo u-ml-0']")
    private WebElement headerPage;

    @FindBy(xpath = "//div[@class='c-cart__order']")
    private List<WebElement> listProducts;

    @FindBy(xpath = "//span/a[text()='Удалить']")
    private List<WebElement> listButtonsRemoveProductFromCart;

    @FindBy(xpath = "//div[@class='c-popup__content']//span[normalize-space(text())='Удалить']")
    private WebElement buttonsRemoveProductOnPopupForm;

    private final String xpathButtonProceedToCheckout = "//input[@value='Перейти к оформлению']";

    /**
     * Method for removing product by name from cart
     */
    public List<ProductCard> removeProductFromCart(Integer itemIndex, List<ProductCard> productCards) {
        log.info("Remove product with index '" + itemIndex + "' from cart");
        ProductCard product = productCards.get(itemIndex);
        String xpathButtonRemoveProductFromCart = "//div[@class='c-cart-item__content-wrapper' and ./div/a[normalize-space(text())='" + product.getName() + "']]/following-sibling::div//span/a[text()='Удалить']";
        WebElement webElement = driver.findElement(By.xpath(xpathButtonRemoveProductFromCart));
        click(webElement);
        click(buttonsRemoveProductOnPopupForm);
        waitInVisibility(webElement);
        Assert.assertTrue(driver.findElements(By.xpath(xpathButtonRemoveProductFromCart)).size() < 1, "Product " + product.getName() + " is removed from cart.");
        productCards.remove(product);
        return productCards;
    }
    /**
     * Method of checking count of products in cart
     */
    public int countOfProducts() {
        return listProducts.size();
    }
    /**
     * Method oj checking whether a product is not presented in cart
     */
    public Boolean productIsRemovedFromCart(String productName) {
        String xpathCardProductInCart = "//div[@class='c-cart-item__header']/a[normalize-space(text())='" + productName + "']";
        return (countElements(xpathCardProductInCart) == 0);
    }
    /**
     * Method oj checking is available proceed to checkout
     */
    public Boolean isAvailableToProceedCheckout() {
        return (countElements(xpathButtonProceedToCheckout) > 0);
    }

}
