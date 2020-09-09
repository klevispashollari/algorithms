package scaffold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
            // TODO
        }
        in.close();
        return id;
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
            // TODO
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
    public String getSequence(String id) throws IOException {
        URL obj = new URL("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=nuccore&id=38638184&rettype=fasta&retmode=text");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        InputStreamReader urlStream = new InputStreamReader(obj.openStream());
        BufferedReader in = new BufferedReader(urlStream);
        String inputLine;
        String res = "";
        while ((inputLine = in.readLine()) != null) {
             System.out.println(inputLine);
            // TODO
        }
        in.close();
        return res;
    }
}
