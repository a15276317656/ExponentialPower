package io.github.mosadie.exponentialpower.items;

import io.github.mosadie.exponentialpower.Config;
import io.github.mosadie.exponentialpower.entities.BaseClasses.StorageBE;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class EnderStorageItem extends BlockItem {

    private final static Item.Properties properties = new Item.Properties()
            .stacksTo(1)
            .fireResistant()
            .tab(ItemManager.ITEM_GROUP);

    public EnderStorageItem(Block block, StorageBE.StorageTier tier) {
        super(block, properties);
        this.tier = tier;
    }

    private final StorageBE.StorageTier tier;

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        double energy = 0;

        if (stack.hasTag() && stack.getTag().contains("BlockEntityTag")) {
            CompoundTag blockEntityTag = stack.getTag().getCompound("BlockEntityTag");
            if (blockEntityTag.contains("energy")) {
                energy = blockEntityTag.getDouble("energy");
            }
        }
        
        tooltip.add(Component.translatable("item.exponentialpower.storage.tooltip.stored"));
        tooltip.add(Component.literal(energy + "/" + getMaxEnergy()));
        double percent = ((int)(energy/getMaxEnergy() * 10000.00)) / 100.00;
        tooltip.add(Component.literal("(" + percent + "%)"));
    }

    public double getMaxEnergy() {
        switch (tier) {
            case REGULAR:
                return Config.ENDER_STORAGE_MAX_ENERGY.get();

            case ADVANCED:
                return Config.ADV_ENDER_STORAGE_MAX_ENERGY.get();

            default:
                return Double.MAX_VALUE;
        }
    }
}
