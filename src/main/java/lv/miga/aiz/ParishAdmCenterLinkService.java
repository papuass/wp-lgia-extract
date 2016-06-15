package lv.miga.aiz;

public interface ParishAdmCenterLinkService {

	enum Case {
		NOMINATIVE, GENITIVE
	}

	String getTitle(String name);

	String getFormattedWikilink(String name, Case grammarCase);

}
