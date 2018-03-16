package nc.tile.energyFluid;

import nc.ModCheck;
import nc.config.NCConfig;
import nc.tile.energy.TileEnergy;
import nc.tile.energy.storage.EnumStorage.EnergyConnection;
import nc.tile.fluid.IFluidSpread;
import nc.tile.fluid.ITileFluid;
import nc.tile.fluid.tank.Tank;
import nc.tile.fluid.tank.EnumTank.FluidConnection;
import nc.tile.passive.ITilePassive;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.capability.templates.EmptyFluidHandler;

public abstract class TileEnergyFluid extends TileEnergy implements ITileFluid, IFluidHandler/*, IGasHandler, ITubeConnection*/ {
	
	public FluidConnection[] fluidConnections;
	public final Tank[] tanks;
	public boolean areTanksShared = false;
	
	public TileEnergyFluid(int capacity, EnergyConnection energyConnection, int fluidCapacity, FluidConnection fluidConnections, String[]... allowedFluids) {
		this(capacity, capacity, capacity, energyConnection, new int[] {fluidCapacity}, new int[] {fluidCapacity}, new int[] {fluidCapacity}, new FluidConnection[] {fluidConnections}, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, EnergyConnection energyConnection, int[] fluidCapacity, FluidConnection[] fluidConnections, String[]... allowedFluids) {
		this(capacity, capacity, capacity, energyConnection, fluidCapacity, fluidCapacity, fluidCapacity, fluidConnections, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, EnergyConnection energyConnection, int fluidCapacity, int maxFluidTransfer, FluidConnection fluidConnections, String[]... allowedFluids) {
		this(capacity, capacity, capacity, energyConnection, new int[] {fluidCapacity}, new int[] {maxFluidTransfer}, new int[] {maxFluidTransfer}, new FluidConnection[] {fluidConnections}, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, EnergyConnection energyConnection, int[] fluidCapacity, int[] maxFluidTransfer, FluidConnection[] fluidConnections, String[]... allowedFluids) {
		this(capacity, capacity, capacity, energyConnection, fluidCapacity, maxFluidTransfer, maxFluidTransfer, fluidConnections, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, EnergyConnection energyConnection, int fluidCapacity, int maxFluidReceive, int maxFluidExtract, FluidConnection fluidConnections, String[]... allowedFluids) {
		this(capacity, capacity, capacity, energyConnection, new int[] {fluidCapacity}, new int[] {maxFluidReceive}, new int[] {maxFluidExtract}, new FluidConnection[] {fluidConnections}, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, EnergyConnection energyConnection, int[] fluidCapacity, int[] maxFluidReceive, int[] maxFluidExtract, FluidConnection[] fluidConnections, String[]... allowedFluids) {
		this(capacity, capacity, capacity, energyConnection, fluidCapacity, maxFluidReceive, maxFluidExtract, fluidConnections, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, EnergyConnection energyConnection, int fluidCapacity, FluidConnection fluidConnections, String[]... allowedFluids) {
		this(capacity, maxTransfer, maxTransfer, energyConnection, new int[] {fluidCapacity}, new int[] {fluidCapacity}, new int[] {fluidCapacity}, new FluidConnection[] {fluidConnections}, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, EnergyConnection energyConnection, int[] fluidCapacity, FluidConnection[] fluidConnections, String[]... allowedFluids) {
		this(capacity, maxTransfer, maxTransfer, energyConnection, fluidCapacity, fluidCapacity, fluidCapacity, fluidConnections, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, EnergyConnection energyConnection, int fluidCapacity, int maxFluidTransfer, FluidConnection fluidConnections, String[]... allowedFluids) {
		this(capacity, maxTransfer, maxTransfer, energyConnection, new int[] {fluidCapacity}, new int[] {maxFluidTransfer}, new int[] {maxFluidTransfer}, new FluidConnection[] {fluidConnections}, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, EnergyConnection energyConnection, int[] fluidCapacity, int[] maxFluidTransfer, FluidConnection[] fluidConnections, String[]... allowedFluids) {
		this(capacity, maxTransfer, maxTransfer, energyConnection, fluidCapacity, maxFluidTransfer, maxFluidTransfer, fluidConnections, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, EnergyConnection energyConnection, int fluidCapacity, int maxFluidReceive, int maxFluidExtract, FluidConnection fluidConnections, String[]... allowedFluids) {
		this(capacity, maxTransfer, maxTransfer, energyConnection, new int[] {fluidCapacity}, new int[] {maxFluidReceive}, new int[] {maxFluidExtract}, new FluidConnection[] {fluidConnections}, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxTransfer, EnergyConnection energyConnection, int[] fluidCapacity, int[] maxFluidReceive, int[] maxFluidExtract, FluidConnection[] fluidConnections, String[]... allowedFluids) {
		this(capacity, maxTransfer, maxTransfer, energyConnection, fluidCapacity, maxFluidReceive, maxFluidExtract, fluidConnections, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxReceive, int maxExtract, EnergyConnection energyConnection, int fluidCapacity, FluidConnection fluidConnections, String[]... allowedFluids) {
		this(capacity, maxReceive, maxExtract, energyConnection, new int[] {fluidCapacity}, new int[] {fluidCapacity}, new int[] {fluidCapacity}, new FluidConnection[] {fluidConnections}, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxReceive, int maxExtract, EnergyConnection energyConnection, int[] fluidCapacity, FluidConnection[] fluidConnections, String[]... allowedFluids) {
		this(capacity, maxReceive, maxExtract, energyConnection, fluidCapacity, fluidCapacity, fluidCapacity, fluidConnections, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxReceive, int maxExtract, EnergyConnection energyConnection, int fluidCapacity, int maxFluidTransfer, FluidConnection fluidConnections, String[]... allowedFluids) {
		this(capacity, maxReceive, maxExtract, energyConnection, new int[] {fluidCapacity}, new int[] {maxFluidTransfer}, new int[] {maxFluidTransfer}, new FluidConnection[] {fluidConnections}, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxReceive, int maxExtract, EnergyConnection energyConnection, int[] fluidCapacity, int[] maxFluidTransfer, FluidConnection[] fluidConnections, String[]... allowedFluids) {
		this(capacity, maxReceive, maxExtract, energyConnection, fluidCapacity, maxFluidTransfer, maxFluidTransfer, fluidConnections, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxReceive, int maxExtract, EnergyConnection energyConnection, int fluidCapacity, int maxFluidReceive, int maxFluidExtract, FluidConnection fluidConnections, String[]... allowedFluids) {
		this(capacity, maxReceive, maxExtract, energyConnection, new int[] {fluidCapacity}, new int[] {maxFluidReceive}, new int[] {maxFluidExtract}, new FluidConnection[] {fluidConnections}, allowedFluids);
	}
	
	public TileEnergyFluid(int capacity, int maxReceive, int maxExtract, EnergyConnection energyConnection, int[] fluidCapacity, int[] maxFluidReceive, int[] maxFluidExtract, FluidConnection[] fluidConnections, String[]... allowedFluids) {
		super(capacity, maxReceive, maxExtract, energyConnection);
		if (fluidCapacity == null || fluidCapacity.length == 0) {
			tanks = null;
		} else {
			Tank[] tankList = new Tank[fluidCapacity.length];
			String[][] fluidWhitelists = new String[fluidCapacity.length][];
			for (int i = 0; i < fluidCapacity.length; i++) {
				if (i < allowedFluids.length) fluidWhitelists[i] = allowedFluids[i];
				else fluidWhitelists[i] = new String[] {};
			}
			for (int i = 0; i < fluidCapacity.length; i++) {
				tankList[i] = new Tank(fluidCapacity[i], maxFluidReceive[i], maxFluidExtract[i], fluidWhitelists[i]);
			}
			tanks = tankList;
		}
		if (fluidConnections == null || fluidConnections.length == 0) {
			this.fluidConnections = null;
		} else {
			FluidConnection[] energyConnectionList = new FluidConnection[fluidConnections.length];
			for (int i = 0; i < fluidConnections.length; i++) {
				energyConnectionList[i] = fluidConnections[i];
			}
			this.fluidConnections = energyConnectionList;
		}
	}
	
	@Override
	public boolean getTanksShared() {
		return areTanksShared;
	}
	
	@Override
	public void setTanksShared(boolean shared) {
		areTanksShared = shared;
	}
	
	@Override
	public IFluidTankProperties[] getTankProperties() {
		if (tanks.length == 0 || tanks == null) return EmptyFluidHandler.EMPTY_TANK_PROPERTIES_ARRAY;
		IFluidTankProperties[] properties = new IFluidTankProperties[tanks.length];
		for (int i = 0; i < tanks.length; i++) {
			properties[i] = new FluidTankProperties(tanks[i].getFluid(), tanks[i].getCapacity(), fluidConnections[i].canFill(), fluidConnections[i].canDrain());
		}
		return properties;
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (tanks.length == 0 || tanks == null) return 0;
		for (int i = 0; i < tanks.length; i++) {
			if (fluidConnections[i].canFill() && tanks[i].isFluidValid(resource) && canFill(resource, i) && tanks[i].getFluidAmount() < tanks[i].getCapacity() && (tanks[i].getFluid() == null || tanks[i].getFluid().isFluidEqual(resource))) {
				return tanks[i].fill(resource, doFill);
			}
		}
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if (tanks.length == 0 || tanks == null) return null;
		for (int i = 0; i < tanks.length; i++) {
			if (fluidConnections[i].canDrain() && tanks[i].getFluid() != null && tanks[i].getFluidAmount() > 0) {
				return tanks[i].drain(resource.amount, doDrain);
			}
		}
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (tanks.length == 0 || tanks == null) return null;
		for (int i = 0; i < tanks.length; i++) {
			if (fluidConnections[i].canDrain() && tanks[i].getFluid() != null && tanks[i].getFluidAmount() > 0) {
				return tanks[i].drain(maxDrain, doDrain);
			}
		}
		return null;
	}
	
	@Override
	public boolean canFill(FluidStack resource, int tankNumber) {
		if (!areTanksShared) return true;
		
		for (int i = 0; i < tanks.length; i++) {
			if (i != tankNumber && fluidConnections[i].canFill() && tanks[i].getFluid() != null) {
				if (tanks[i].getFluid().isFluidEqual(resource)) return false;
			}
		}
		return true;
	}
	
	@Override
	public void clearTank(int tankNo) {
		if (tankNo < tanks.length) tanks[tankNo].setFluidStored(null);
	}
	
	@Override
	public Tank[] getTanks() {
		return tanks;
	}
	
	@Override
	public FluidConnection[] getFluidConnections() {
		return fluidConnections;
	}
	
	@Override
	public BlockPos getBlockPos() {
		return pos;
	}
	
	// Mekanism Gas
	
	/*public int receiveGas(EnumFacing side, GasStack stack, boolean doTransfer) {
		String gasName = stack.getGas().getName();
		Fluid fluid = FluidRegistry.getFluid(gasName);
		if (fluid == null) return 0;
		FluidStack fluidStack = new FluidStack(fluid, 1000);
		
		if (tanks.length == 0 || tanks == null) return 0;
		for (int i = 0; i < tanks.length; i++) {
			if (fluidConnections[i].canFill() && tanks[i].isFluidValid(fluidStack) && canFill(fluidStack, i) && tanks[i].getFluidAmount() < tanks[i].getCapacity() && (tanks[i].getFluid() == null || tanks[i].getFluid().isFluidEqual(fluidStack))) {
				return tanks[i].fill(fluidStack, doTransfer);
			}
		}
		return 0;
	}

	public GasStack drawGas(EnumFacing side, int amount, boolean doTransfer) {
		return null;
	}

	public boolean canReceiveGas(EnumFacing side, Gas gas) {
		Fluid fluid = FluidRegistry.getFluid(gas.getName());
		if (fluid == null) return false;
		if (!areTanksShared) return true;
		
		FluidStack fluidStack = new FluidStack(fluid, 1000);
		for (int i = 0; i < tanks.length; i++) {
			if (fluidConnections[i].canFill() && tanks[i].getFluid() != null) {
				if (tanks[i].getFluidAmount() >= tanks[i].getCapacity() && tanks[i].getFluid().isFluidEqual(fluidStack)) return false;
			}
		}
		return true;
	}

	public boolean canDrawGas(EnumFacing side, Gas type) {
		return false;
	}
	
	public boolean canTubeConnect(EnumFacing side) {
		for (FluidConnection con : this.fluidConnections) {
			if (con.canFill()) return true;
		}
		return false;
	}*/
	
	// NBT
	
	@Override
	public NBTTagCompound writeAll(NBTTagCompound nbt) {
		super.writeAll(nbt);
		if (tanks.length > 0 && tanks != null) for (int i = 0; i < tanks.length; i++) {
			nbt.setInteger("fluidAmount" + i, tanks[i].getFluidAmount());
			nbt.setString("fluidName" + i, tanks[i].getFluidName());
		}
		nbt.setBoolean("areTanksShared", areTanksShared);
		return nbt;
	}
		
	@Override
	public void readAll(NBTTagCompound nbt) {
		super.readAll(nbt);
		if (tanks.length > 0 && tanks != null) for (int i = 0; i < tanks.length; i++) {
			if (nbt.getString("fluidName" + i) == "nullFluid" || nbt.getInteger("fluidAmount" + i) == 0) tanks[i].setFluidStored(null);
			else tanks[i].setFluidStored(FluidRegistry.getFluid(nbt.getString("fluidName" + i)), nbt.getInteger("fluidAmount" + i));
		}
		setTanksShared(nbt.getBoolean("areTanksShared"));
	}
	
	// Fluid Connections
	
	public void setConnection(FluidConnection[] energyConnection) {
		if (tanks.length > 0 && tanks != null) fluidConnections = energyConnection;
	}
	
	public void setConnection(FluidConnection energyConnection, int tankNumber) {
		if (tanks.length > 0 && tanks != null) fluidConnections[tankNumber] = energyConnection;
	}
	
	public void pushFluid() {
		if (tanks.length > 0 && tanks != null) for (int i = 0; i < tanks.length; i++) {
			if (tanks[i].getFluid() == null) return;
			if (tanks[i].getFluidAmount() <= 0 || !fluidConnections[i].canDrain()) return;
			for (EnumFacing side : EnumFacing.VALUES) {
				TileEntity tile = world.getTileEntity(getPos().offset(side));
				if (tile instanceof ITilePassive) if (!((ITilePassive) tile).canPushFluidsTo()) continue;
				IFluidHandler adjStorage = tile == null ? null : tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite());
				
				if (tile instanceof IFluidHandler) {
					tanks[i].drain(((IFluidHandler) tile).fill(tanks[i].drain(tanks[i].getCapacity(), false), true), true);
				}
				else if (adjStorage != null) {
					tanks[i].drain(adjStorage.fill(tanks[i].drain(tanks[i].getCapacity(), false), true), true);
				}
			}
		}
	}
	
	public void spreadFluid() {
		if (!NCConfig.passive_permeation) return;
		if (tanks.length > 0 && tanks != null) for (int i = 0; i < tanks.length; i++) {
			if (tanks[i].getFluid() == null) return;
			if (tanks[i].getFluidAmount() <= 0 || fluidConnections[i] == FluidConnection.NON) return;
			for (EnumFacing side : EnumFacing.VALUES) {
				TileEntity tile = world.getTileEntity(getPos().offset(side));
				if (tile instanceof ITilePassive) if (!((ITilePassive) tile).canPushFluidsTo()) continue;
				IFluidHandler adjStorage = tile == null ? null : tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite());
				
				if (!(tile instanceof IFluidSpread)) continue;
				
				if (tile instanceof IFluidHandler) {
					int maxDrain = tanks[i].getFluidAmount()/2;
					FluidStack stack = ((IFluidHandler) tile).getTankProperties()[0].getContents();
					if (stack != null) maxDrain -= stack.amount/2;
					if (maxDrain > 0) tanks[i].drain(((IFluidHandler) tile).fill(tanks[i].drain(maxDrain, false), true), true);
				}
				else if (adjStorage != null) {
					int maxDrain = tanks[i].getFluidAmount()/2;
					FluidStack stack = adjStorage.getTankProperties()[0].getContents();
					if (stack != null) maxDrain -= stack.amount/2;
					if (maxDrain > 0) tanks[i].drain(adjStorage.fill(tanks[i].drain(tanks[i].getCapacity(), false), true), true);
				}
			}
		}
	}
	
	// Capability
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (CapabilityEnergy.ENERGY == capability && energyConnection.canConnect()) {
			return true;
		}
		if (energyConnection != null && ModCheck.teslaLoaded() && energyConnection.canConnect()) {
			if ((capability == TeslaCapabilities.CAPABILITY_CONSUMER && energyConnection.canReceive()) || (capability == TeslaCapabilities.CAPABILITY_PRODUCER && energyConnection.canExtract()) || capability == TeslaCapabilities.CAPABILITY_HOLDER)
				return true;
		}
		if (tanks.length > 0 && tanks != null) {
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return true;
			//else if (capability == Capabilities.GAS_HANDLER_CAPABILITY) return true;
			//else if (capability == Capabilities.TUBE_CONNECTION_CAPABILITY) return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (CapabilityEnergy.ENERGY == capability && energyConnection.canConnect()) {
			return (T) storage;
		}
		if (energyConnection != null && ModCheck.teslaLoaded() && energyConnection.canConnect()) {
			if ((capability == TeslaCapabilities.CAPABILITY_CONSUMER && energyConnection.canReceive()) || (capability == TeslaCapabilities.CAPABILITY_PRODUCER && energyConnection.canExtract()) || capability == TeslaCapabilities.CAPABILITY_HOLDER)
				return (T) storage;
		}
		if (tanks.length > 0 && tanks != null) {
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
			//if (capability == Capabilities.GAS_HANDLER_CAPABILITY) return Capabilities.GAS_HANDLER_CAPABILITY.cast(this);
			//if (capability == Capabilities.TUBE_CONNECTION_CAPABILITY) return Capabilities.TUBE_CONNECTION_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}
}
