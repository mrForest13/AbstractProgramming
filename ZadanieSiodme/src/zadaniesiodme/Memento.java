package zadaniesiodme;

import java.util.HashMap;
import java.util.Map;

public class Memento {

	private String documentName;
	private String documentContext;
	private boolean isTextBold;
	private boolean isTextItalic;
	private boolean isTextUnderlined;

	private Map<String, String> attributes;

	public Memento(String documentContext, String documentName) {
		attributes = new HashMap<>();
		this.setDocumentName(documentName);
		this.setDocumentContext(documentContext);
	}

	public Memento(String documentName, String documentContext, boolean isTextBold, boolean isTextItalic,
			boolean isTextUnderlined) {
		this.documentName = documentName;
		this.documentContext = documentContext;
		this.isTextBold = isTextBold;
		this.isTextItalic = isTextItalic;
		this.isTextUnderlined = isTextUnderlined;
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

	public boolean isTextBold() {
		return isTextBold;
	}

	public void changeBold() {
		this.isTextBold = !this.isTextBold;
	}

	public boolean isTextItalic() {
		return isTextItalic;
	}

	public void changeItalic() {
		this.isTextItalic = !this.isTextItalic;
	}

	public boolean isTextUnderlined() {
		return isTextUnderlined;
	}

	public void changeUnderlined() {
		this.isTextUnderlined = !this.isTextUnderlined;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentContext() {
		return documentContext;
	}

	public void setDocumentContext(String documentContext) {
		this.documentContext = documentContext;
	}

	public int getNumberOfAdditionalAttributes() {
		return attributes.size();
	}

	@Override
	public boolean equals(Object o) {
		return this.documentName.equals(((Memento) o).documentName)
				&& this.documentContext.equals(((Memento) o).documentContext)
				&& this.isTextBold == ((Memento) o).isTextBold 
				&& this.isTextItalic == ((Memento) o).isTextItalic
				&& this.isTextUnderlined == ((Memento) o).isTextUnderlined ? true : false;
	}

	@Override
	public String toString() {
		return "Memento [documentName=" + documentName + ", documentContext=" + documentContext + "]";
	}

}
