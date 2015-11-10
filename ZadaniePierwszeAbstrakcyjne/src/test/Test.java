package test;

import static org.junit.Assert.*;
import zadaniepierwsze.data.Data;
import zadaniepierwsze.data.TextData;
import zadaniepierwsze.exporter.Exporter;
import zadaniepierwsze.exporter.TextExporter;
import zadaniepierwsze.factory.DistributedModuleFactory;
import zadaniepierwsze.factory.DistributedModuleTextFactory;
import zadaniepierwsze.importer.Importer;
import zadaniepierwsze.importer.TextImporter;

public class Test {

	@org.junit.Test
	public void testExporter() {
		String textToBeExported = "Ala ma kota";
		Exporter exporter = new TextExporter(textToBeExported);
		Data exportedData = exporter.getExportData();
		String exportedText = ((TextData)exportedData).getText();
		assertEquals(textToBeExported, exportedText);
		exportedData = exporter.getExportData();
		exportedText = ((TextData)exportedData).getText();
		textToBeExported = new String();
		assertEquals(textToBeExported, exportedText);
	}
	
	@org.junit.Test
	public void TestImporter() {
		String textToBeImported = "Ala zgubila dolara";
		Data dataToSendToImporter = new TextData(textToBeImported);
		Importer importer = new TextImporter();
		importer.ImportData(dataToSendToImporter);
		String dataSavedInImporter = ((TextImporter)importer).importedText;
		assertEquals(textToBeImported, dataSavedInImporter);
	}
	
	@org.junit.Test
	public void TestFactory() {
		final String textToForFactory = "Ali kot zjadl dolara";
		DistributedModuleFactory factory = new DistributedModuleTextFactory(textToForFactory);
		Data dataFromFactory = factory.createData();
		String textFromModule = ((TextData)dataFromFactory).getText();
		assertEquals(textToForFactory, textFromModule);
		Exporter exporter = factory.createExporter();
		textFromModule = ((TextData)(((TextExporter)exporter).getExportData())).getText();
		assertEquals(textToForFactory, textFromModule);
		Importer importer = factory.createImporter();
	}

}
