import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.GenericArrayType;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WebCrawler {

	public static String getCurrentData(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sdf.format(new Date());
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		int i = 1;
		 String Url1 = "http://m.hungryapp.co.kr/bbs/game_7knights_charDetail.php?no=";
	     String Url2 = "&page=1";
	     String Url3;
		while(i < 2){
			// 2. ������ HTTP �ּ� ����
			Url3 = Url1 + i + Url2;
		   HttpPost http = new HttpPost(Url3);
	
		   // 3. �������⸦ ������ Ŭ���̾�Ʈ ��ü ����
		   HttpClient httpClient = HttpClientBuilder.create().build();
	
		   // 4. ���� �� ���� �����͸� Response ��ü�� ����
		   HttpResponse response = httpClient.execute(http);
	
		   // 5. Response ���� ������ ��, DOM �����͸� ������ Entity�� ����
		   HttpEntity entity = response.getEntity();
		   
		   // 6. Charset�� �˾Ƴ��� ���� DOM�� ����Ʈ Ÿ���� ������ ��� Charset�� ������ 
		   ContentType contentType = ContentType.getOrDefault(entity);
	       Charset charset = contentType.getCharset();
	        
	       // 7. DOM �����͸� �� �پ� �б� ���� Reader�� ���� (InputStream / Buffered �� ������ ��������) 
		   BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		   
	
		   // 8. ������ DOM �����͸� ������� �׸�
		   StringBuffer sb = new StringBuffer();
	
		   // 9. DOM ������ ��������
		   String line = "";
		   int count = 0;
		   while((line=br.readLine()) != null){
			   if (count > 1250 && count < 1387){
				   sb.append(line+"\n");   
			   } else if(count > 1500){
				   break;			   
			   }
			   count++;
		   }
		   // 10. ������ �Ƹ��ٿ� DOM�� ����
		   System.out.println(sb.toString());
		   
		   // ** txt���Ϸ� �����غ���
		   BufferedWriter txtCreater;
		   
		   String filename = "C:/tmp//etc/really/"+ "webcrawler_" + i + ".txt";
		   
		   
		   txtCreater = new BufferedWriter(new FileWriter(filename));
		   txtCreater.write(sb.toString()); txtCreater.newLine();
		   txtCreater.close();
		   
		   i++;
		}
	}

}