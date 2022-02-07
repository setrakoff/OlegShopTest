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
    public CatalogPage(WebDriver driver, String partOfUrl) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitURLContains(partOfUrl);
        waitPageLoaded();
        waitVisibility(contentOnPage);
    }

    @FindBy(xpath = "//main[@class='layout__content']")
    private WebElement contentOnPage;

    @FindBy(xpath = "//mvid-dropdown//div[@class='dropdown__title' and ./span[contains(text(), 'Сначала')]]")
    private WebElement ddSearchResultSort;

    private final String xpathIconViewSwitcherList = "//div[@class='listing-view-switcher__inner-area']/div[contains(@class, 'pointer--list')]";

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
    public List<ProductCard> addToCartItem(Integer itemIndex, List<ProductCard> productCards) {
        ProductCard productCard;
        waitPageLoaded();
        waitVisibility(listProductCards.get(itemIndex));
        WebElement titleOfProduct = listProductCards.get(itemIndex).findElement(By.xpath(".//a[@class='product-title__text']"));
        WebElement priceOfProduct = listProductCards.get(itemIndex).findElement(By.xpath(".//span[@class='price__main-value']"));
        WebElement buttonAddToCart = listProductCards.get(itemIndex).findElement(By.xpath(".//button[@title='Добавить в корзину']"));
        String name = readText(titleOfProduct);
        Integer price = Integer.parseInt(readText(priceOfProduct).replace("₽", "").replace(" ", ""));
        productCard = new ProductCard(name, price);
        productCards.add(productCard);
        click(buttonAddToCart);
        return productCards;
    }
    /**
     * Method for checking added product in cart
     */
    public Boolean productIsAddedToCart(List<ProductCard> productCards) {
        return productIsPresentInCartForm(productCards.get(productCards.size() - 1));
    }
    /**
     * Method for checking added product in cart
     */
    public Boolean selectedCategoryIsTV() {
        String xpathCategoryButton = "//button[normalize-space(text())='Телевизоры' and contains(@class, 'selected')]";
        Boolean isCorrectURL = driver.getCurrentUrl().contains("category=televizory");
        Boolean isCorrectElement = countElements(xpathCategoryButton) == 1;
        return isCorrectURL && isCorrectElement;
    }
    /**
     * Method for checking the presence of keyword in results
     */
    public Boolean isCatalogResultsContainsKeyword(String keyword) {
        Integer counter = 0;
        waitVisibilityOfAllElements(listProductCards);
        for (WebElement w:listProductCards) {
            WebElement titleOfProduct = w.findElement(By.xpath(".//a[@class='product-title__text']"));
            if (readText(titleOfProduct).toLowerCase().contains(keyword.toLowerCase()))
            {counter ++;}
        }
        Boolean b = listProductCards.size() == counter;
        return listProductCards.size() == counter;
    }
}
