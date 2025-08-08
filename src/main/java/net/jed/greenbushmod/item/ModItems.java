package net.jed.greenbushmod.item;

import net.jed.greenbushmod.GreenBushMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GreenBushMod.MOD_ID);

    public static final RegistryObject<Item> GREEN_BUSH = ITEMS.register("green_bush",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LOOSE_STONE = ITEMS.register("loose_stone",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
