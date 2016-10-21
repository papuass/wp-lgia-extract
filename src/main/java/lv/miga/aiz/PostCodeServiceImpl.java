package lv.miga.aiz;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PostCodeServiceImpl implements PostCodeService {

	private static final String POSTCODESDB_URL = "http://www.postcodesdb.com/AlphabeticSearch.aspx?country=Latvia&city=";

	@Override
	public String getPostCode(String settlementName, String parish) {
		String code = null;
		try {
			Document doc = Jsoup.connect(POSTCODESDB_URL + settlementName).get();
			Elements el = doc.select("div.cityLetterContainer table td a");
			// only if one match found
			if (el.size() == 1) { 
				code = el.get(0).text().trim();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HttpStatusException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (code == null) {
			code = NOT_FOUND;
		}
		return code;
	}

}
