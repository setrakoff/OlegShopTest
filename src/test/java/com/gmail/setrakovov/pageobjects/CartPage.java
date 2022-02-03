package com.gmail.setrakovov.pageobjects;

import com.gmail.setrakovov.objects.ProductCard;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

@Slf4j
public class CartPage extends BasePage{

    /**
     * Constructor
     */
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitURLContains("/cart");
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

    /**
     * Method for removing product by name from cart
     */
    public Integer removeProductFromCart(Integer index) {
        ProductCard product = productCards.get(index);
        String xpathButtonRemoveProductFromCart = "//div[@class='c-cart-item__content-wrapper' and ./div/a[normalize-space(text())='" + product.getName() + "']]/following-sibling::div//span/a[text()='Удалить']";
        WebElement webElement = driver.findElement(By.xpath(xpathButtonRemoveProductFromCart));
        click(webElement);
        click(buttonsRemoveProductOnPopupForm);
        waitInVisibility(webElement);
        Assert.assertTrue(driver.findElements(By.xpath(xpathButtonRemoveProductFromCart)).size() < 1, "Product " + product.getName() + " is removed from cart.");
        productCards.remove(product);
        return productCards.size();
    }
    /**
     * Method of checking count of products in cart
     */
    public Integer countOfProducts() {
        return listProducts.size();
    }
    /**
     * Method oj checking whether a product is not presented in cart
     */
    public Boolean productIsRemovedFromCart(Integer index) {
        ProductCard product = productCards.get(index);
        String xpathCardProductInCart = "//div[@class='c-cart-item__header']/a[normalize-space(text())='" + product.getName() + "']";
        return (countElements(xpathCardProductInCart) == 0);
    }

}
