package nc.integration.jei.generator;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import nc.integration.jei.IJEIHandler;
import nc.integration.jei.JEICategoryProcessor;
import nc.integration.jei.JEIMethods.RecipeFluidMapper;
import nc.integration.jei.JEIRecipeWrapper;
import nc.recipe.IngredientSorption;
import nc.util.Lang;

public class FusionCategory extends JEICategoryProcessor<JEIRecipeWrapper.Fusion> {
	
	public FusionCategory(IGuiHelper guiHelper, IJEIHandler handler) {
		super(guiHelper, handler, "fusion_core", "_jei", 55, 30, 94, 26);
		recipeTitle = Lang.localise("gui.container.fusion_core.reactor");
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, JEIRecipeWrapper.Fusion recipeWrapper, IIngredients ingredients) {
		super.setRecipe(recipeLayout, recipeWrapper, ingredients);
		
		RecipeFluidMapper fluidMapper = new RecipeFluidMapper();
		fluidMapper.map(IngredientSorption.INPUT, 0, 0, 56 - backPosX, 31 - backPosY, 6, 24);
		fluidMapper.map(IngredientSorption.INPUT, 1, 1, 66 - backPosX, 31 - backPosY, 6, 24);
		fluidMapper.map(IngredientSorption.OUTPUT, 0, 2, 112 - backPosX, 31 - backPosY, 6, 24);
		fluidMapper.map(IngredientSorption.OUTPUT, 1, 3, 122 - backPosX, 31 - backPosY, 6, 24);
		fluidMapper.map(IngredientSorption.OUTPUT, 2, 4, 132 - backPosX, 31 - backPosY, 6, 24);
		fluidMapper.map(IngredientSorption.OUTPUT, 3, 5, 142 - backPosX, 31 - backPosY, 6, 24);
		fluidMapper.mapFluidsTo(recipeLayout.getFluidStacks(), ingredients);
	}
	
	@Override
	public String getTitle() {
		return recipeTitle;
	}
}
