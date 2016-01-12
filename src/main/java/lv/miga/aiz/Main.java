package lv.miga.aiz;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class Main {
	private static final String LGIA_URL = "http://vietvardi.lgia.gov.lv/vv/to_www_obj.objekts?p_id=";
	private static final String NOT_FOUND = "NAV_ATRASTS";

	public static void main(String[] args) {
		staticFileLocation("/public");

		get("/lgia-extract", (req, res) -> {
			Map<String, Object> map = new HashMap<>();

			String id = req.queryParams("id");
			if (id != null) {
				String url = LGIA_URL + id;

				try {
					Document doc = Jsoup.connect(url).get();
					if (doc.select("#drukat").isEmpty()) {
						throw new IllegalArgumentException();
					}

					map.put("id", id);
					map.put("url", url);

					fillValueMapFromDocument(map, doc);
				} catch (IllegalArgumentException e) {
					map.put("message", "Kïûda, objekts nav atrasts, ID: " + id);
				} catch (HttpStatusException e) {
					map.put("message", "Kïûda, ielâdçjot URL: " + url);
				}

			}

			return new ModelAndView(map, "mainPage.ftl");
		}, new FreeMarkerEngine());
	}

	private static void fillValueMapFromDocument(Map<String, Object> map, Document doc) {
		map.put("date", new Date());

		map.put("name", getValueFromTable(doc, "nosaukums adreðu klasifikatorâ"));
		map.put("type", getValueFromTable(doc, "objekta veids"));
		map.put("population", formatPopulationString(getValueFromTable(doc, "vçrtîba")));
		map.put("populationYear", getValueFromTable(doc, "apraksts"));

		String[] territ = getValueFromTerritTable(doc);
		map.put("parish", territ[0]);
		map.put("parish_gen", territ[0].replace("pagasts", "pagasta").replace("novads", "novada"));
		map.put("parish_loc", territ[0].replace("pagasts", "pagastâ").replace("novads", "novadâ"));
		map.put("municipality", territ[1]);
		map.put("municipality_gen", territ[1].replace("novads", "novada"));
		map.put("district", territ[2]);
		map.put("district_loc", territ[2].replace("rajons", "rajonâ"));

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

		// TODO viensçtas???
	}

	private static String[] getValueFromTerritTable(Document doc) {
		Elements el = doc.select("#layertopoTeritData #rezult tr td");
		String[] values = new String[] { NOT_FOUND, NOT_FOUND, NOT_FOUND };
		if (el.size() > 2) {
			values[0] = el.get(0).text().trim();
			values[1] = el.get(1).text().trim();
			values[2] = el.get(2).text().replace("agrâk", "").trim();
		}
		return values;
	}

	private static String formatPopulationString(String value) {
		return value.replace(",0 iedz.", "");
	}

	private static String getValueFromTable(Document doc, String string) {
		Elements el = doc.select("table.h100 td[style=vertical-align:top;] td:contains(" + string
				+ ":) + td.dati_skatit");
		if (!el.isEmpty()) {
			return el.get(0).text().trim();
		}
		return NOT_FOUND;
	}

}