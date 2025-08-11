package net.jed.greenbushmod.util;

import net.jed.greenbushmod.GreenBushMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> METAL_DETECTABLE = tag("metal_detectable");
        public static final TagKey<Block> IGNITABLE = tag("ignitable");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(Objects.requireNonNull(ResourceLocation.tryBuild(GreenBushMod.MOD_ID, name)));
        }
    }

    public static class Items{

        private static TagKey<Item> tag(String name){
            return ItemTags.create(Objects.requireNonNull(ResourceLocation.tryBuild(GreenBushMod.MOD_ID, name)));
        }
    }
}
