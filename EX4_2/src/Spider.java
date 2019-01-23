import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

public class Spider {
    ArrayList<String> links = new ArrayList<>();
    ArrayList<String> emails = new ArrayList<>();
    HashMap<String, Boolean> websiteList = new HashMap<>();

    public Spider(){

        // Starts the address set to cnn.com
        String firstAddress = "https://www.cnn.com/";
        links.add(firstAddress);
        websiteList.put(firstAddress, false);
        boolean run = true;

        // Runs the spider search for 100 searches
        int i = 0;
        while(run){
            if(i > 100){
                run = false;
            }else{
                search(links.get(i));
                websiteList.replace(links.get(i), true);
                i++;
            }
        }
    }

    public void search(String address){

        try {

            // Sets up the line reader
            URL url = new URL(address);
            BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            // Reads through 50 lines in the website
            int i=50;
            while ((line = rdr.readLine()) != null && i > 0) {

                //  Searches for website links
                Pattern webLink = Pattern.compile("<a\\s*?href=\"(http:.*?)\"\n");
                Matcher webMatcher = webLink.matcher(line);
                if (webMatcher.find()) {
                    links.add(webMatcher.group(1));
                }

                // Searches for email addresses
                Pattern emailAddress = Pattern.compile("\"mailto:(.*?)\"");
                Matcher emailMatcher = emailAddress.matcher(line);
                if (emailMatcher.find()) {
                    emails.add(emailMatcher.group(1));
                }

                //
                String[] link = line.split("href=");
                for(String p: link) {
                    for(int j = 1; j < p.length(); j++){
                        if(p.charAt(j) == 34){
                            if (p.charAt(1) == 'h' && p.charAt(4) == 'p') {
                                String website = p.substring(1, j);
                                links.add(p.substring(1, j));
                                websiteList.put(p.substring(1, j), false);
                                break;
                            }
                        }
                    }
                }
                i--;
            }
        }
        catch (Exception ex) {
            System.out.println("Exception: " +  ex.getMessage());
        }


    }

    // Lists out all of the website links in the Arraylist
    public void listLinks(){
        System.out.println("Websites: ");
        for(String p: links){
            System.out.println(p);
        }
    }

    // Lists out all of the emails in the ArrayList
    public void listEmails(){
        if(emails.size() > 0) {             // The email ArrayList is empty most of the time
            System.out.println("Emails: ");
            for (String p : emails) {
                System.out.println(p);
            }
        }
    }

    public static void main(String[] args) {
        Spider spider = new Spider();
        spider.listEmails();
        spider.listLinks();
    }

}
