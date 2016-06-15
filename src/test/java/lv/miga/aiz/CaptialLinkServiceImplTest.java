package lv.miga.aiz;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CaptialLinkServiceImplTest {

	@Test
	public void testFormatWikiLinkSimple() {
		assertEquals("[[Tests]]", ParishAdmCenterLinkServiceImpl.formatWikiLink("Tests", "Tests"));
	}
	
	@Test
	public void testFormatWikiLinkDifferentShort() {
		assertEquals("[[Test]]s", ParishAdmCenterLinkServiceImpl.formatWikiLink("Test", "Tests"));
	}

	@Test
	public void testFormatWikiLinkDifferent() {
		assertEquals("[[Tests (datorika)|Tests]]", ParishAdmCenterLinkServiceImpl.formatWikiLink("Tests (datorika)", "Tests"));
	}

	@Test
	public void testGetFormattedCapitalWikilinkGenitiveDifferent() {
		assertEquals("[[Anna (ciems)|Annas]]",
				new ParishAdmCenterLinkServiceImpl().getFormattedCapitalWikilinkGenitive("Anna (ciems)", "Anna"));
	}

	@Test
	public void testGetFormattedCapitalWikilinkGenitiveEqual() {
		assertEquals("[[Anna]]s", new ParishAdmCenterLinkServiceImpl().getFormattedCapitalWikilinkGenitive("Anna", "Anna"));
	}
	
	@Test
	public void testGetFormattedCapitalWikilinkNominativeEqual() {
		assertEquals("[[Anna]]", new ParishAdmCenterLinkServiceImpl().getFormattedCapitalWikilinkNominative("Anna", "Anna"));
	}
	
	@Test
	public void testGetFormattedCapitalWikilinkNominativeDifferent() {
		assertEquals("[[Anna (ciems)|Anna]]", new ParishAdmCenterLinkServiceImpl().getFormattedCapitalWikilinkNominative("Anna (ciems)", "Anna"));
	}
	
}
