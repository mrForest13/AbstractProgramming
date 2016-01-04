package zadaniesiodme;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Document implements Command {

	private String documentName = new String();
	private String documentContext = new String();
	private boolean isTextBold;
	private boolean isTextItalic;
	private boolean isTextUnderlined;

	private Map<String, String> attributes;

	private LinkedList<Memento> documentState;

	public Document() {
		documentState = new LinkedList<>();
		attributes = new HashMap<>();
		saveState();
	}

	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}

	public String getAttribute(String key) {
		String s = attributes.get(key);
		if (s != null)
			return s;
		else
			throw new IllegalArgumentException();
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
		saveState();
	}

	public String getDocumentContext() {
		return documentContext;
	}

	public void setDocumentContext(String documentContext) {
		this.documentContext = documentContext;
		saveState();
	}

	public boolean isTextBold() {
		return isTextBold;
	}

	public void changeBold() {
		this.isTextBold = !this.isTextBold;
		saveState();
	}

	public boolean isTextItalic() {
		return isTextItalic;
	}

	public void changeItalic() {
		this.isTextItalic = !this.isTextItalic;
		saveState();
	}

	public boolean isTextUnderlined() {
		return isTextUnderlined;
	}

	public void changeUnderlined() {
		this.isTextUnderlined = !this.isTextUnderlined;
		saveState();
	}

	public int getNumberOfAdditionalAttributes() {
		return attributes.size();
	}

	private void saveState() {
		documentState.add(new Memento(documentName, documentContext, isTextBold, isTextItalic, isTextUnderlined));
	}

	private boolean setAll(Memento memento) {
		this.documentName = memento.getDocumentName();
		this.documentContext = memento.getDocumentContext();
		this.isTextBold = memento.isTextBold();
		this.isTextItalic = memento.isTextItalic();
		this.isTextUnderlined = memento.isTextUnderlined();
		return true;
	}

	public boolean undo() {
		Memento actual = new Memento(documentName, documentContext, isTextBold, isTextItalic, isTextUnderlined);
		Memento undo = documentState.removeLast();
		if (undo.equals(actual))
			undo = documentState.removeLast();
		
		return undo == null ? false : setAll(undo);
	}

	@Override
	public void execute() { }
}
