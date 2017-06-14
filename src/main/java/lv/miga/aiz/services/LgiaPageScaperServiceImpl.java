package lv.miga.aiz.services;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import lv.miga.aiz.services.ParishAdmCenterLinkService.Case;

public class LgiaPageScaperServiceImpl implements LgiaPageScaperService {

	@Override
	public Map<String, Object> getValueMapFromDocument(String id) {
		String url = LGIA_URL + id;
		Map<String, Object> map = new HashMap<>();
		map.put("url", url);
		map.put("date", new Date());

		try {
			Document doc = Jsoup.connect(url).get();
			if (doc.select("#drukat").isEmpty()) {
				throw new IllegalArgumentException();
			}

			String name = getValueFromTable(doc, "nosaukums adrešu klasifikatorā");
			if (name.equals("")) {
				name = getValueFromTable(doc, "pamatnosaukums");
			}
			map.put("name", name);
			map.put("type", getValueFromTable(doc, "objekta veids"));
			map.put("population", formatPopulationString(getValueFromTable(doc, "vērtība")));
			map.put("population_date", reformatDateString(getValueFromTable(doc, "apraksts")));

			ParishAdmCenterLinkService captialLinkService = new ParishAdmCenterLinkServiceImpl();
			String[] territ = getValueFromTerritTable(doc);
			map.put("parish", territ[0]);
			map.put("parish_gen", territ[0].replace("pagasts", "pagasta").replace("novads", "novada"));
			map.put("parish_loc", territ[0].replace("pagasts", "pagastā").replace("novads", "novadā"));
			map.put("municipality", territ[1]);
			map.put("municipality_gen", territ[1].replace("novads", "novada"));
			String admCenterName = getAdmCenterName(territ[1]);
			map.put("municipality_adm_center",
					captialLinkService.getFormattedCapitalWikilinkGenitive(admCenterName, admCenterName));
			map.put("district", territ[2]);
			map.put("district_loc", territ[2].replace("rajons", "rajonā"));

			String latitude = getValueFromTable(doc, "platums");
			String longitude = getValueFromTable(doc, "garums");

			Pattern p = Pattern.compile("^.*\\((\\d+)° (\\d+)' (\\d+)\"\\)$");
			Matcher m = p.matcher(latitude);
			if (m.matches()) {
				map.put("lat_deg", m.group(1));
				map.put("lat_min", m.group(2));
				map.put("lat_sec", m.group(3));
			}
			m = p.matcher(longitude);
			if (m.matches()) {
				map.put("lon_deg", m.group(1));
				map.put("lon_min", m.group(2));
				map.put("lon_sec", m.group(3));
			}

			p = Pattern.compile("^(\\d+\\.\\d+)\\(.*$");
			m = p.matcher(latitude);
			if (m.matches()) {
				map.put("lat", m.group(1));
			}
			m = p.matcher(longitude);
			if (m.matches()) {
				map.put("lon", m.group(1));
			}

			map.put("var_id", reformatDateString(getValueFromTable(doc, "#layertopoArisData", "kods")));

			map.put("parish_adm_center", captialLinkService.getFormattedWikilink(territ[0], Case.GENITIVE));
		} catch (HttpStatusException e) {
			map.put("message", "Kļūda, ielādējot URL: " + url);
		} catch (SocketTimeoutException e) {
			map.put("message", "Noildze, ielādējot URL: " + url + ", mēģiniet vēlreiz");
		} catch (IllegalArgumentException | IOException e) {
			map.put("message", "Kļūda, objekts nav atrasts, ID: " + id);
		}

		return map;
	}

	private String getAdmCenterName(String municipalityName) {
		return municipalityName.replace("u novads", "i").replace("s novads", "").replace(" novads", "");
	}

	private String[] getValueFromTerritTable(Document doc) {
		Elements el = doc.select("#layertopoTeritData #rezult tr td");
		String[] values = new String[] { NOT_FOUND, NOT_FOUND, NOT_FOUND };
		if (el.size() > 2) {
			values[0] = el.get(0).text().trim();
			values[1] = el.get(1).text().trim();
			values[2] = el.get(2).text().replace("agrāk", "").trim();
		}
		return values;
	}

	private String formatPopulationString(String value) {
		return value.replace(",0 iedz.", "");
	}

	private String getValueFromTable(Document doc, String string) {
		return getValueFromTable(doc, "", string);
	}

	private String getValueFromTable(Document doc, String marker, String string) {
		Elements el = doc.select("table.h100 td[style=vertical-align:top;] " + marker + " td:contains(" + string
				+ ":) + td.dati_skatit");
		if (!el.isEmpty()) {
			return el.get(0).text().replace('\u00A0', ' ').trim();
		}
		return NOT_FOUND;
	}

	private String reformatDateString(String value) {
		String returnString = value;
		Pattern datePattern = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.?$");
		Pattern yearPattern = Pattern.compile("^(\\d+)\\.$");

		Matcher m = datePattern.matcher(value);
		if (m.matches()) {
			returnString = "{{dat|" + m.group(3) + "|" + m.group(2) + "|" + m.group(1) + "||bez}}";
		} else {
			m = yearPattern.matcher(value);
			if (m.matches()) {
				returnString = "{{dat|" + m.group(1) + "||||bez}}";
			}
		}

		return returnString;
	}

}
