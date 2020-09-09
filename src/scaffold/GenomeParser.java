package scaffold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenomeParser {

	private final String genome;
	private Map<String, String> codonWheel;
	private Map<String, List<String>> aminoAcidWheel;

	public GenomeParser(String genome) {
		this.genome = genome;
		this.codonWheel = this.initCodonWheel();
		this.aminoAcidWheel = this.initAminoAcidWheel();
	}

	/**
	 * Returns the codons (such as "ATG") from the given sequence.
	 * 
	 * @param seq The sequence which shall return all codons.
	 * @return A list with all codons.
	 */
	public List<String> getCodons(String seq) {
		List<String> codons = new ArrayList<>();
		// Todo
		return codons;
	}

	/**
	 * Returns the respective amino acid for a given triplet (a codon such as
	 * "TTA").
	 * 
	 * @param triplet The query codon, such as "TTA".
	 * @return The amino acid.
	 */
	public String getAminoAcid(String triplet) {
		// TODO
		return "";
	}

	/**
	 * Returns all codons for the given amino acid.
	 * 
	 * @param aminoAcid The amino acid.
	 * @return A list with all possible codons of the query amino acid.
	 */
	public List<String> getCodon(String aminoAcid) {
		// TODO
		return null;
	}

	/**
	 * Initializes the amino acid wheel, which maps amino acids to codons.
	 * 
	 * @return The initialized amino acid wheel.
	 */
	private Map<String, List<String>> initAminoAcidWheel() {
		Map<String, List<String>> aaW = new HashMap<>();
		// TODO
		return aaW;
	}

	/**
	 * Initializes the codon wheel, which maps codons to the respective amino acid.
	 * 
	 * @return The initialized codon wheel.
	 */
	private Map<String, String> initCodonWheel() {
		Map<String, String> cw = new HashMap<>();
		// TODO
		return cw;
	}

}
