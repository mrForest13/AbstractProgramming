package zadaniepierwsze.factory;

import zadaniepierwsze.data.Data;
import zadaniepierwsze.exporter.Exporter;
import zadaniepierwsze.importer.Importer;

public interface DistributedModuleFactory {
	
	public Data createData();
	public Exporter createExporter();
	public Importer createImporter();
}
