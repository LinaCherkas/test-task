import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {
    private final SelenideElement searchField = $x("//input[@type='text']");

    private final ArrayList<SelenideElement> googleButtonsList = new ArrayList<>() {{
        add($x("//input[@value='Google Search']"));
        add($x("//input[@value='Google Search']/following-sibling::input"));
    }};

    public SearchPage(String url){
        Selenide.open(url);
    }

    public SelenideElement getSearchField (){
        return searchField;
    }

    public ArrayList<SelenideElement> getGoogleButtonsList (){
        return googleButtonsList;
    }

    public SearchResultPage search(String searchString){
        searchField.val(searchString).pressEnter();
        return new SearchResultPage();
    }
}
