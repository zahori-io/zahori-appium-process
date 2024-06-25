package io.zahori.process;

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
import io.zahori.framework.core.TestContext;
import io.zahori.framework.core.ZahoriProcess;
import io.zahori.framework.utils.Pause;
import io.zahori.model.process.CaseExecution;
import io.zahori.process.pages.WikipediaPageMobile;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchWikipediaAppium extends ZahoriProcess {

    /*
     * Warning! Do not declare any variables here, values are overwritten when
     * several cases are executed in parallel.
     */
    @Override
    public void run(TestContext testContext, CaseExecution caseExecution) {

        // Read case data
        Map<String, String> data = caseExecution.getCas().getDataMap();
        String language = data.get("Language");
        String searchText = data.get("Search");

        // Load page
        String url = caseExecution.getConfiguration().getEnvironmentUrl();
        testContext.getBrowser().loadPage(url);

        WikipediaPageMobile wiki = new WikipediaPageMobile(testContext);
        if (wiki.pageLoaded()) {
            testContext.logStepPassedWithScreenshot("Page loaded");
        }

        wiki.selectLanguage(language);
        Pause.pause(2);
        testContext.logStepPassedWithScreenshot("Language selected: {}", language);

        if (testContext.isAndroidDriver()) {
            Pause.pause(2);
            wiki.closeDonateBanner();
        }

        wiki.search(searchText);
        Pause.pause(2);
        testContext.logStepPassedWithScreenshot("Search results: {}", searchText);

        wiki.selectFirstResult();
        Pause.pause(2);

        String firstParagraph = wiki.getFirstParagraph();
        testContext.logStepPassedWithScreenshot("First paragraph: {}", firstParagraph);

    }
}
