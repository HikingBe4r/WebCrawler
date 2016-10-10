import java.net.*;
import java.io.*;

public class WebCrawler_test {
	  public static void main(String[] args) {
		    try {
		      //http://www.hungryapp.co.kr/bbs/game_7knights_charDetail.php?no=427&page=1
		      String Url1 = "http://www.hungryapp.co.kr/bbs/game_7knights_charDetail.php?no=";
		      String Url2 = "&page=1";

		      String Url3 = Url1 + '1' + Url2;

		      URL url = new URL(Url3);

		      BufferedReader bf;
		      String line;
		      BufferedWriter txtCreater;
		      
		      //String [] types = {"UTF-8","EUC-KR","ISO-8859-1"};
		      

		      bf = new BufferedReader(new InputStreamReader(url.openStream()));
		      txtCreater = new BufferedWriter(new FileWriter("webcrawler_2.txt"));

		      while ((line=bf.readLine())!=null) {
		        System.out.println (URLEncoder.encode(line, "UTF-8"));
		        txtCreater.write(URLEncoder.encode(line, "UTF-8")); txtCreater.newLine();
		      }

		      bf.close();
		      txtCreater.close();

		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		    }
		    
	  }

}
