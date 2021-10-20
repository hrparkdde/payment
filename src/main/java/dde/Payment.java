package dde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Payment {
    
    public static final String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken";

    public static final String KEY = "1414301817795258";
    public static final String SECRET = "1be635394af547c74577eaa25656fe894b573a868fded5001c58d254ae362678580ee75faf1c3ccd";

    // 아임포트 인증(토큰)을 받아주는 함수
    public String getImportToken() {
        String result = "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(IMPORT_TOKEN_URL);
        Map<String,String> m = new HashMap<String,String>();
        m.put("imp_key", KEY);
        m.put("imp_secret", SECRET);

        try {
            post.setEntity(new UrlEncodedFormEntity(convertParameter(m)));
            HttpResponse res = client.execute(post);
            ObjectMapper mapper = new ObjectMapper();
            String body = EntityUtils.toString(res.getEntity());
            JsonNode rootNode = mapper.readTree(body);
            JsonNode resNode = rootNode.get("response");
            result = resNode.get("access_token").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Map을 사용해서 Http요청 파라미터를 만들어 주는 함수
    private List<NameValuePair> convertParameter( Map<String,String> paramMap )  {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        Set<Entry<String,String>> entries = paramMap.entrySet();
        for(Entry<String,String> entry : entries) {
            paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return paramList;
    }
}
