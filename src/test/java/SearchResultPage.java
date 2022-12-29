import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchResultPage {
    private final ElementsCollection articleTitles = $$x("//h3");

    public SelenideElement getFirstElemFromArticle(){
        return articleTitles.first();
    }
}
