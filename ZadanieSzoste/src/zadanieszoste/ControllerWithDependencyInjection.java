package zadanieszoste;

public class ControllerWithDependencyInjection extends ControllerTemplateMethod {

	private IObjectsConstructor objectsConstructor;
	private IConstructionRecipeCreator constructionRecipeCreator;
	private ILogger logger;
	private IProductionLineMover productionLineMover;

	public ControllerWithDependencyInjection(IObjectsConstructor objectsConstructor,
			IConstructionRecipeCreator constructionRecipeCreator, ILogger logger,
			IProductionLineMover productionLineMover) {
		this.objectsConstructor = objectsConstructor;
		this.constructionRecipeCreator = constructionRecipeCreator;
		this.logger = logger;
		this.productionLineMover = productionLineMover;
	}

	@Override
	public boolean nextMoving() {
		try {
			return productionLineMover.moveProductionLine(MovingDirection.Forward);
		} catch (ProductionException e) {
			return false;
		}
	}

	@Override
	public boolean scran() {
		try {
			return productionLineMover.moveProductionLine(MovingDirection.ToScran);
		} catch (ProductionException e) {
			return false;
		}
	}

	@Override
	public int getNumber() {
		return constructionRecipeCreator.getNumberOfElementsToProduce();
	}

	@Override
	public ConstructionRecipe getReciple() {
		return constructionRecipeCreator.getConstructionRecipe();
	}

	@Override
	public boolean constructionObject(ConstructionRecipe constructionRecipe) {
		return objectsConstructor.constructObjectFromRecipe(constructionRecipe);
	}

	@Override
	public void log(LoggingType type, String message) {
		logger.log(type, message);
	}

}
