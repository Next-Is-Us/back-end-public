package com.nextisus.project.util.loder;

import com.nextisus.project.domain.Hospital;
import com.nextisus.project.repository.HospitalRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Component
public class ApiDataLoader implements CommandLineRunner {

    private final HospitalRepository hospitalRepository;

    public ApiDataLoader(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        String serviceKey = "o3EWDRotdgYjzHFQ8iYGsXMaC43KutGQFNvAXJGKLQe7aPyiXdNJUplheNRqzASQC2W2GwdXXTyjCl8yw%2BGOig%3D%3D";
        int totalCount = 76470;
        int pageNo = 1;
        int numOfRows = 1000;
        int totalPages = (int) Math.ceil((double) totalCount / numOfRows);

        //for (int pageNo = 1; pageNo <= 100; pageNo++) {
            String apiURL = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncFullDown?ServiceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows;
            try {
                URL url = new URL(apiURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode());

                BufferedReader rd;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                conn.disconnect();

                String xmlResponse = sb.toString();

                // XML 파싱 및 데이터 추출
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(new InputSource(new StringReader(xmlResponse)));

                    doc.getDocumentElement().normalize();

                    NodeList itemList = doc.getElementsByTagName("item");

                    for (int i = 0; i < itemList.getLength(); i++) {
                        Element item = (Element) itemList.item(i);

                        String dutyAddr = getTagValue("dutyAddr", item);
                        String dutyName = getTagValue("dutyName", item);
                        String dutyTel = getTagValue("dutyTel1", item);

                        Hospital hospital = Hospital.builder()
                                .hospitalName(dutyName)
                                .hospitalAddress(dutyAddr)
                                .hospitalTel(dutyTel)
                                .build();
                        hospitalRepository.save(hospital);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
       // }
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        return (nodeList != null && nodeList.getLength() > 0) ? nodeList.item(0).getNodeValue() : "N/A";
    }
}
