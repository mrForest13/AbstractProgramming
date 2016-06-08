package zadaniepierwsze.data;

public class TextData extends Data {
	
	private String textToBeImported;
	
	public TextData() {}
	
	public TextData(String textToBeImported) {
		this.textToBeImported = textToBeImported;	
	}

	@Override
	public String getTextToBeImported() {
		return textToBeImported;
	}

	
}
