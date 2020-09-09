package scaffold_gled.agBioinformatik.aufgabe2;

import scaffold_gled.agBioinformatik.aminoacid.AminoAcid;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenomeParserOO {

    private final String genome;
    private Map<String, AminoAcid> codonWheel;

    public GenomeParserOO(String genome) {
        this.genome = genome;
        this.codonWheel = this.initCodonWheel();
    }

    /**
     * Finds all coding regions within the given list of amino acid objects.
     * @param aminoAcids A list of amino acid objects.
     * @return A list of all found coding regions.
     */
    public List<String> getCodingRegions(List<AminoAcid> aminoAcids) {
        List<String> codingRegions = new ArrayList<>();
        // TODO
        return codingRegions;
    }

    /**
     * Returns the codons (such as "ATG") from the given sequence.
     * @param seq The sequence which shall return all codons.
     * @return A list with all codons.
     */
    public List<AminoAcid> getCodons(String seq) {
        List<AminoAcid> codons = new ArrayList<>();
        // TODO
        return codons;
    }

    /**
     * Returns the respective amino acid object for a given triplet (a codon such as "TTA").
     * @param triplet The query codon, such as "TTA".
     * @return The amino acid.
     */
    public AminoAcid getAminoAcid(String triplet) {
        // TODO 
        return null;
    }

    /**
     * Initializes the codon wheel, which maps codons to the respective amino acid object.
     * @return The initialized codon wheel.
     */
    private Map<String, AminoAcid> initCodonWheel() {
        Map<String, AminoAcid> cw = new HashMap<>();
        // TODO
        return cw;
    }

    public String getLengthDistribution(List<String> codingRegions, int bins, int width) {

        ArrayList<ArrayList<Integer>> collect = IntStream.rangeClosed(0, bins * width)
                .filter(x -> x % width == 0)
                .skip(1)
                .collect(ArrayList::new, (oldList, e) -> {
                    ArrayList<Integer> r = new ArrayList<>();
                    r.add(oldList.size() == 0 ? 0 : oldList.get(oldList.size() - 1).get(1));
                    r.add(e);
                    oldList.add(r);
                }, ArrayList::addAll);

        Map<String, String> tmp = new LinkedHashMap<>();
        collect.forEach(t -> {
           int s = codingRegions.stream()
                   .filter(cr -> cr.length() >= t.get(0) && cr.length() < t.get(1))
                   .collect(Collectors.toList())
                   .size();
           tmp.put(
                   String.format("[%s %s)", t.get(0), t.get(1)),
                   String.join("", Collections.nCopies(s, "*"))
           );
        });

        return tmp.entrySet().stream()
                .map(entry -> String.format("%s %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
