package zadaniesiodme.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import zadaniesiodme.Command;
import zadaniesiodme.Document;

public class CommandTest {

	private Document document = new Document();

	private void checkTextFont(boolean expcecteIsBold, boolean expcecteIsItalic, boolean expcecteIsUnderlined) {
		Assert.assertEquals(expcecteIsBold, document.isTextBold());
		Assert.assertEquals(expcecteIsItalic, document.isTextItalic());
		Assert.assertEquals(expcecteIsUnderlined, document.isTextUnderlined());
	}

	private void executeAllCommands(List<Command> commandType) {
		for (Command c : commandType)
			c.execute();
	}

	@Test
	public void testNoArgumentsCommands() {
		boolean isBold = false, isItalic = false, isUnderlined = false;
		checkTextFont(isBold, isItalic, isUnderlined);

		List<Command> commandType = new ArrayList<>();
		commandType.add(() -> document.changeBold());
		commandType.add(() -> document.changeItalic());
		commandType.add(() -> document.changeUnderlined());

		checkTextFont(isBold, isItalic, isUnderlined);

		isBold = isItalic = isUnderlined = true;
		executeAllCommands(commandType);
		checkTextFont(isBold, isItalic, isUnderlined);
	}

	@Test
	public void testSingleArgumentCommands() {
		final String newDocumentContext = new String("Ale urwal, ale to bylo dobre!");
		final String newDocumentName = new String("najlepszeCytatyInternetu.txt");

		List<Command> commandType = new ArrayList<>();
		commandType.add(() -> document.setDocumentContext(newDocumentContext));
		commandType.add(() -> document.setDocumentName(newDocumentName));

		Assert.assertEquals("", document.getDocumentName());
		Assert.assertEquals("", document.getDocumentContext());

		executeAllCommands(commandType);

		Assert.assertEquals(newDocumentName, document.getDocumentName());
		Assert.assertEquals(newDocumentContext, document.getDocumentContext());
	}

	@Test
	public void testCommandToAddAttributes() {
		Map<String, String> attributes = new HashMap<>();

		attributes.put("autor", "Grzegorz");
		attributes.put("numer zadania", "7");
		attributes.put("data inspiracji do wymyslania zadania", "1 grudnia 2015");
		
		List<Command> commandType = new ArrayList<>();
		
		for(Map.Entry<String, String> entry : attributes.entrySet())
			commandType.add(() -> document.setAttribute(entry.getKey(), entry.getValue()));
		
		Assert.assertEquals(0,document.getNumberOfAdditionalAttributes());
		
		executeAllCommands(commandType);
		
		Assert.assertEquals(attributes.size(),document.getNumberOfAdditionalAttributes());	
		for(String key : attributes.keySet())
			Assert.assertEquals(attributes.get(key),document.getAttribute(key));
	}

}
