package com.gmail.setrakovov.pageobjects;

import com.gmail.setrakovov.objects.ProductCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CatalogPage extends HeaderPage {

    /**
     * Constructor
     */
    public CatalogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitURLContains("/stiralnye-i-sushilnye-mashiny-2427/stiralnye-mashiny-89");
        waitVisibility(headerPageH1);
    }

    @FindBy(xpath = "//h1[normalize-space(text())]")
    private WebElement headerPageH1;

    @FindBy(xpath = "//mvid-dropdown//div[@class='dropdown__title' and ./span[contains(text(), 'Сначала')]]")
    private WebElement ddSearchResultSort;

    private String xpathIconViewSwitcherList = "//div[@class='listing-view-switcher__inner-area']/div[contains(@class, 'pointer--list')]";

    @FindBy(xpath = "//div[@class='listing-view-switcher__inner-area']/mvid-button[1]/button")
    private WebElement buttonViewSwitcherList;

    @FindBy(xpath = "//mvid-dropdown//div[@class='dropdown__options' and ./div[contains(text(), 'Сначала')]]/div")
    private List<WebElement> ddListSearchResultSort;

    @FindBy(xpath = "//mvid-plp-cart-button//button")
    private List<WebElement> listButtonsAddToCart;

    @FindBy(xpath = "//mvid-plp-product-card")
    private List<WebElement> listProductCards;

    /**
     * Method for selecting in sort dropdown
     */
    public void selectInListSearchResultSort(String value) {
        click(ddSearchResultSort);
        for(WebElement element:ddListSearchResultSort){
            if (element.getText().equals(value)){
                click(element);
                break;
            }
        }
    }
    /**
     * Method for switching product list view
     */
    public void switchProductListViewToGrid() {
        if(driver.findElements(By.xpath(xpathIconViewSwitcherList)).size() == 0){
            click(buttonViewSwitcherList);
        }
    }
    /**
     * Method for adding to cart
     */
    public Integer addToCartItem(Integer itemIndex) {
        ProductCard productCard;
        Integer i = itemIndex - 1;
        waitPageLoaded();
        waitVisibility(listProductCards.get(i));
        WebElement titleOfProduct = listProductCards.get(i).findElement(By.xpath(".//a[@class='product-title__text']"));
        WebElement priceOfProduct = listProductCards.get(i).findElement(By.xpath(".//span[@class='price__main-value']"));
        WebElement buttonAddToCart = listProductCards.get(i).findElement(By.xpath(".//button[@title='Добавить в корзину']"));
        String name = readText(titleOfProduct);
        Integer price = Integer.parseInt(readText(priceOfProduct).replace("₽", "").replace(" ", ""));
        productCard = new ProductCard(name, price);
        super.productCards.add(productCard);
        click(buttonAddToCart);
        return super.productCards.size();
    }
    /**
     * Method for check added product in cart
     */
    public Boolean productIsAddedToCart() {
        return super.productIsPresentInCartForm(productCards.get(productCards.size() - 1));
    }
}
