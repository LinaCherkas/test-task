import org.junit.Assert;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;

public class SearchTest extends MainClass {
    private final static String BASE_URL = "https://www.google.com/";
    private final static String SEARCH_STRING = "iTechArt";

    @Test
    public void checkSearchResultLink() {

        SearchPage searchPage = new SearchPage(BASE_URL);

        searchPage.getSearchField().shouldBe(visible);
        searchPage.getGoogleButtonsMap().forEach((key, value) -> key.shouldBe(exist)
                .shouldHave(attribute("value", value)));

        SearchResultPage searchResultPage = searchPage.search(SEARCH_STRING);

        Assert.assertTrue(searchResultPage.getFirstElemFromArticle().getText().contains(SEARCH_STRING));
        searchResultPage.getFirstElemFromArticle().click();
    }

}
