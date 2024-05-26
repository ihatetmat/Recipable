package capstone.recipable.domain.ingredient.service;

import capstone.recipable.domain.ingredient.controller.dto.response.IngredientListResponse;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Slf4j
@Component
public class NaverOcrService {
    @Value("${naver.service.url}")
    private String url;

    @Value("${naver.service.secretKey}")
    private String secretKey;


    public List<String> callApi(MultipartFile multipartFile) {
        String apiURL = url;
        List<String> parseData = null;

        log.info("callApi Start!");
        Map<String, String> mimeToExt = new HashMap<>();
        mimeToExt.put("image/jpeg", "jpg");
        mimeToExt.put("image/png", "png");
        mimeToExt.put("image/gif", "gif");

        String contentType = multipartFile.getContentType();
        String ext = mimeToExt.getOrDefault(contentType, "jpg");

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", ext);
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.add(image);
            json.put("images", images);
            String postParams = json.toString();

            con.connect();
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                writeMultiPart(wr, postParams, multipartFile, boundary);
            }

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            parseData = jsonParse(response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return parseData;
    }

    private static void writeMultiPart(OutputStream out, String jsonMessage, MultipartFile multipartFile, String boundary) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");

        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();

        if (multipartFile != null && !multipartFile.isEmpty()) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
                    .append(multipartFile.getOriginalFilename()).append("\"\r\n");
            fileString.append("Content-Type: ").append(multipartFile.getContentType()).append("\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();

            try (InputStream fis = multipartFile.getInputStream()) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                out.write("\r\n".getBytes());
            }

            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        out.flush();
    }

    private List<String> jsonParse(StringBuffer response) throws ParseException {
        // json 파싱
        JSONParser parser = new JSONParser();
        JSONObject responseObject = (JSONObject) parser.parse(response.toString());
        JSONArray imagesArray = (JSONArray) responseObject.get("images");
        List<String> productNames = new ArrayList<>();
        for (Object imageObj : imagesArray) {
            JSONObject image = (JSONObject) imageObj;
            JSONObject receipt = (JSONObject) image.get("receipt");
            JSONObject result = (JSONObject) receipt.get("result");
            JSONArray subResults = (JSONArray) result.get("subResults");
            for (Object subResultObj : subResults) {
                JSONObject subResult = (JSONObject) subResultObj;
                JSONArray items = (JSONArray) subResult.get("items");
                for (Object itemObj : items) {
                    JSONObject item = (JSONObject) itemObj;
                    JSONObject name = (JSONObject) item.get("name");
                    String text = (String) name.get("text");
                    productNames.add(text);
                }
            }
        }
        return productNames;
    }
}