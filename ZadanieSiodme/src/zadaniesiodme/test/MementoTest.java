package zadaniesiodme.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import zadaniesiodme.Memento;

public class MementoTest {

	private final String fileName = "file.txt"; // from C++11 it is legal in C++
	private final String fileContext = "Ala ma kota";

	private Memento memento = new Memento(fileContext, fileName);

	private boolean isBold = false, isItalic = false, isUnderlined = false;

	private void checkTextFont(boolean expcecteIsBold, boolean expcecteIsItalic, boolean expcecteIsUnderlined) {
		Assert.assertEquals(expcecteIsBold, memento.isTextBold());
		Assert.assertEquals(expcecteIsItalic, memento.isTextItalic());
		Assert.assertEquals(expcecteIsUnderlined, memento.isTextUnderlined());
	}

	@Test
	public void testConstructionOfSimpleMemento() {
		Assert.assertEquals(fileName, memento.getDocumentName());
		Assert.assertEquals(fileContext, memento.getDocumentContext());

		checkTextFont(isBold, isItalic, isUnderlined);

		Assert.assertEquals(0, memento.getNumberOfAdditionalAttributes());
	}

	@Test
	public void testRenaming() {
		Assert.assertEquals(fileName, memento.getDocumentName());
		Assert.assertEquals(fileContext, memento.getDocumentContext());

		final String newfileName = new String("path/to/file/file.txt");
		memento.setDocumentName(newfileName);

		Assert.assertEquals(newfileName, memento.getDocumentName());
		Assert.assertEquals(fileContext, memento.getDocumentContext());

		checkTextFont(isBold, isItalic, isUnderlined);

		Assert.assertEquals(0, memento.getNumberOfAdditionalAttributes());
	}

	@Test
	public void testChangingContext() {
		Assert.assertEquals(fileName, memento.getDocumentName());
		Assert.assertEquals(fileContext, memento.getDocumentContext());

		final String newFileContext = new String(
				"Ciekawe zadanie do rozwiazania:\n" + "  SEND\n" + "+ MORE\n" + "_________\n" + " MONEY");

		memento.setDocumentContext(newFileContext);

		Assert.assertEquals(fileName, memento.getDocumentName());
		Assert.assertEquals(newFileContext, memento.getDocumentContext());

		checkTextFont(isBold, isItalic, isUnderlined);

		Assert.assertEquals(0, memento.getNumberOfAdditionalAttributes());
	}

	@Test
	public void testChangingFontStyle() {
		checkTextFont(isBold, isItalic, isUnderlined);

		Assert.assertEquals(0, memento.getNumberOfAdditionalAttributes());

		memento.changeBold();
		isBold = true;
		checkTextFont(isBold, isItalic, isUnderlined);

		memento.changeItalic();
		isItalic = true;
		checkTextFont(isBold, isItalic, isUnderlined);

		memento.changeUnderlined();
		isUnderlined = true;
		checkTextFont(isBold, isItalic, isUnderlined);

		memento.changeBold();
		isBold = false;
		checkTextFont(isBold, isItalic, isUnderlined);

		Assert.assertEquals(0, memento.getNumberOfAdditionalAttributes());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testChangingAdditionalAttributes() {
		checkTextFont(isBold, isItalic, isUnderlined);

		Assert.assertEquals(0, memento.getNumberOfAdditionalAttributes());

		Map<String, String> attributes = new HashMap<>();
		attributes.put("author", "Grzegorz");
		attributes.put("group", "programmers");

		for (String key : attributes.keySet()) {
			memento.setAttribute(key, attributes.get(key));
		}

		Assert.assertEquals(attributes.size(), memento.getNumberOfAdditionalAttributes());

		for (String key : attributes.keySet()) {
			Assert.assertEquals(attributes.get(key), memento.getAttribute(key));
		}

		// Patrz adnotacja
		memento.getAttribute("noe existing attribute");
	}

	@Test
	public void testComparation() {
		Memento mementoNew = new Memento(fileContext, fileName);

		Assert.assertEquals(memento.getDocumentName(), mementoNew.getDocumentName());
		Assert.assertEquals(memento.getDocumentContext(), mementoNew.getDocumentContext());

		Assert.assertEquals(memento, mementoNew);

		memento.changeBold();
		Assert.assertNotEquals(memento, mementoNew);
	}
}
