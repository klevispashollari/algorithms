package scaffold;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		try {

			/* Teil A */

			BioBasicsParser bioBasicsParser = new BioBasicsParser();

			Path file = Paths.get("/home/ProgPrak2020/genom.txt");

			bioBasicsParser.loadEntireFile(file);
			bioBasicsParser.loadFileBuffered(file);

			List<String> info = bioBasicsParser.getInfoFor("Viral", 80, true);
			System.out.println(info);

			bioBasicsParser.loadWikiPage(new URL("https://de.wikipedia.org/wiki/Genom"));
			System.out.println(bioBasicsParser.getInfoFor("Viral", 80, false));

			/* Teil B1 */

			GenomeDownloader genomeDownloader = new GenomeDownloader();

			String id = genomeDownloader.parseGenomeId("escherichia", "coli");

			List<String> sequenceIds = genomeDownloader.parseSequenceId(id);

			Map<String, String> res = genomeDownloader.getSequence(sequenceIds.get(0));

			/* Teil B2 */

			GenomeParser genomeParser = new GenomeParser(res.get("seq"));

			String aminoAcid = genomeParser.getAminoAcid("AGC");
			System.out.println(aminoAcid);

			List<String> triplets = genomeParser.getCodon("X");
			System.out.println(triplets);

			GenomeParserOO genomeParserOO = new GenomeParserOO(res.get("seq"));

			Serine s = (Serine) genomeParserOO.getAminoAcid("AGC");
			System.out.println(s.getOneLetterName());

			List<String> codons = genomeParser.getCodons(res.get("seq"));
			System.out.println(codons);

			List<AminoAcid> aminoAcids = genomeParserOO.getCodons(res.get("seq"));
			System.out.println(aminoAcids);

			/* Teil B3 */

			List<String> codingRegions = genomeParserOO.getCodingRegions(aminoAcids);
			System.out.println(codingRegions);

			System.out.println(genomeParserOO.getLengthDistribution(codingRegions, 15, 20));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
