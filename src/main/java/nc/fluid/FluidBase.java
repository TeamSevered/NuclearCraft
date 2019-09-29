package nc.fluid;

import nc.Global;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidBase extends Fluid {
	
	public FluidBase(String fluidName, boolean canBeStill) {
		super(fluidName, new ResourceLocation(Global.MOD_ID + ":blocks/fluids/" + fluidName + (canBeStill ? "_still" : "")), new ResourceLocation(Global.MOD_ID + ":blocks/fluids/" + fluidName + (canBeStill ? "_flow" : "")));
		
		boolean defaultFluid = FluidRegistry.registerFluid(this);
		if (defaultFluid) FluidRegistry.addBucketForFluid(this);
	}
	
	public FluidBase(String fluidName, boolean canBeStill, String textureName, Integer colour) {
		super(fluidName, new ResourceLocation(Global.MOD_ID + ":blocks/fluids/" + textureName + (canBeStill ? "_still" : "")), new ResourceLocation(Global.MOD_ID + ":blocks/fluids/" + textureName + (canBeStill ? "_flow" : "")));
		
		int fixedColour = colour.intValue();
		if(((fixedColour >> 24) & 0xFF) == 0) fixedColour |= 0xFF << 24;
		setColor(fixedColour);
		
		boolean defaultFluid = FluidRegistry.registerFluid(this);
		if (defaultFluid) FluidRegistry.addBucketForFluid(this);
	}
	
	public FluidBase(String fluidName, Integer colour) {
		this(fluidName, true, "liquid", colour);
	}
}
