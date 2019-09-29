package nc.container.processor;

import nc.container.SlotFurnace;
import nc.container.SlotProcessorInput;
import nc.container.SlotSpecificInput;
import nc.recipe.NCRecipes;
import nc.tile.processor.TileItemFluidProcessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

public class ContainerExtractor extends ContainerItemFluidProcessor {

	public ContainerExtractor(EntityPlayer player, TileItemFluidProcessor tileEntity) {
		super(player, tileEntity, NCRecipes.extractor);
		
		addSlotToContainer(new SlotProcessorInput(tileEntity, recipeHandler, 0, 42, 35));
		
		addSlotToContainer(new SlotFurnace(player, tileEntity, 1, 102, 35));
		
		addSlotToContainer(new SlotSpecificInput(tileEntity, 2, 132, 64, speedUpgrade));
		addSlotToContainer(new SlotSpecificInput(tileEntity, 3, 152, 64, energyUpgrade));
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(player.inventory, j + 9*i + 9, 8 + 18*j, 84 + 18*i));
			}
		}
		
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(player.inventory, i, 8 + 18*i, 142));
		}
	}
}
