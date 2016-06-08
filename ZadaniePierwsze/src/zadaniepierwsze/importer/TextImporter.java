package zadaniepierwsze.importer;

import zadaniepierwsze.data.Data;

public class TextImporter implements Importer {

	public String importedText;

	@Override
	public void ImportData(Data dataToSendToImporter) {
		importedText = dataToSendToImporter.getTextToBeImported();
	}



}
