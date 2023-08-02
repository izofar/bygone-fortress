package com.izofar.bygonefortress;

import com.izofar.bygonefortress.event.ModWorldEvents;
import com.izofar.bygonefortress.init.*;
import com.izofar.bygonefortress.util.ModStructureUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BygoneNetherMod.MODID)
public class BygoneNetherMod
{
    public static final String MODID = "bygonefortress";
    public static final Logger LOGGER = LogManager.getLogger();

    public BygoneNetherMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModStructures.register(eventBus);
        ModFeatures.register(eventBus);

        MinecraftForge.EVENT_BUS.register(ModWorldEvents.class);

        eventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModProcessors.registerProcessors();
            ModStructures.setupStructures();
            ModConfiguredStructures.registerConfiguredStructures();
            ModConfiguredFeatures.registerConfiguredFeatures();
            ModStructureUtils.addBasaltRestrictions();
        });
    }
}
