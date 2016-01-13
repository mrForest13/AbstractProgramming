package zadanieszoste.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import zadanieszoste.ConstructionRecipe;
import zadanieszoste.ControllerTemplateMethod;
import zadanieszoste.ControllerWithDependencyInjection;
import zadanieszoste.IConstructionRecipeCreator;
import zadanieszoste.ILogger;
import zadanieszoste.IObjectsConstructor;
import zadanieszoste.IProductionLineMover;
import zadanieszoste.LoggingType;
import zadanieszoste.MovingDirection;
import zadanieszoste.ProductionException;

public class EndToEndTests {

	private IObjectsConstructor objectsConstructor;
	private IConstructionRecipeCreator constructionRecipeCreator;
	private ILogger logger;
	private IProductionLineMover productionLineMover;
	private ConstructionRecipe constructionRecipe;
	private ControllerTemplateMethod controller;

	private final String nameOfObject = "UJ's first car";
	private int numberOfObjectsToConstruct = 1;

	@Before
	public void initialize() {
		objectsConstructor = Mockito.mock(IObjectsConstructor.class);
		constructionRecipeCreator = Mockito.mock(IConstructionRecipeCreator.class);
		logger = Mockito.mock(ILogger.class);
		productionLineMover = Mockito.mock(IProductionLineMover.class);
		constructionRecipe = Mockito.mock(ConstructionRecipe.class);
		controller = new ControllerWithDependencyInjection(objectsConstructor, constructionRecipeCreator, logger,
				productionLineMover);
	}

	@Test
	public void TestConstructionOfSingleObject() {
		final boolean wasSuccessfull = true;
		Mockito.when(constructionRecipe.getNameOfObject()).thenReturn(nameOfObject);
		Mockito.when(constructionRecipeCreator.getConstructionRecipe()).thenReturn(constructionRecipe);
		Mockito.when(constructionRecipeCreator.getNumberOfElementsToProduce()).thenReturn(numberOfObjectsToConstruct);
		Mockito.when(productionLineMover.moveProductionLine(MovingDirection.Forward)).thenReturn(wasSuccessfull, false);
		Mockito.when(objectsConstructor.constructObjectFromRecipe(constructionRecipe)).thenReturn(wasSuccessfull);

		controller.execute();

		Mockito.verify(constructionRecipe, Mockito.times(1)).getNameOfObject();
		Mockito.verify(constructionRecipeCreator, Mockito.times(1)).getConstructionRecipe();
		Mockito.verify(objectsConstructor, Mockito.times(1)).constructObjectFromRecipe(constructionRecipe);
		Mockito.verify(logger, Mockito.times(1)).log(Mockito.eq(LoggingType.Info), Mockito.anyString());
		Mockito.verify(productionLineMover, Mockito.times(2)).moveProductionLine(MovingDirection.Forward);
	}

	@Test
	public void TestConstructionFailure_MovingProductionLineFailed() {
		Mockito.when(constructionRecipe.getNameOfObject()).thenReturn(nameOfObject);
		Mockito.when(constructionRecipeCreator.getConstructionRecipe()).thenReturn(constructionRecipe);
		Mockito.when(constructionRecipeCreator.getNumberOfElementsToProduce()).thenReturn(numberOfObjectsToConstruct);
		Mockito.when(productionLineMover.moveProductionLine(MovingDirection.Forward))
				.thenThrow(new ProductionException("Can't move ProductionLine!"));
		
		controller.execute();
		
		Mockito.verify(constructionRecipe,Mockito.times(1)).getNameOfObject();
		Mockito.verify(constructionRecipeCreator, Mockito.times(1)).getConstructionRecipe();
		Mockito.verify(objectsConstructor, Mockito.never()).constructObjectFromRecipe(constructionRecipe);
		Mockito.verify(logger, Mockito.never()).log(Mockito.eq(LoggingType.Info), Mockito.anyString());
		Mockito.verify(productionLineMover, Mockito.times(1)).moveProductionLine(MovingDirection.Forward);
	}
	
	@Test
	public void TestConstructionFailure_ObjectConstructionFailed() {
		boolean wasSuccessfull = true;
		Mockito.when(constructionRecipe.getNameOfObject()).thenReturn(nameOfObject);
		Mockito.when(constructionRecipeCreator.getConstructionRecipe()).thenReturn(constructionRecipe);
		Mockito.when(constructionRecipeCreator.getNumberOfElementsToProduce()).thenReturn(numberOfObjectsToConstruct);
		Mockito.when(productionLineMover.moveProductionLine(MovingDirection.Forward)).thenReturn(wasSuccessfull, false);
		Mockito.when(objectsConstructor.constructObjectFromRecipe(constructionRecipe)).thenReturn(wasSuccessfull=false);
		Mockito.when(productionLineMover.moveProductionLine(MovingDirection.ToScran)).thenReturn(wasSuccessfull=true, false);
		
		controller.execute();
		
		Mockito.verify(constructionRecipe,Mockito.times(1)).getNameOfObject();
		Mockito.verify(constructionRecipeCreator, Mockito.times(1)).getConstructionRecipe();
		Mockito.verify(objectsConstructor, Mockito.times(1)).constructObjectFromRecipe(constructionRecipe);
		Mockito.verify(logger, Mockito.times(1)).log(Mockito.eq(LoggingType.Warning), Mockito.anyString());
		Mockito.verify(productionLineMover, Mockito.times(1)).moveProductionLine(MovingDirection.Forward);
		Mockito.verify(productionLineMover, Mockito.times(1)).moveProductionLine(MovingDirection.ToScran);
	}
	
	@Test
	public void TestConstructionFailure_ObjectConstructionFailedThenMovingToScanAlsoFailed() {
		boolean wasSuccessfull = true;
		Mockito.when(constructionRecipe.getNameOfObject()).thenReturn(nameOfObject);
		Mockito.when(constructionRecipeCreator.getConstructionRecipe()).thenReturn(constructionRecipe);
		Mockito.when(constructionRecipeCreator.getNumberOfElementsToProduce()).thenReturn(numberOfObjectsToConstruct);
		Mockito.when(productionLineMover.moveProductionLine(MovingDirection.Forward)).thenReturn(wasSuccessfull, false);
		Mockito.when(objectsConstructor.constructObjectFromRecipe(constructionRecipe)).thenReturn(wasSuccessfull=false);
		Mockito.when(productionLineMover.moveProductionLine(MovingDirection.ToScran))
		.thenThrow(new ProductionException("Can't move not constructed car to scan, scan is full!"));
		
		controller.execute();

		Mockito.verify(constructionRecipe,Mockito.times(1)).getNameOfObject();
		Mockito.verify(constructionRecipeCreator, Mockito.times(1)).getConstructionRecipe();
		Mockito.verify(objectsConstructor, Mockito.times(1)).constructObjectFromRecipe(constructionRecipe);
		Mockito.verify(logger, Mockito.times(1)).log(Mockito.eq(LoggingType.Warning), Mockito.anyString());
		Mockito.verify(logger, Mockito.times(1)).log(Mockito.eq(LoggingType.Error), Mockito.anyString());
		Mockito.verify(productionLineMover, Mockito.times(1)).moveProductionLine(MovingDirection.Forward);
		Mockito.verify(productionLineMover, Mockito.times(1)).moveProductionLine(MovingDirection.ToScran);
	}
}
