package io.zahori.process.pages;

/*-
 * #%L
 * zahori-process
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2021 PANEL SISTEMAS INFORMATICOS,S.L
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
import io.zahori.framework.core.Locator;
import io.zahori.framework.core.Page;
import io.zahori.framework.core.PageElement;

public class WikipediaPageMobile extends Page {

    private static final long serialVersionUID = -8823897590918241825L;

    public WikipediaPageMobile(io.zahori.framework.core.TestContext testContext) {
        super(testContext);
    }

    public boolean pageLoaded() {
        PageElement logo = new PageElement(this, "Central logo", Locator.xpath("//img[@class='central-featured-logo']"));
        return logo.waitElementVisible();
    }

    public void selectLanguage(String language) {
        PageElement languageSelector = new PageElement(this, "Language selector", Locator.xpath("//a[contains(@id,'-" + language + "')]"));
        languageSelector.click();
    }

    public void closeDonateBanner() {
        PageElement alreadyDonatedButton = new PageElement(this, "Already donated button", Locator.xpath("//button[contains(@class,'frb-iad-link')]"));
        if (alreadyDonatedButton.isPresent()) {
            alreadyDonatedButton.click();
        }

        PageElement hideDonationButton = new PageElement(this, "Hide donation button", Locator.xpath("//button[@name='frb-iad-hide-button']"));
        if (hideDonationButton.isPresent()) {
            hideDonationButton.click();
        }
    }

    public void search(String textToSearch) {
        PageElement searchButton = new PageElement(this, "Search button", Locator.xpath("//*[@id='searchIcon']/span[1]"));
        searchButton.click();

        PageElement searchInput = new PageElement(this, "Search input", Locator.xpath("(//input[@name='search'])[2]"));
        searchInput.write(textToSearch);
    }

    public void selectFirstResult() {
        PageElement firstResult = new PageElement(this, "First result", Locator.xpath("(//li[@class='page-summary']/a)[1]"));
        firstResult.click();
    }

    public String getFirstParagraph() {
        PageElement firstParagraph = new PageElement(this, "First paragraph", Locator.xpath("(//div[@id='bodyContent']//p)[1]"));
        return firstParagraph.getText();
    }
}
