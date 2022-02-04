package com.gmail.setrakovov.pageobjects;

import com.gmail.setrakovov.objects.ProductCard;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class HeaderPage extends BasePage {

    /**
     * Constructor
     */
    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitVisibility(iconMainPage);
    }

    @FindBy(xpath = "//a[@href='https://www.mvideo.ru/']")
    private WebElement iconMainPage;

    @FindBy(xpath = "//mvid-button[@label='Каталог']")
    private WebElement buttonCatalogMain;

    @FindBy(xpath = "//mvid-catalog/div[@class='catalog-container ng-star-inserted']")
    private WebElement formCatalog;

    @FindBy(xpath = "//div/a[text()='Техника для дома']")
    private WebElement category;

    @FindBy(xpath = "//a/span[text()='Стиральные машины']")
    private WebElement subCategory;

    @FindBy(xpath = "//mvid-header-icon/div/a[@title='Корзина']")
    private WebElement iconCart;

    @FindBy(xpath = "//div[@class='nav-tab tab-cart']//mvid-bubble")
    private WebElement iconCounterProductsInCart;

    @FindBy(xpath = "//mvid-header-icon-tooltip/div")
    private WebElement formProductsInCart;

    @FindBy(xpath = "//mvid-header-icon-tooltip/div//a[@href and text()]")
    private List<WebElement> productsInCartForm;

    /**
     * Method for expanding catalog form and go to subCategory page
     */
    public CatalogPage goToSubItemCatalogPage() {
        log.debug("Go to sub-item catalog page");
        click(buttonCatalogMain);
        moveMouse(category);
        click(subCategory);
        return new CatalogPage(driver);
    }
    /**
     * Method of going to subCategory page
     */
    public CartPage goToCartPage() {
        log.debug("Go to cart page");
        click(iconCart);
        return new CartPage(driver);
    }
    /**
     * Method of checking count of products in the cart form
     */
    public int countOfProductsInCartForm() {
        return productsInCartForm.size();
    }
    /**
     * Method oj checking whether a product is present in the cart
     */
    public Boolean productIsPresentInCartForm(ProductCard product) {
        log.debug("Check, that product is present in");
        waitVisibility(formProductsInCart);
        String xpathProductInBasket = ".//a[@href and text()='" + product.getName() + "']";
        return (countElements(xpathProductInBasket) > 0);
    }

}
