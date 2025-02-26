package is.webworks.link_service.service;

import is.webworks.link_service.dao.UrlDao;
import is.webworks.link_service.model.EmailContent;
import is.webworks.link_service.model.EmailContentResponse;
import is.webworks.link_service.model.Url;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LinkService {

    @Autowired
    UrlDao urlDao;

    @Autowired
    Environment env;

    private static final Pattern htmlPattern = Pattern.compile(".*\\<[^>]+>.*", Pattern.DOTALL);
    private static final Pattern linkPattern = Pattern.compile("\\b((?:https?):\\/\\/[-a-zA-Z0-9+&@#\\/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#\\/%=~_|])", Pattern.CASE_INSENSITIVE);

    public ResponseEntity<EmailContentResponse> transformMessage(EmailContent content){

        if(htmlPattern.matcher(content.getEmailContent()).matches()) {
            return transformHtml(content);
        } else {
            return transformString(content);
        }
    }

    public ResponseEntity<EmailContentResponse> transformHtml(EmailContent content){
        Document doc = Jsoup.parse(content.getEmailContent());
        Elements links = doc.select("a[href]");
        for(Element link : links){
            System.out.println(link.attr("href"));
            String originalLink = link.attr("abs:href");
            if(originalLink.startsWith("https://") || originalLink.startsWith("http://")){
                UUID id = addUrl(originalLink, content.getExpiryDate());
                link.attr("href", env.getProperty("redirect.url") + id.toString());
            }
            System.out.println(link.attr("abs:href"));
        }

        EmailContentResponse response = new EmailContentResponse("UUID", doc.body().html());

        System.out.println(doc.body().html());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<EmailContentResponse> transformString(EmailContent content){
        Matcher matcher = linkPattern.matcher(content.getEmailContent());

        ArrayList<String> urlList = new ArrayList<>();
        while (matcher.find()) {
            urlList.add(matcher.group());
        }

        StringBuilder builder = new StringBuilder(content.getEmailContent());
        if(urlList.isEmpty()){
            System.out.println("Empty");
        } else {
            for (String url : urlList) {
                System.out.println(url);
                UUID id = addUrl(url, content.getExpiryDate());
                replaceAll(builder, url, env.getProperty("redirect.url") + id.toString());
            }
        }
        System.out.println(builder);
        EmailContentResponse response = new EmailContentResponse("UUID", builder.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private static void replaceAll(StringBuilder builder, String from, String to) {
        int index = builder.lastIndexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index = builder.lastIndexOf(from);
        }
    }

    private UUID addUrl(String url, Date expiry){
        Url savedUrl = new Url();
        savedUrl.setMessageUrl(url);
        savedUrl.setExpiryDate(expiry);
        savedUrl.setCreatedDate(new Date());

        savedUrl = urlDao.save(savedUrl);
        return savedUrl.getId();
    }
}
