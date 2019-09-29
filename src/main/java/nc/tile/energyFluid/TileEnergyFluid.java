package nc.tile.energyFluid;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import nc.ModCheck;
import nc.config.NCConfig;
import nc.tile.energy.TileEnergy;
import nc.tile.fluid.ITileFluid;
import nc.tile.internal.energy.EnergyConnection;
import nc.tile.internal.fluid.FluidConnection;
import nc.tile.internal.fluid.FluidTileWrapper;
import nc.tile.internal.fluid.GasTileWrapper;
import nc.tile.internal.fluid.Tank;
import nc.tile.internal.fluid.TankOutputSetting;
import nc.util.GasHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public abstract class TileEnergyFluid extends TileEnergy implements ITileFluid {
	
	private final @Nonnull List<Tank> tanks;
	
	private @Nonnull FluidConnection[] fluidConnections;
	
	private final @Nonnull FluidTileWrapper[] fluidSides;
	
	private final @Nonnull GasTileWrapper gasWrapper;
	
	private boolean inputTanksSeparated = false;
	private final List<Boolean> voidUnusableFluidInputs;
	private final List<TankOutputSetting> tankOutputSettings;
	
	public TileEnergyFluid(int capacity, @Nonnull EnergyConnection[] energyConnections, int fluidCapacity, List<String> allowedFluidsList, @Nonnull FluidConnection[] fluidConnections) {
		this(capacity, capacity, energyConnections, Lists.newArrayList(fluidCapacity), Lists.newArrayList(fluidCapacity), Lists.<List<String>>newArrayList(allowedFluidsList), fluidConnections);
	}
	
	public TileEnergyFluid(int capacity, @Nonnull EnergyConnection[] energyConnections, @Nonnull List<Integer> fluidCapacity, List<List<String>> allowedFluidsLists, @Nonnull FluidConnection[] fluidConnections) {
		this(capacity, capacity, energyConnections, fluidCapacity, fluidCapacity, allowedFluidsLists, fluidConnections);
	}
	
	public TileEnergyFluid(int capacity, @Nonnull EnergyConnection[] energyConnections, int fluidCapacity, int maxFluidTransfer, List<String> allowedFluidsList, @Nonnull FluidConnection[] fluidConnections) {
		this(capacity, capacity, energyConnections, Lists.newArrayList(fluidCapacity), Lists.newArrayList(maxFluidTransfer), Lists.<List<String>>newArrayList(allowedFluidsList), fluidConnections);
	}
	
	public TileEnergyFluid(int capacity, @Nonnull EnergyConnection[] energyConnections, @Nonnull List<Integer> fluidCapacity, @Nonnull List<Integer> maxFluidTransfer, List<List<String>> allowedFluidsLists, @Nonnull FluidConnection[] fluidConnections) {
		this(capacity, capacity, energyConnections, fluidCapacity, maxFluidTransfer, allowedFluidsLists, fluidConnections);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, @Nonnull EnergyConnection[] energyConnections, int fluidCapacity, List<String> allowedFluidsList, @Nonnull FluidConnection[] fluidConnections) {
		this(capacity, maxTransfer, energyConnections, Lists.newArrayList(fluidCapacity), Lists.newArrayList(fluidCapacity), Lists.<List<String>>newArrayList(allowedFluidsList), fluidConnections);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, @Nonnull EnergyConnection[] energyConnections, @Nonnull List<Integer> fluidCapacity, List<List<String>> allowedFluidsLists, @Nonnull FluidConnection[] fluidConnections) {
		this(capacity, maxTransfer, energyConnections, fluidCapacity, fluidCapacity, allowedFluidsLists, fluidConnections);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, @Nonnull EnergyConnection[] energyConnections, int fluidCapacity, int maxFluidTransfer, List<String> allowedFluidsList, @Nonnull FluidConnection[] fluidConnections) {
		this(capacity, maxTransfer, energyConnections, Lists.newArrayList(fluidCapacity), Lists.newArrayList(maxFluidTransfer), Lists.<List<String>>newArrayList(allowedFluidsList), fluidConnections);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, @Nonnull EnergyConnection[] energyConnections, @Nonnull List<Integer> fluidCapacity, @Nonnull List<Integer> maxFluidTransfer, List<List<String>> allowedFluidsLists, @Nonnull FluidConnection[] fluidConnections) {
		super(capacity, maxTransfer, energyConnections);
		tanks = new ArrayList<Tank>();
		voidUnusableFluidInputs = new ArrayList<Boolean>();
		tankOutputSettings = new ArrayList<TankOutputSetting>();
		if (!fluidCapacity.isEmpty()) {
			for (int i = 0; i < fluidCapacity.size(); i++) {
				tanks.add(new Tank(fluidCapacity.get(i), maxFluidTransfer.get(i), allowedFluidsLists == null || allowedFluidsLists.size() <= i ? null : allowedFluidsLists.get(i)));
				voidUnusableFluidInputs.add(false);
				tankOutputSettings.add(TankOutputSetting.DEFAULT);
			}
		}
		this.fluidConnections = fluidConnections;
		fluidSides = ITileFluid.getDefaultFluidSides(this);
		gasWrapper = new GasTileWrapper(this);
	}
	
	@Override
	public @Nonnull List<Tank> getTanks() {
		return tanks;
	}
	
	@Override
	public @Nonnull FluidConnection[] getFluidConnections() {
		return fluidConnections;
	}
	
	@Override
	public void setFluidConnections(@Nonnull FluidConnection[] connections) {
		fluidConnections = connections;
	}

	@Override
	public @Nonnull FluidTileWrapper[] getFluidSides() {
		return fluidSides;
	}
	
	@Override
	public @Nonnull GasTileWrapper getGasWrapper() {
		return gasWrapper;
	}
	
	@Override
	public boolean getInputTanksSeparated() {
		return inputTanksSeparated;
	}
	
	@Override
	public void setInputTanksSeparated(boolean shared) {
		inputTanksSeparated = shared;
	}
	
	@Override
	public boolean getVoidUnusableFluidInput(int tankNumber) {
		return voidUnusableFluidInputs.get(tankNumber);
	}
	
	@Override
	public void setVoidUnusableFluidInput(int tankNumber, boolean voidUnusableFluidInput) {
		voidUnusableFluidInputs.set(tankNumber, voidUnusableFluidInput);
	}
	
	@Override
	public TankOutputSetting getTankOutputSetting(int tankNumber) {
		return tankOutputSettings.get(tankNumber);
	}
	
	@Override
	public void setTankOutputSetting(int tankNumber, TankOutputSetting setting) {
		tankOutputSettings.set(tankNumber, setting);
	}
	
	// NBT
	
	@Override
	public NBTTagCompound writeAll(NBTTagCompound nbt) {
		super.writeAll(nbt);
		writeTanks(nbt);
		writeFluidConnections(nbt);
		writeTankSettings(nbt);
		return nbt;
	}
		
	@Override
	public void readAll(NBTTagCompound nbt) {
		super.readAll(nbt);
		readTanks(nbt);
		readFluidConnections(nbt);
		readTankSettings(nbt);
	}
	
	// Capability
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing side) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || (ModCheck.mekanismLoaded() && NCConfig.enable_mek_gas && capability == GasHelper.GAS_HANDLER_CAPABILITY)) {
			return !getTanks().isEmpty() && hasFluidSideCapability(side);
		}
		return super.hasCapability(capability, side);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (!getTanks().isEmpty() && hasFluidSideCapability(side)) {
				return (T) getFluidSide(nonNullSide(side));
			}
			return null;
		}
		else if (ModCheck.mekanismLoaded() && capability == GasHelper.GAS_HANDLER_CAPABILITY) {
			if (NCConfig.enable_mek_gas && !getTanks().isEmpty() && hasFluidSideCapability(side)) {
				return (T) getGasWrapper();
			}
			return null;
		}
		return super.getCapability(capability, side);
	}
}
