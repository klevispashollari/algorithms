package scaffold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BioBasicsParser {

	private List<String> content;
	private List<String> wikiContent;

	/**
	 * Load the whole file into memory.
	 * 
	 * @param file A path object to the actual file.
	 * @throws IOException
	 */
	public void loadEntireFile(Path file) throws IOException {
		// TODO
		this.content = null;
	}

	/**
	 * Read a file line by line. Recommended way for large files.
	 * 
	 * @param file A path object to the actual file
	 * @throws IOException
	 */
	public void loadFileBuffered(Path file) throws IOException {
		// TODO
		this.content = null;
	}

	/**
	 * Retrieves an entire page and saves it in a string.
	 * 
	 * @param url An URL object to the web page.
	 * @throws IOException
	 */
	public void loadWikiPage(URL url) throws IOException {
		InputStreamReader urlStream = new InputStreamReader(url.openStream());
		BufferedReader in = new BufferedReader(urlStream);
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			// TODO
		}
		in.close();
		this.wikiContent = null;
	}

	/**
	 * Accepts a keyword and returns this words subsequent content.
	 * 
	 * @param keyword The word to look for.
	 * @param length  The length of the subsequent content
	 * @param useFile Use a file as source or a web page. If false, web page needs
	 *                to be present.
	 * @return Returns the specified keywords subsequent content.
	 */
	public List<String> getInfoFor(String keyword, int length, boolean useFile) {
		List<String> source = useFile ? this.content : this.wikiContent;
		List<String> hits = new ArrayList<>();
		// TODO
		return hits;
	}

	/**
	 * Takes a string and replaces all HTML tags.
	 * 
	 * @param tag AN HTML tag, such as '&#60;button&#62;Klick
	 *            mich!&#60;/button&#62;'.
	 * @return The purified string.
	 */
	public String replaceTags(String tag) {
		// TODO
		return tag;
	}

}
