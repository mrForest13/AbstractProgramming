package zadaniesiodme.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import zadaniesiodme.Document;

public class DocumentTest {

	private Document document = new Document();

	@Test
	public void testConstructionOfSimpleDocument() {
		Assert.assertEquals("", document.getDocumentName());
		Assert.assertEquals("", document.getDocumentContext());

		Assert.assertFalse(document.isTextBold());
		Assert.assertFalse(document.isTextUnderlined());
		Assert.assertFalse(document.isTextItalic());

		Assert.assertEquals(0, document.getNumberOfAdditionalAttributes());
	}

	@Test
	public void testUndo() {
		List<String> contexts = new ArrayList<String>(
				Arrays.asList("Ala ma kota", "Ala ma psa", "Krzysztof ma matke, ma brata"));

		for (String s : contexts) {
			document.setDocumentContext(s);
			Assert.assertEquals(s, document.getDocumentContext());
		}

		Assert.assertEquals("", document.getDocumentName());

		for (int i = contexts.size() - 2; i >= 0; i--) {
			document.undo();
			Assert.assertEquals(contexts.get(i), document.getDocumentContext());
		}
	}

}
