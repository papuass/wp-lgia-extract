package lv.miga.aiz;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lv.miga.aiz.pojo.Results;

public class PostCodeGoogleGeocodeServiceImpl implements PostCodeService {

	private static final String GOOGLE_API_KEY = "AIzaSyBJkgmtNNrWVMSWcs6t9ZPbLhTm8mbVQB0";
	private static final String NAME_PLACEHOLDER = "NAME_PLACEHOLDER";
	private static final String API_KEY = "API_KEY";
	private static final String GOOGLE_GEOCODE_URL = "https://maps.googleapis.com/maps/api/geocode/json?address="
			+ NAME_PLACEHOLDER + "&key=" + API_KEY;

	@Override
	public String getPostCode(String settlementName, String parish) {
		String code = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			URL url = new URL(GOOGLE_GEOCODE_URL.replace(API_KEY, GOOGLE_API_KEY).replace(NAME_PLACEHOLDER,
					URLEncoder.encode(settlementName + ", " + parish, "UTF-8")));

			Results data = mapper.readValue(url.openConnection().getInputStream(), Results.class);
			if ("OK".equals(data.status) && data.results.size() == 1) {
				code = data.results.get(0).addressComponents.stream()
						.filter(component -> component.types.contains("postal_code")).findAny().get().shortName;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (code == null) {
			code = NOT_FOUND;
		}
		return code;
	}

}
