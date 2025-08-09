package net.jed.greenbushmod.block.datagen;

import net.jed.greenbushmod.GreenBushMod;
import net.jed.greenbushmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GreenBushMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.GREEN_BUSH);
        blockWithItem(ModBlocks.LOOSE_PEBBLE);
        blockWithItem(ModBlocks.THATCH_BLOCK);

        simpleBlockWithItem(ModBlocks.POTTERY_WHEEL.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/pottery_wheel")));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
