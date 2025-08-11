package net.jed.greenbushmod.datagen;

import net.jed.greenbushmod.GreenBushMod;
import net.jed.greenbushmod.block.ModBlocks;
import net.jed.greenbushmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, GreenBushMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTABLE).addTag(Tags.Blocks.ORES);

        // FLAMABLE BLOCKS
        this.tag(ModTags.Blocks.IGNITABLE)
                .addTag(BlockTags.CAMPFIRES)
                .addTag(BlockTags.CANDLES)
                .addTag(BlockTags.LOGS)
                .addTag(BlockTags.PLANKS)
                .addTag(BlockTags.LEAVES)
                .addTag(BlockTags.WOOL)
                .addTag(BlockTags.BEDS);
        // .add(ModBlocks.THIS_MOD_ORE_BLOCK.get()) <- adicionar isso para bloco ore custom do mod

        //this.tag(BlockTags.MINEABLE_WITH_PICKAXE) // .add(ModBlocks.THIS_MOD_BLOCK.get()) <- adicionar isso para bloco custom do mod
        //this.tag(BlockTags.MINEABLE_WITH_SHOVEL) // .add(ModBlocks.THIS_MOD_BLOCK.get()) <- adicionar isso para bloco custom do mod
        //this.tag(BlockTags.MINEABLE_WITH_AXE) // .add(ModBlocks.THIS_MOD_BLOCK.get()) <- adicionar isso para bloco custom do mod
        //this.tag(BlockTags.MINEABLE_WITH_HOE) // .add(ModBlocks.THIS_MOD_BLOCK.get()) <- adicionar isso para bloco custom do mod


        //this.tag(BlockTags.NEEDS_DIAMOND_TOOL) // .add(ModBlocks.THIS_MOD_BLOCK.get()) <- adicionar isso para bloco custom do mod
        //this.tag(BlockTags.NEEDS_IRON_TOOL)  // .add(ModBlocks.THIS_MOD_BLOCK.get()) <- adicionar isso para bloco custom do mod
        //this.tag(BlockTags.NEEDS_STONE_TOOL) // .add(ModBlocks.THIS_MOD_BLOCK.get()) <- adicionar isso para bloco custom do mod
        //this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL) // .add(ModBlocks.THIS_MOD_BLOCK.get()) <- adicionar isso para bloco custom do mod

    }
}
