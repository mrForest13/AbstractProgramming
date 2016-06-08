package zadaniepierwsze.exporter;

import zadaniepierwsze.data.Data;
import zadaniepierwsze.data.TextData;

public class TextExporter implements Exporter {
	
	private Data exportData = new TextData();
	
	public TextExporter(String textToBeExported) {
		exportData.setText(textToBeExported);
	}

	@Override
	public Data getExportData() {
		try {
			return exportData;
		}finally {
			exportData = new TextData();
		}
	}
	
}
