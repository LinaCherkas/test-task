import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {
    private final SelenideElement searchField = $x("//input[@type='text']");

    public SearchPage(String url) {
        Selenide.open(url);
        setGoogleButtonsMap();
    }

    private final Map<SelenideElement, String> googleButtonsMap = new HashMap<>();

    public SelenideElement getSearchField() {
        return searchField;
    }

    public Map<SelenideElement, String> getGoogleButtonsMap() {
        return googleButtonsMap;
    }

    public void setGoogleButtonsMap() {
        googleButtonsMap.put($x("//input[@value='Google Search']"), "Google Search");
        googleButtonsMap.put($x("//input[@value='Google Search']/following-sibling::input"),
                "I'm Feeling Lucky");
    }

    public SearchResultPage search(String searchString) {
        searchField.val(searchString).pressEnter();
        return new SearchResultPage();
    }
}
