package net.jed.greenbushmod.block;

import net.jed.greenbushmod.GreenBushMod;
import net.jed.greenbushmod.block.custom.PotteryWheel;
import net.jed.greenbushmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GreenBushMod.MOD_ID);

    // 'NATURAL_GENERATED' BLOCKS
    public static final RegistryObject<Block> GREEN_BUSH = registerBlock("green_bush",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS)));
    public static final RegistryObject<Block> LOOSE_PEBBLE = registerBlock("loose_pebble",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    // 'CRAFTED_DECORATIVE' BLOCKS
    public static final RegistryObject<Block> THATCH_BLOCK = registerBlock("thatch_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK))); // converter esse bloco para um customblock, para que não tenha colisão A NÃO SER pela chuva (++efeito de goteira abaixo)

    // 'STATIONS' BLOCKS
    public static final RegistryObject<Block> POTTERY_WHEEL = registerBlock("pottery_wheel",
            () -> new PotteryWheel(BlockBehaviour.Properties.copy(Blocks.GRINDSTONE).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
