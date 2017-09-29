import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;

import java.io.IOException;
import java.util.Set;

public class HtmlunitDemo {
    public static void main(String[] args) throws IOException {

//        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
//        HttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
//        String tieba = "https://www.douban.com/people/163296676/rev_contacts";
//        HttpGet httpGet = new HttpGet(tieba);
//        String Cookie = "bid=fp-BlwmyeTY; __yadk_uid=dLpMqMsIGD1N38NzhbcG3E6QA33NQ9bE; ps=y; _pk_ref.100001.8cb4=%5B%22%22%2C%22%22%2C1506515077%2C%22https%3A%2F%2Faccounts.douban.com%2Flogin%3Falias%3D793890838%2540qq.com%26redir%3Dhttps%253A%252F%252Fwww.douban.com%26source%3DNone%26error%3D1013%22%5D; ll=\"108296\"; ue=\"793890838@qq.com\"; __utmt=1; _ga=GA1.2.388925103.1505404043; _gid=GA1.2.1409223546.1506515083; dbcl2=\"161927939:ZDwWtUnYaH4\"; ck=rMaO; ap=1; push_noty_num=0; push_doumail_num=0; __utma=30149280.388925103.1505404043.1506510959.1506515077.8; __utmb=30149280.22.9.1506516374528; __utmc=30149280; __utmz=30149280.1506510959.7.5.utmcsr=accounts.douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/login; __utmv=30149280.16192; _pk_id.100001.8cb4=1df4f52fdf296b72.1505404042.8.1506516380.1506512502.; _pk_ses.100001.8cb4=*";
//        try {
//            httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//            httpGet.setHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3192.0 Safari/537.36");
//            httpGet.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//            httpGet.setHeader("Connection","Keep-Alive");
//            httpGet.setHeader("Accept-Language","zh-CN");
//            httpGet.setHeader("Cookie", Cookie);
//            HttpResponse response = httpClient.execute(httpGet);
//            System.out.println(response.toString());
//            HttpEntity responseEntity = response.getEntity();
//            if(response.getStatusLine().getStatusCode() ==  200 && responseEntity != null) {
//                String responseHtml = EntityUtils.toString(responseEntity);
//                System.out.println(responseHtml);
//            }
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        httpGet.abort();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        // Get the first page
        HtmlPage page1 = webClient.getPage("https://www.douban.com/accounts/login");
        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        HtmlForm form = page1.getFormByName("lzform");

        HtmlSubmitInput button = form.getInputByName("login");
        HtmlTextInput email = form.getInputByName("form_email");
        HtmlPasswordInput password = form.getInputByName("form_password");
        // Change the value of the text field
        email.setValueAttribute("793890838@qq.com");
        password.setValueAttribute("jyjycxt19960427");

//        HtmlTextInput captcha = form.getInputByName("captcha-solution");
//        HtmlImage valiCodeImg = (HtmlImage)page1.getElementById("captcha_image");
//
//        ImageReader imageReader = valiCodeImg.getImageReader();
//        BufferedImage bufferedImage = imageReader.read(0);
//
//        JFrame f2 = new JFrame();
//        JLabel l = new JLabel();
//        l.setIcon(new ImageIcon(bufferedImage));
//        f2.getContentPane().add(l);
//        f2.setSize(250, 80);
//        f2.setTitle("验证码");
//        f2.setVisible(true);
//
//        String valicodeStr = JOptionPane.showInputDialog("请输入验证码：");

        //captcha.setValueAttribute(valicodeStr);
        // Now submit the form by clicking the button and get back the second page.
        HtmlPage page2 = button.click();
        System.out.println(page2.getTitleText());
        CookieManager CM = webClient.getCookieManager(); //WC = Your WebClient's name
        Set<Cookie> cookies_ret = CM.getCookies();//返回的Cookie在这里，下次请求的时候可
        WebClient webClient1 = new WebClient(BrowserVersion.CHROME);

        webClient1.addRequestHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3192.0 Safari/537.36");
       // webClient1.addRequestHeader("Cookie", "bid=fp-BlwmyeTY; ps=y; ue=\"793890838@qq.com\"; push_noty_num=0; push_doumail_num=0; __utmt=1; ap=1; ll=\"108296\"; __utma=30149280.388925103.1505404043.1506510959.1506515077.8; __utmb=30149280.5.9.1506515082360; __utmc=30149280; __utmz=30149280.1506510959.7.5.utmcsr=accounts.douban.com|utmccn=(referral)|utmcmd=referral|utmc" +
        //        "ct=/login; __utmv=30149280.16192; _ga=GA1.2.388925103.1505404043; _gid=GA1.2.1409223546.1506515083; _gat_UA-7019765-1=1; as=\"https://www.douban.com/\"");
        //webClient1.getCookieManager().addCookie(new Cookie("bid=fp-BlwmyeTY; ps=y; ue=\"793890838@qq.com\"; push_noty_num=0; push_doumail_num=0; __utmt=1; ap=1; ll=\"108296\"; __utma=30149280.388925103.1505404043.1506510959.1506515077.8; __utmb=30149280.5.9.1506515082360; __utmc=30149280; __utmz=30149280.1506510959.7.5.utmcsr=accounts.douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/login; __utmv=30149280.16192; _ga=GA1.2.388925103.1505404043; _gid=GA1.2.1409223546.1506515083; _gat_UA-7019765-1=1; as=\"https://www.douban.com/"));
//        webClient1.getCookieManager().addCookie(new Cookie(".douban.com", "bid", "Qyc_DxdYIT0", "/", new Date("Thu Sep 27 20:05:22 CST 2018"), true));
//        webClient1.getCookieManager().addCookie(new Cookie(".douban.com", "ue", "793890838@qq.com", "/", new Date("Thu Sep 27 20:05:27 CST 2018"), true));
//        webClient1.getCookieManager().addCookie(new Cookie(".douban.com", "dbcl2", "161927939:N07DURSH5T4"));
//        webClient1.getCookieManager().addCookie(new Cookie(".douban.com", "ck", "OZKD"));
//        Iterator<Cookie> i = cookies_ret.iterator();
//        while (i.hasNext()) {
//            Cookie cookie = i.next();
//            webClient1.getCookieManager().addCookie(cookie);
//            System.out.println(cookie.getDomain() + "  " + cookie.getName() + "  " +
//                    cookie.getValue()+ "  " + cookie.getPath()  + "  " + cookie.getExpires());
//        }

        HtmlPage page3 = webClient1.getPage("https://www.douban.com/people/163296676/rev_contacts");

        System.out.println(page3.getTitleText());
    }
}
