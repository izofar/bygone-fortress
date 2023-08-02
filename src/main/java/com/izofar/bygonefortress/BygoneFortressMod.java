package com.izofar.bygonefortress;

import com.izofar.bygonefortress.init.*;
import com.izofar.bygonefortress.util.ModStructureUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BygoneFortressMod.MODID)
public class BygoneFortressMod
{
    public static final String MODID = "bygonefortress";
    public static final Logger LOGGER = LogManager.getLogger();

    public BygoneFortressMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModStructures.register(eventBus);
        ModFeatures.register(eventBus);

        eventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModProcessors.registerProcessors();
            ModStructureUtils.addBasaltRestrictions();
            ModTags.initTags();
        });
    }

}
