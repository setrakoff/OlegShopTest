package com.gmail.setrakovov.tests;

import com.gmail.setrakovov.pageobjects.CatalogPage;
import com.gmail.setrakovov.pageobjects.HeaderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest{
    @Test
    public void checkSearchByKeyword() {
        HeaderPage headerPage = new HeaderPage(driver);
        String keyWord = "TV Samsung";

        CatalogPage catalogPage = headerPage.searchProductByKeyword(keyWord);
        catalogPage.switchProductListViewToGrid();
        Assert.assertTrue(catalogPage.selectedCategoryIsTV(), "Selected category is 'Телевизоры'");
        Assert.assertTrue(catalogPage.isCatalogResultsContainsKeyword(keyWord.replace("TV", "Телевизор")), "Each catalog results line contains keyword '" + keyWord + "'");
    }
}
