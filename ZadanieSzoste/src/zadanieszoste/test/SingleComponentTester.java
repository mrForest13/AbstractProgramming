package zadanieszoste.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import zadanieszoste.ConstructionRecipe;
import zadanieszoste.IConstructionRecipeCreator;
import zadanieszoste.ILogger;
import zadanieszoste.IObjectsConstructor;
import zadanieszoste.IProductionLineMover;
import zadanieszoste.LoggingType;
import zadanieszoste.MovingDirection;

public class SingleComponentTester {

	@Test
	public void TestILogger() {
		ILogger iLogger = Mockito.mock(ILogger.class);
		int[] loggedErrors = { 0 };
		Mockito.doAnswer(invocation -> loggedErrors[0]++).when(iLogger).log(Mockito.eq(LoggingType.Error),
				Mockito.anyString());
		int[] loggedWanings = { 0 };
		Mockito.doAnswer(invocation -> loggedWanings[0]++).when(iLogger).log(Mockito.eq(LoggingType.Warning),
				Mockito.anyString());
		int[] loggedInformations = { 0 };
		Mockito.doAnswer(invocation -> loggedInformations[0]++).when(iLogger).log(Mockito.eq(LoggingType.Info),
				Mockito.anyString());

		iLogger.log(LoggingType.Error, "seriousError");
		iLogger.log(LoggingType.Error, "seriousError2");
		iLogger.log(LoggingType.Warning, "wrn");
		iLogger.log(LoggingType.Info, "succesfully done");
		iLogger.log(LoggingType.Info, "done");
		iLogger.log(LoggingType.Info, "done again");

		Assert.assertEquals(2, loggedErrors[0]);
		Assert.assertEquals(1, loggedWanings[0]);
		Assert.assertEquals(3, loggedInformations[0]);
		Mockito.verify(iLogger, Mockito.atLeastOnce()).log(Mockito.eq(LoggingType.Error), Mockito.anyString());
		Mockito.verify(iLogger, Mockito.times(1)).log(Mockito.eq(LoggingType.Warning), Mockito.anyString());
		Mockito.verify(iLogger, Mockito.times(3)).log(Mockito.eq(LoggingType.Info), Mockito.anyString());
	}
	
	@Test
	public void TestProductionLineMover() {
		IProductionLineMover ipProductionLineMover = Mockito.mock(IProductionLineMover.class);
		
		ipProductionLineMover.moveProductionLine(MovingDirection.Forward);
		ipProductionLineMover.moveProductionLine(MovingDirection.ToScran);
		ipProductionLineMover.moveProductionLine(MovingDirection.Forward);
		ipProductionLineMover.moveProductionLine(MovingDirection.Forward);
		
		Mockito.verify(ipProductionLineMover, Mockito.times(3)).moveProductionLine(MovingDirection.Forward);
		Mockito.verify(ipProductionLineMover, Mockito.times(1)).moveProductionLine(MovingDirection.ToScran);
		Mockito.verify(ipProductionLineMover, Mockito.never()).moveProductionLine(MovingDirection.Back);
	}
	
	@Test
	public void TestConstructionRecipe() {
		ConstructionRecipe constructionRecipe = Mockito.mock(ConstructionRecipe.class);
		final String nameOfObject = "Fiat 126P";
		Mockito.when(constructionRecipe.getNameOfObject()).thenReturn(nameOfObject);
		
		Assert.assertEquals(nameOfObject, constructionRecipe.getNameOfObject());
	}
	
	@Test
	public void TestIConstructionRecipeCreator() {
		ConstructionRecipe constructionRecipe = Mockito.mock(ConstructionRecipe.class);
		final String nameOfObject = "Fiat 126P";
		Mockito.when(constructionRecipe.getNameOfObject()).thenReturn(nameOfObject);
		
		final int numberOfObjectsToProduce = 10;
		IConstructionRecipeCreator icConstructionRecipeCreator = Mockito.mock(IConstructionRecipeCreator.class);
		Mockito.when(icConstructionRecipeCreator.getConstructionRecipe()).thenReturn(constructionRecipe);
		Mockito.when(icConstructionRecipeCreator.getNumberOfElementsToProduce()).thenReturn(numberOfObjectsToProduce);
		
		IConstructionRecipeCreator mockCreatorInstance = icConstructionRecipeCreator;
		Assert.assertEquals(numberOfObjectsToProduce, mockCreatorInstance.getNumberOfElementsToProduce());
		Assert.assertEquals(nameOfObject,mockCreatorInstance.getConstructionRecipe().getNameOfObject());
	}
	
	@Test
	public void TestObjectConstructor() {
		ConstructionRecipe constructionRecipe = Mockito.mock(ConstructionRecipe.class);
		IObjectsConstructor ioConstructor = Mockito.mock(IObjectsConstructor.class);
		Mockito.when(ioConstructor.constructObjectFromRecipe(constructionRecipe)).thenReturn(true);
		
		Assert.assertEquals(true, ioConstructor.constructObjectFromRecipe(constructionRecipe));
		Mockito.verify(ioConstructor, Mockito.times(1)).constructObjectFromRecipe(constructionRecipe);
	}

}
