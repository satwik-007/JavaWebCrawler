
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class webscraper {

    /**
     * url is attached with the keyword given by the user
     * code executed as per query choice
     * @param args
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args)  throws IOException, ParseException {
        String url = "https://www.shopping.com/search.html?keyword=";
        String keyword;
        int pageNumber=-1;
        int choice;
        System.out.println("Query 1:");
        System.out.println("Query 2");

        Scanner scanner=new Scanner(System.in);
        //choice taken fro user
        choice=scanner.nextInt();

        scanner.nextLine();

        switch (choice) {
            //gives all the results for a keyword
            case 1:
                System.out.println("Enter keyword");
                keyword = scanner.nextLine();
                url += keyword;
                scraper crawler=new scraper(keyword);
                crawler.getResults(url);
                break;
                // gives all the results for a keyword in a page
            case 2:
                System.out.println("Enter keyword:");
                keyword = scanner.nextLine();
                System.out.println("Enter page number:");
                pageNumber = scanner.nextInt();
                scanner.nextLine();
                url+=keyword+"&page="+pageNumber;
                scraper crawler1 = new scraper(keyword);
                crawler1.getResults(url,pageNumber);
                break;
        }

    }
}
