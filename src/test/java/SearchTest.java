import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.junit.Test;

public class SearchTest extends MainClass {
    private final static String BASE_URL = "https://www.google.com/";
    private final static String SEARCH_STRING = "iTechArt";

    @Test
    public void checkSearchResultLink() {

        SearchPage searchPage = new SearchPage(BASE_URL);
        searchPage.getSearchField().shouldBe(Condition.visible);

        searchPage.getGoogleButtonsMap().forEach((key, value) -> key.shouldBe(Condition.exist)
                .shouldHave(Condition.attribute("value", value)));

        SearchResultPage searchResultPage = searchPage.search(SEARCH_STRING);
        Assert.assertTrue(searchResultPage.getFirstElemFromArticle().getText().contains(SEARCH_STRING));
        searchResultPage.getFirstElemFromArticle().click();
    }

}
