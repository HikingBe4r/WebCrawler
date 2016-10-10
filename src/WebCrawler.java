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
			// 2. 가져올 HTTP 주소 세팅
			Url3 = Url1 + i + Url2;
		   HttpPost http = new HttpPost(Url3);
	
		   // 3. 가져오기를 실행할 클라이언트 객체 생성
		   HttpClient httpClient = HttpClientBuilder.create().build();
	
		   // 4. 실행 및 실행 데이터를 Response 객체에 담음
		   HttpResponse response = httpClient.execute(http);
	
		   // 5. Response 받은 데이터 중, DOM 데이터를 가져와 Entity에 담음
		   HttpEntity entity = response.getEntity();
		   
		   // 6. Charset을 알아내기 위해 DOM의 컨텐트 타입을 가져와 담고 Charset을 가져옴 
		   ContentType contentType = ContentType.getOrDefault(entity);
	       Charset charset = contentType.getCharset();
	        
	       // 7. DOM 데이터를 한 줄씩 읽기 위해 Reader에 담음 (InputStream / Buffered 중 선택은 개인취향) 
		   BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		   
	
		   // 8. 가져온 DOM 데이터를 담기위한 그릇
		   StringBuffer sb = new StringBuffer();
	
		   // 9. DOM 데이터 가져오기
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
		   // 10. 가져온 아름다운 DOM을 보자
		   System.out.println(sb.toString());
		   
		   // ** txt파일로 저장해보자
		   BufferedWriter txtCreater;
		   
		   String filename = "C:/tmp//etc/really/"+ "webcrawler_" + i + ".txt";
		   
		   
		   txtCreater = new BufferedWriter(new FileWriter(filename));
		   txtCreater.write(sb.toString()); txtCreater.newLine();
		   txtCreater.close();
		   
		   i++;
		}
	}

}