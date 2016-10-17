import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;	// utf-8�� ���ڵ��Ҷ� ��µ�, �����Ҷ� �����ٺ��� ������ ���ͼ� �Ⱦ�.
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;		// �յڷ� �ð� ����Ҷ� ��µ� ����.

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;

public class WebCrawler {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		 int i = 1;
		 String Url1 = "http://m.hungryapp.co.kr/bbs/game_7knights_charDetail.php?no=";
	     String Url2 = "&page=1";
	     String Url3;
	     
	     // ��� ������� �ݺ��� ����. [161010] ���� 427�� ����.
	     while(i < 427 + 1){
			//  HTTP �ּ� ����
	    	Url3 = Url1 + i + Url2;
			HttpPost http = new HttpPost(Url3);
	
			// �������⸦ ������ Ŭ���̾�Ʈ ��ü ����
			HttpClient httpClient = HttpClientBuilder.create().build();
	
			// ���� �� ���� �����͸� Response ��ü�� ����
			HttpResponse response = httpClient.execute(http);
	
			// Response ���� ������ ��, DOM �����͸� ������ Entity�� ����
			HttpEntity entity = response.getEntity();
		   
			// ������ ������ Charset �˾Ƴ���.
			ContentType contentType = ContentType.getOrDefault(entity);
			Charset charset = contentType.getCharset();
	        
			// ������ ���پ� �б�.
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		    StringBuffer sb = new StringBuffer();
	
			// ������ �����ͼ� sb�� �Է�.
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
			// ������ ���. //�̰� ��� ���Ϸ������Ҷ� ������.
			System.out.println(sb.toString());
		   
			// txt���Ϸ� �����غ���
			BufferedWriter txtCreater;
		   
			String filename = "C:/Users/HikingBear/Documents/webcrawler/WebCrawler/etc/really/" + "webcrawler_" + i + ".txt";	// ������ ���� ����η� �ϴ¹�� ã�ƺ���.
		   
			txtCreater = new BufferedWriter(new FileWriter(filename));
			txtCreater.write(sb.toString()); txtCreater.newLine();
			txtCreater.close();
		   
			i++;
		}
	}

}