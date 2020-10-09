package kz.parser.etml;

import kz.parser.etml.reflection.GenericType;
import kz.parser.etml.testmodels.TestModel;
import kz.parser.etml.testmodels.TestModelWithoutBase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEtml {

    private static Etml etml;

    @BeforeAll
    public static void setUp() {
        etml = new Etml();
    }

    @Test
    public void testNoRequiredAnnotation() {
        Assertions.assertThrows(EtmlParseException.class, () -> {
            etml.fromHtml(null, TestModelWithoutBase.class);
        });
    }

    @Test
    public void testSuccess() {
        String html = "<html>\n" +
                "<head>\n" +
                "<title>Enter a title, displayed at the top of the window.</title>\n" +
                "</head>\n" +
                "<!-- The information between the BODY and /BODY tags is displayed.-->\n" +
                "<body>\n" +
                "<h1>17</h1>\n" +
                "<p>Be <b>bold</b> in stating your key points. Put them in a list: </p>\n" +
                "<ul>\n" +
                "<li>The first item in your list</li>\n" +
                "<li>The second item; <i>italicize</i> key words</li>\n" +
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
        Document doc = Jsoup.parse(html);
        TestModel model = etml.fromHtml(doc, TestModel.class);

        System.out.println("Name is: " +  model.getName());
        System.out.println("Age is: " +  model.getAge());
    }

}


