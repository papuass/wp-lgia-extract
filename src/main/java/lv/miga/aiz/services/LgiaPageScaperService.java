package lv.miga.aiz.services;

import java.util.Map;

public interface LgiaPageScaperService {
	
	static final String NOT_FOUND = "NAV_ATRASTS";
	
	static final String LGIA_URL = "http://vietvardi.lgia.gov.lv/vv/to_www_obj.objekts?p_id=";
	
	Map<String, Object> getValueMapFromDocument(String id);

}
