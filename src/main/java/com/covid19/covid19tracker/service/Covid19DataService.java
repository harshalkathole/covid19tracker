package com.covid19.covid19tracker.service;

import com.covid19.covid19tracker.model.RegionWiseStatistics;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class Covid19DataService {
    private static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/" +
            "csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<RegionWiseStatistics> allRegionStatisticsList = new ArrayList<>();

    @PostConstruct
//    @Scheduled(cron = "* * 1 * * *")
    public void getCovidVirusData() throws IOException {
        List<RegionWiseStatistics> newRegionStatisticsList = new ArrayList<>();
        URL url = new URL(COVID_DATA_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            StringReader csvBodyReader = new StringReader(response.toString());
            Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
            for (CSVRecord csvRecord : csvRecords) {
                RegionWiseStatistics regionWiseStatistics = new RegionWiseStatistics();
                regionWiseStatistics.setState(csvRecord.get("Province/State"));
                regionWiseStatistics.setCountry(csvRecord.get("Country/Region"));
                int latestCases = Integer.parseInt(csvRecord.get(csvRecord.size() - 1));
                regionWiseStatistics.setTotalActiveCases(latestCases);
                newRegionStatisticsList.add(regionWiseStatistics);
                System.out.println(regionWiseStatistics.toString());
            }
            this.allRegionStatisticsList = newRegionStatisticsList;

            bufferedReader.close();
        } else {
            System.out.println("Failure");
        }
    }
}
