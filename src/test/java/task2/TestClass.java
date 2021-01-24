package task2;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;


/**
 * @author Vadym Nastoiashchyi
 */

public class TestClass extends WebDriverProperties {

    @Test
    public void test() {
        //create link storage
        List<String> formatLinks = new LinkedList<>();
        List<String> links = new LinkedList<>();
        String searchWord = "automation";
        //create page storage
        int pages = 5;
        int currentPage = 1;

        //get connection to url
        driver.get("https://www.google.com/");

        //use google search form
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(searchWord);
        element.submit();

        //Engine processing logic and for getting links and turning pages
        endIteration:
        for (int i = 1; i < pages; i++, currentPage++) {
            int linkCount = 1;
            links.clear();
            formatLinks.clear();
            List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']/div/div"));
            getLinks(findElements, links);
            formateLink(links, formatLinks);
            for (int j = 0; j < formatLinks.size(); j++) {
                if (formatLinks.get(j).contains(searchWord)) {
                    Assert.assertTrue(formatLinks.get(j).contains(searchWord));
                    break endIteration;
                }
                if (linkCount == formatLinks.size()) {
                    WebElement getNextPage = driver.findElement(By.xpath("//*[@id='xjs']/div/table/tbody/tr/td[12]"));
                    WebElement getHref = getNextPage.findElement(By.tagName("a"));
                    driver.navigate().to(getHref.getAttribute("href"));
                    linkCount = 1;
                }
                linkCount++;
            }
        }
        Assert.assertNotEquals(currentPage, pages);
    }

    public void getLinks(List<WebElement> findElements, List<String> links) {
        for (WebElement findElement : findElements) {
            WebElement getLinks = findElement.findElement(By.tagName("a"));
            links.add(getLinks.getAttribute("href"));
        }

    }

    public void formateLink(List<String> links, List<String> formatLinks) {
        for (String l : links) {
            String temp = l.substring(8);
            formatLinks.add(temp.substring(0, temp.indexOf('/')));
        }
    }

}





