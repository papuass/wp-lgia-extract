package lv.miga.aiz.services;

public interface ParishAdmCenterLinkService {

	enum Case {
		NOMINATIVE, GENITIVE
	}

	String getTitle(String name);

	String getFormattedWikilink(String name, Case grammarCase);

	String getFormattedCapitalWikilinkGenitive(String articleTitle, String title);

}
