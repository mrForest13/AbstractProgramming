package zadanieszoste;

public abstract class ControllerTemplateMethod {

	public abstract boolean nextMoving();

	public abstract ConstructionRecipe getReciple();

	public abstract boolean constructionObject(ConstructionRecipe constructionRecipe);

	public abstract int getNumber();

	public abstract void log(LoggingType type, String message);

	public abstract boolean scran();

	public void execute() {
		ConstructionRecipe constructionRecipe = getReciple();
		String nameOfObject = constructionRecipe.getNameOfObject();

		while (nextMoving()) {
			for (int i = 0; i < getNumber(); i++) {
				if (constructionObject(constructionRecipe))
					log(LoggingType.Info, ": Info!");
				else {
					log(LoggingType.Warning, ": Warrning!");
					if (!scran()) {
						log(LoggingType.Error, ": Error!");
					}
					return;
				}
			}
		}
	}
}
