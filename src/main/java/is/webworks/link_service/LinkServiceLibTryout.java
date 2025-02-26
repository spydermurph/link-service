package is.webworks.link_service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkServiceLibTryout {

    static Pattern htmlPattern = Pattern.compile(".*\\<[^>]+>.*", Pattern.DOTALL);
    static Pattern linkPattern = Pattern.compile("\\b((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:, .;]*[-a-zA-Z0-9+&@#/%=~_|])", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args){
        //htmlTest();
        stringTest();

        String str = "Welcome to https://www.geeksforgeeks.org:8934 Computer Science Portal";
        //extractURL(str);
    }

    public static void htmlTest(){
        String html = "<a href=\"https://www.example.com\">Example</a><a href=\"https://www.google.com\">Google</a><a href=\"./page\">Google</a>";
        if(!htmlPattern.matcher(html).matches()){
            System.out.println("Not html!");
            return;
        }
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("a[href]");
        for(Element link : links){
            System.out.println(link.attr("href"));
            String originalLink = link.attr("abs:href");
            if(originalLink.startsWith("https://") || originalLink.startsWith("http://")){
                link.attr("href", "https://www.mbl.is");
            }
            System.out.println(link.attr("abs:href"));
        }
        System.out.println(doc.body().html());
        System.out.println(doc.html());
    }

    public static void stringTest(){
        String testString = "Sæll Arnar\n" +
                "\n" +
                "Here is the task I mentioned\n" +
                "https://bokunteam.notion.site/link-service-assignment\n" +
                "\n" +
                "Please let me know if you have any questions.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Sindri Traustason\n" +
                "\n" +
                "Director of Engineering\n" +
                "\n" +
                "https://www.bokun.io";

        String regexStr = "\\b((?:https?|ftp|file):\\/\\/[-a-zA-Z0-9+&@#\\/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#\\/%=~_|])";

        Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(testString);

        ArrayList<String> urlList = new ArrayList<>();
        while (matcher.find()) {
            urlList.add(matcher.group());
        }

        StringBuilder builder = new StringBuilder(testString);
        if(urlList.isEmpty()){
            System.out.println("Empty");
        } else {
            for (String url : urlList) {
                System.out.println(url);
                replaceAll(builder, url, "https://www.ruv.is");
            }
        }
        System.out.println(builder);
    }

    public static void replaceAll(StringBuilder builder, String from, String to) {
        int index = builder.lastIndexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index = builder.lastIndexOf(from);
        }
    }
}
