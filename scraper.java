import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class scraper {
    private String keyword;
    public scraper(String keyword){
        this.keyword=keyword;
    }

    /**
     * take the results for query 1 and prints
     * @param url
     * @throws IOException
     * @throws ParseException
     */
    public void getResults(String url) throws IOException, ParseException {

        JSONObject json=getJsonObject(url);
        JSONObject data = json.getJSONObject("pagination");
        int results = data.getInt("totalMatchedItems");
        System.out.println(results);
    }

    /**
     * get the required items from the json object like product,description,price
     * @param url
     * @param pageNumber
     * @throws IOException
     * @throws ParseException
     */
    public void getResults(String url, int pageNumber) throws IOException, ParseException {
        JSONObject json = getJsonObject(url);
        JSONArray items = json.getJSONArray("items");
        if (items.length() == 0) {
            System.out.println("No matching results found.");
        } else {
            System.out.println("Results for your search are:");
            for (int i = 0; i < items.length(); i++) {
                JSONObject obj = items.getJSONObject(i);
                System.out.println("Product " + (i + 1) + ".");
                System.out.println("name : " + obj.get("name"));
                System.out.println("Description : " + obj.get("shortDescription"));
                System.out.println("original Price : " + ((JSONObject) obj.get("price")).get("originalPrice"));
                System.out.println("selling Price : " + ((JSONObject) obj.get("price")).get("sellingPrice"));
            }
        }
    }

    /**
     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
     *          * up all the links on the page.
     * @param url
     * @return JSON Object
     * @throws IOException
     * @throws ParseException
     */

    private JSONObject getJsonObject(String url) throws IOException, ParseException {
        Document doc=(Document)Jsoup.connect(url).timeout(5000).userAgent("Mozilla"+"").get();
        Elements scripts = doc.getElementsByTag("script");
        Element element = scripts.get(0);
        String script = element.toString();
        String myJson = script.substring(29, script.length() - 10);
        return new JSONObject(myJson);

    }
}
