package lv.miga.aiz;

public interface PostCodeService {
	
	static final String NOT_FOUND = "NAV_ATRASTS";
	
	String getPostCode(String settlementName, String parish);

}
