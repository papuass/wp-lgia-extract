package lv.miga.aiz;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import lv.miga.aiz.services.LgiaPageScaperService;
import lv.miga.aiz.services.LgiaPageScaperServiceImpl;
import lv.miga.aiz.services.PostCodeGoogleGeocodeServiceImpl;
import lv.miga.aiz.services.PostCodeService;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class Main {

	public static void main(String[] args) {
		staticFileLocation("/public");

		get("/lgia-extract", (req, res) -> {
			Map<String, Object> map = new HashMap<>();

			String id = req.queryParams("id");
			if (id != null) {
				map.put("id", id);
				map.put("otherUses", req.queryParams("otheruses"));

				LgiaPageScaperService psService = new LgiaPageScaperServiceImpl();
				map.putAll(psService.getValueMapFromDocument(id));

				String name = (String) map.get("name");
				String parish = (String) map.get("parish");
				if (name != null) {
					PostCodeService postCodeService = new PostCodeGoogleGeocodeServiceImpl();
					map.put("post_code", postCodeService.getPostCode(name, parish));
				}
			}

			return new ModelAndView(map, "mainPage.ftl");
		}, new FreeMarkerEngine());
	}

}