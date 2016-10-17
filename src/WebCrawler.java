import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;	// utf-8로 인코딩할때 썼는데, 저장할때 쓰려다보니 깨져서 나와서 안씀.
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;		// 앞뒤로 시간 기록할때 썼는데 지움.

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
	     
	     // 모든 영웅목록 반복문 실행. [161010] 현재 427개 있음.
	     while(i < 427 + 1){
			//  HTTP 주소 세팅
	    	Url3 = Url1 + i + Url2;
			HttpPost http = new HttpPost(Url3);
	
			// 가져오기를 실행할 클라이언트 객체 생성
			HttpClient httpClient = HttpClientBuilder.create().build();
	
			// 실행 및 실행 데이터를 Response 객체에 담음
			HttpResponse response = httpClient.execute(http);
	
			// Response 받은 데이터 중, DOM 데이터를 가져와 Entity에 담음
			HttpEntity entity = response.getEntity();
		   
			// 가져올 데이터 Charset 알아내기.
			ContentType contentType = ContentType.getOrDefault(entity);
			Charset charset = contentType.getCharset();
	        
			// 데이터 한줄씩 읽기.
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		    StringBuffer sb = new StringBuffer();
	
			// 데이터 가져와서 sb에 입력.
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
			// 데이터 출력. //이건 없어도 파일로저장할때 무방함.
			System.out.println(sb.toString());
		   
			// txt파일로 저장해보자
			BufferedWriter txtCreater;
		   
			String filename = "C:/Users/HikingBear/Documents/webcrawler/WebCrawler/etc/really/" + "webcrawler_" + i + ".txt";	// 절대경로 말고 상대경로로 하는방법 찾아보기.
		   
			txtCreater = new BufferedWriter(new FileWriter(filename));
			txtCreater.write(sb.toString()); txtCreater.newLine();
			txtCreater.close();
		   
			i++;
		}
	}

}