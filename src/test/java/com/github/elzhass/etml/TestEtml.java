package com.github.elzhass.etml;

import com.github.elzhass.etml.testmodels.TestModelWithoutBase;
import com.github.elzhass.etml.testmodels.TestModel;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 *
 *  * Copyright (C) 2020 Suleimenov Yelzhas.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

public class TestEtml {

    private static Etml etml;
    private static final String HTML = "<html>\n" +
            "<head>\n" +
            "<title>Enter a title, displayed at the top of the window.</title>\n" +
            "</head>\n" +
            "<!-- The information between the BODY and /BODY tags is displayed.-->\n" +
            "<body>\n" +
            "<h1>999999999</h1>\n" +
            "<h2> FALSE </h2>\n" +
            "<p>Be <b>bold</b> in stating your key points. Put them in a list: </p>\n" +
            "<ul>\n" +
            "<li>The first item in your list</li>\n" +
            "<h1>22</h1>\n" +
            "<ol>Test</ol>" +
            "<ol>Test1</ol>" +
            "<ol>Test2</ol>" +
            "</ul>\n" +
            "<ul>\n" +
            "<li>The second item; <i>italicize</i> key words</li>\n" +
            "<h1>23</h1>\n" +
            "<ol>Test3</ol>" +
            "<ol>Test4</ol>" +
            "<ol>Test5</ol>" +
            "</ul>\n" +
            "<p>Improve your image by including an image. </p>\n" +
            "<p><img src=\"http://www.mygifs.com/CoverImage.gif\" alt=\"A Great HTML Resource\"></p>\n" +
            "<p>Add a link to your favorite <a href=\"https://www.dummies.com/\">Web site</a>.\n" +
            "Break up your page with a horizontal rule or two. </p>\n" +
            "<hr>\n" +
            "<p>Finally, link to <a href=\"page2.html\">another page</a> in your own Web site.</p>\n" +
            "<!-- And add a copyright notice.-->\n" +
            "<p>&#169; Wiley Publishing, 2011</p>\n" +
            "</body>\n" +
            "</html>";

    @BeforeAll
    public static void setUp() {
        etml = new Etml();
    }

    @Test
    public void testNoRequiredAnnotation() {
        Assertions.assertThrows(EtmlParseException.class, () -> {
            etml.fromHtml(HTML, TestModelWithoutBase.class);
        });
    }

    @Test
    public void testSuccessPlainHTML() {
        TestModel model = etml.fromHtml(HTML, TestModel.class);
    }

    @Test
    public void testSuccessJsoupDoc() {
        org.jsoup.nodes.Document doc = Jsoup.parse(HTML);
        TestModel model = etml.fromHtml(doc, TestModel.class);
    }

}


