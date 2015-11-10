package zadaniepierwsze.factory;

import zadaniepierwsze.data.Data;
import zadaniepierwsze.data.TextData;
import zadaniepierwsze.exporter.Exporter;
import zadaniepierwsze.exporter.TextExporter;
import zadaniepierwsze.importer.Importer;
import zadaniepierwsze.importer.TextImporter;

public class DistributedModuleTextFactory implements DistributedModuleFactory {

	private Data data = new TextData();
	
	public DistributedModuleTextFactory(String textToForFactory) {
		this.data.setText(textToForFactory);
	}

	@Override
	public Data createData() {
		return data;
	}

	@Override
	public Exporter createExporter() {
		return new TextExporter(data.getText());
	}

	@Override
	public Importer createImporter() {
		return new TextImporter();
	}

}
