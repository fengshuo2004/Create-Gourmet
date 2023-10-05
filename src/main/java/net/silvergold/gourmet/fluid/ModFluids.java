package net.silvergold.gourmet.fluid;

import net.silvergold.gourmet.Gourmet;
import net.silvergold.gourmet.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    //Seed Oil
    public static final ResourceLocation SEED_OIL_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation SEED_OIL_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation SEED_OIL_RL = new ResourceLocation(Gourmet.MOD_ID, "misc/seed_oil_overlay");
    //Batter
    public static final ResourceLocation BATTER_STILL_RL = new ResourceLocation(Gourmet.MOD_ID, "misc/batter_still");
    public static final ResourceLocation BATTER_FLOWING_RL = new ResourceLocation(Gourmet.MOD_ID, "misc/batter_flow");
    public static final ResourceLocation BATTER_RL = new ResourceLocation(Gourmet.MOD_ID, "misc/seed_oil_overlay");

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Gourmet.MOD_ID);
    public static final RegistryObject<FlowingFluid> SOURCE_SEED_OIL = FLUIDS.register("seed_oil",
            () -> new ForgeFlowingFluid.Source(ModFluids.SEED_OIL_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SEED_OIL = FLUIDS.register("flowing_seed_oil",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SEED_OIL_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SOURCE_BATTER = FLUIDS.register("batter",
            () -> new ForgeFlowingFluid.Source(ModFluids.BATTER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BATTER = FLUIDS.register("flowing_batter",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.BATTER_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SEED_OIL_FLUID_PROPERTIES =
        new ForgeFlowingFluid.Properties(SOURCE_SEED_OIL, FLOWING_SEED_OIL,
            FluidAttributes.builder(SEED_OIL_STILL_RL, SEED_OIL_FLOWING_RL).density(15).luminosity(2)
            .viscosity(5).overlay(SEED_OIL_RL).color(0xA1DCB506)); //ADD .bucket(ModItems.SEED_OIL_BUCKET)

    public static final ForgeFlowingFluid.Properties BATTER_FLUID_PROPERTIES = 
        new ForgeFlowingFluid.Properties(SOURCE_BATTER, FLOWING_BATTER,
            FluidAttributes.builder(BATTER_STILL_RL, BATTER_FLOWING_RL).density(15).luminosity(2)
            .viscosity(5).overlay(BATTER_RL).color(0xFFFFFFFF)).bucket(ModItems.BATTER_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}