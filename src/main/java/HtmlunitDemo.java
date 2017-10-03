import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.jsoup.helper.StringUtil.isNumeric;


public class HtmlunitDemo {

    private static List<Integer> pages = new ArrayList<Integer>();
    private static Integer currentPage = 1;
    private static boolean isEnd = true;

    public static void main(String[] args) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(35000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3192.0 Safari/537.36");
        HtmlPage page = webClient.getPage("http://www.sse.com.cn/assortment/stock/list/share/");
        pages.add(1);
        while(true){
            String html = page.asXml();
            if(isEmpty(html)){
                for(String str : getCommpanyNames(html)){
                    System.out.println(str);
                }
                List<HtmlAnchor> htmlListItems = page.getByXPath("//*[@id=\"idStr\"]");
                for(HtmlAnchor htmlAnchor : htmlListItems){
                    String number = htmlAnchor.asText();
                    if(isNumeric(number)){
                        Integer pageNumber = Integer.valueOf(number);
                        if(pageNumber > currentPage){
                            isEnd = false;
                            pages.add(pageNumber);
                            currentPage = pageNumber;
                            htmlAnchor.click();
                            break;
                        }
                    }
                }
                if(isEnd){
                    break;
                }else{
                    isEnd = true;
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断js是否加载出来
     * @param html
     * @return
     */
    private static boolean isEmpty(String html){
        return Jsoup.parse(html).getElementsByTag("tr").size() > 10;
    }


    /**
     * 抓取公司简称
     * @param html　
     * @return　公司简称列表
     */
    private static List<String> getCommpanyNames(String html){
        Document doc = Jsoup.parse(html);
        Elements trs = doc.getElementsByTag("tr");
        List<String> comms = new ArrayList<String>();
        for(Element tr : trs){
            String[] strings = tr.text().split(" ");
            if(strings.length > 1)
               comms.add(strings[1]);
        }
        return comms;
    }
}
