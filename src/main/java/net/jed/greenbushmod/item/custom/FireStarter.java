package net.jed.greenbushmod.item.custom;

import net.jed.greenbushmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class FireStarter extends Item {
    private static final int CHARGE_TIME = 75;

    public FireStarter(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return CHARGE_TIME;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity livingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if (!(livingEntity instanceof  Player player)) return;

        int usedTicks = this.getUseDuration(pStack) - pRemainingUseDuration;

        if(pLevel.isClientSide)spawnParticles(pLevel, player, usedTicks);

        if(pRemainingUseDuration <= 1){
            this.tryIgnite(pLevel, player, pStack);
            player.stopUsingItem();
        }
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity livingEntity, int pTimeCharged) {
        if (!(livingEntity instanceof  Player player)) return;

        int usedTicks = this.getUseDuration(pStack) - pTimeCharged;

        if (usedTicks >= CHARGE_TIME - 5){
            this.tryIgnite(pLevel, player, pStack);
        } else {
            playFailEffect(pLevel, player, pStack);
        }
    }

    private void spawnParticles(Level level, Player player, int usedTicks){
        Vec3 look = player.getLookAngle();
        Vec3 eyePos = player.getEyePosition();
        double px = eyePos.x + look.x * 1.2;
        double py = eyePos.y + look.y * 1.2;
        double pz = eyePos.z + look.z * 1.2;

        if (usedTicks < 15){
            level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, px, py, pz, 0, 0.01, 0);
        } else if (usedTicks < 30){
            level.addParticle(ParticleTypes.SMOKE, px, py, pz, 0, 0.01, 0);
            level.addParticle(ParticleTypes.CRIT, px, py, pz, 0, 0.01, 0);
        } else {
            level.addParticle(ParticleTypes.SMOKE, px, py, pz, 0, 0.01, 0);
            level.addParticle(ParticleTypes.CRIT, px, py, pz, 0, 0.01, 0);
            level.addParticle(ParticleTypes.FLAME, px, py, pz, 0, 0.01, 0);
        }
    }

    private boolean tryIgnite(Level level, Player player, ItemStack stack) {
        HitResult hitResult = player.pick(5.0D, 0.0F, false);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hitResult;
            BlockPos clickedPos = blockHit.getBlockPos();
            BlockState clickedBlock = level.getBlockState(clickedPos);

            var clickedFace = blockHit.getDirection();
            if (clickedBlock.is(ModTags.Blocks.IGNITABLE)) {
                Property<?> property = clickedBlock.getBlock().getStateDefinition().getProperty("lit");
                if (property instanceof BooleanProperty litProperty) {
                    if (!clickedBlock.getValue(litProperty)) {
                        level.setBlock(clickedPos, clickedBlock.setValue(litProperty, true), 11);
                        level.playSound(null, clickedPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
                        return true;
                    }
                } else {
                    BlockPos firePos = clickedPos.relative(clickedFace);
                    if (level.isEmptyBlock(firePos)) {
                        level.setBlock(firePos, Blocks.FIRE.defaultBlockState(), 11);
                        level.playSound(null, firePos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
                        return true;
                    }
                }
            }
            else {
                BlockPos firePos = clickedPos.relative(clickedFace);
                if (level.isEmptyBlock(firePos)) {
                    level.setBlock(firePos, Blocks.FIRE.defaultBlockState(), 11);
                    level.playSound(null, firePos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
                    return true;
                }
            }
        }
        return false;
    }


    private void playFailEffect(Level level, Player player, ItemStack stack){
        if (!level.isClientSide){
            HitResult hitResult = player.pick(5.0D,0.0F,false);
            if (hitResult.getType() == HitResult.Type.BLOCK){
                BlockHitResult blockHit = (BlockHitResult) hitResult;
                BlockPos pos = blockHit.getBlockPos().relative(blockHit.getDirection());

                Vec3 look = player.getLookAngle();
                Vec3 eyePos = player.getEyePosition();

                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
                level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        eyePos.x + look.x * 1.2, eyePos.y + look.y * 1.2, eyePos.z + look.z * 1.2,
                        0, 0.01, 0);
            }
        }
    }
}
