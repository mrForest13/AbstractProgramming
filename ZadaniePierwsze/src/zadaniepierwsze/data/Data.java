package zadaniepierwsze.data;

public abstract class Data {
	
	private String text = new String();
	
	public String getText() {
			return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public abstract String getTextToBeImported();
}
