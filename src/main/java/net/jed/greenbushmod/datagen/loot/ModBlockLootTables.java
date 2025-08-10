package net.jed.greenbushmod.datagen.loot;

import net.jed.greenbushmod.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.POTTERY_WHEEL.get());
        this.dropSelf(ModBlocks.LOOSE_PEBBLE.get());
        this.dropSelf(ModBlocks.THATCH_BLOCK.get());

        this.add(ModBlocks.GREEN_BUSH.get(),
                block -> createGrassLikeDrops(ModBlocks.GREEN_BUSH.get(), Items.STICK));
    }

    protected LootTable.Builder createGrassLikeDrops(Block pBlock, Item item) {
        return createShearsDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item).when(LootItemRandomChanceCondition.randomChance(0.250F)).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
