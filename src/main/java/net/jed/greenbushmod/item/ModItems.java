package net.jed.greenbushmod.item;

import net.jed.greenbushmod.GreenBushMod;
import net.jed.greenbushmod.item.custom.FireStarter;
import net.jed.greenbushmod.item.custom.HandleMetalDetector;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GreenBushMod.MOD_ID);

    public static final RegistryObject<Item> STRAW = ITEMS.register("straw",
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HANDLE_METAL_DETECTOR = ITEMS.register("handle_metal_detector",
            () -> new HandleMetalDetector(new Item.Properties().durability(64)));
    public static final RegistryObject<Item> FIRE_STARTER = ITEMS.register("fire_starter",
            () -> new FireStarter(new Item.Properties().durability(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
