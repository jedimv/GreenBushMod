package net.jed.greenbushmod.item;

import net.jed.greenbushmod.GreenBushMod;
import net.jed.greenbushmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GreenBushMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GREEN_BUSH_TAB = CREATIVE_MODE_TAB.register("green_bush_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.GREEN_BUSH.get()))
                    .title(Component.translatable("creativetab.green_bush_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.FIRE_STARTER.get());
                        output.accept((ModItems.HANDLE_METAL_DETECTOR.get()));

                        output.accept(ModBlocks.GREEN_BUSH.get());
                        output.accept(ModBlocks.LOOSE_PEBBLE.get());
                        output.accept(ModBlocks.THATCH_BLOCK.get());
                        output.accept(ModBlocks.POTTERY_WHEEL.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
