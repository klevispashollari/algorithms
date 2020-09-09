package scaffold.agBioinformatik.aufgabe2;


import scaffold.agBioinformatik.aufgabe1.BioBasicsParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenomeDownloader {

    private final BioBasicsParser bioBasicsParser = new BioBasicsParser();
    private final String baseUrl = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";

    /**
     * Returns the NCBI genome ID, given a specific query string.
     * @param searchStrings The query terms. Will be concatenated with '+'.
     *                      Example: ["escherichia", "coli"] -> "escherichia+coli"
     * @return The genome ID.
     * @throws IOException
     */
    public String parseGenomeId(String... searchStrings) throws IOException {
        URL obj = new URL(baseUrl +
                String.format(
                        "esearch.fcgi?db=genome&term=%s",
                        String.join("+", searchStrings)));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        InputStreamReader urlStream = new InputStreamReader(obj.openStream());
        BufferedReader in = new BufferedReader(urlStream);
        String inputLine;
        String id = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            if(inputLine.contains("<Id>")){
                id = getTagValue(inputLine,"Id");
            }
        }
        in.close();
        return id;
    }
    public static String getTagValue(String xml, String tagName){
        return xml.split("<"+tagName+">")[1].split("</"+tagName+">")[0];
    }

    /**
     * Returns the NCBI sequence ID for the given genome ID.
     * @param id The genome ID.
     * @return The sequence ID.
     * @throws IOException
     */
    public List<String> parseSequenceId(String id) throws IOException {
        URL obj = new URL(baseUrl +
                String.format("elink.fcgi?dbfrom=genome&db=nuccore&id=%s&term=srcdb+refseq[prop]", id));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        InputStreamReader urlStream = new InputStreamReader(obj.openStream());
        BufferedReader in = new BufferedReader(urlStream);
        List<String> sequenceIds = new ArrayList<>();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            if(inputLine.contains("<Id>")){
                sequenceIds.add(getTagValue(inputLine,"Id"));
            }
        }
        in.close();
        return sequenceIds;
    }

    /**
     * Gets the actual sequence for a given sequence ID.
     * @param id The sequence ID.
     * @return A map with actual sequence ("seq") and the meta information ("info")
     * @throws IOException
     */
    public Map<String,String> getSequence(String id) throws IOException {
        URL obj = new URL("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=nuccore&id=38638184&rettype=fasta&retmode=text");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        InputStreamReader urlStream = new InputStreamReader(obj.openStream());
        BufferedReader in = new BufferedReader(urlStream);
        String inputLine;
        StringBuilder sb = new StringBuilder();
        Map<String,String> map = new HashMap<>();
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.startsWith(">")) {
                System.out.println("Comment line: "+inputLine);
                map.put("infp",inputLine);
            }else {
                System.out.println(inputLine);
                sb.append(inputLine);
            }
        }
        in.close();
        map.put("seq",sb.toString());
        return map;
    }
}
