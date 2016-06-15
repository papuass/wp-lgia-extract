package lv.miga.aiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ParishAdmCenterLinkServiceImpl implements ParishAdmCenterLinkService {

	static Map<String, Village> capitals;

	ParishAdmCenterLinkServiceImpl() {
		if (capitals == null) {
			capitals = new HashMap<>();
			try {
				BufferedReader tsvFile = new BufferedReader(new FileReader(
						System.getProperty("user.dir") + "/src/main/resources/data/parish_adm_centers.tsv"));

				String dataRow = tsvFile.readLine();

				while (dataRow != null) {
					String[] dataArray = dataRow.split("\\t");
					Village village = new Village(dataArray[1], dataArray[2]);
					capitals.put(dataArray[0], village);

					dataRow = tsvFile.readLine();
				}
				tsvFile.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getTitle(String name) {
		return capitals.get(name).title;
	};

	@Override
	public String getFormattedWikilink(String name, Case grammarCase) {
		Village village = capitals.get(name);
		if (grammarCase == Case.NOMINATIVE) {
			return getFormattedCapitalWikilinkNominative(village.articleTitle, village.title);
		} else if (grammarCase == Case.GENITIVE) {
			return getFormattedCapitalWikilinkGenitive(village.articleTitle, village.title);
		}
		throw new NotImplementedException();
	}

	String getFormattedCapitalWikilinkGenitive(String articleTitle, String title) {

		// Quick & Dirty
		if (title.endsWith("i")) { // Ērgļi
			title += "em";
		} else if (title.endsWith("es")) { // Dekšāres
			title = title.substring(0, title.length() - 2) + "ēm";
		} else if (title.endsWith("as")) { // Mandegas
			title = title.substring(0, title.length() - 2) + "ām";
		} else if (title.endsWith("pils")) { // Bērzpils
			// do nothing
		} else if (title.endsWith("tis")) { // Gulbītis ??
			title = title.substring(0, title.length() - 3) + "ša";
		} else if (title.endsWith("is")) { // Kolberģis			
			title = title.substring(0, title.length() - 2) + "a";
		} else if (title.endsWith("s")) { // Naudaskalns, Briežuciems
			title = title.substring(0, title.length() - 1) + "a";
		} else {
			title += "s";
		}

		return formatWikiLink(articleTitle, title);
	}

	String getFormattedCapitalWikilinkNominative(String articleTitle, String title) {
		return formatWikiLink(articleTitle, title);
	}

	static String formatWikiLink(String articleTitle, String title) {
		if (title.startsWith(articleTitle)) {
			return "[[" + articleTitle + "]]" + title.substring(articleTitle.length());
		} else {
			return "[[" + articleTitle + "|" + title + "]]";
		}
	}

	class Village {

		String title;
		String articleTitle;

		Village(String title, String articleTitle) {
			this.title = title;
			this.articleTitle = articleTitle;
		}
	}

}
