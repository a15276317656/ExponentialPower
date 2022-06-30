package io.github.mosadie.exponentialpower.setup;

import io.github.mosadie.exponentialpower.ExponentialPower;
import io.github.mosadie.exponentialpower.blocks.AdvancedEnderGenerator;
import io.github.mosadie.exponentialpower.blocks.AdvancedEnderStorage;
import io.github.mosadie.exponentialpower.blocks.EnderGenerator;
import io.github.mosadie.exponentialpower.blocks.EnderStorage;
import io.github.mosadie.exponentialpower.container.ContainerEnderGeneratorTE;
import io.github.mosadie.exponentialpower.items.EnderCell;
import io.github.mosadie.exponentialpower.items.EnderStorageItem;
import io.github.mosadie.exponentialpower.tiles.AdvancedEnderGeneratorTE;
import io.github.mosadie.exponentialpower.tiles.AdvancedEnderStorageTE;
import io.github.mosadie.exponentialpower.tiles.BaseClasses.GeneratorTE;
import io.github.mosadie.exponentialpower.tiles.BaseClasses.StorageTE;
import io.github.mosadie.exponentialpower.tiles.EnderGeneratorTE;
import io.github.mosadie.exponentialpower.tiles.EnderStorageTE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.mosadie.exponentialpower.items.ItemManager.ITEM_GROUP;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExponentialPower.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExponentialPower.MODID);
    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ExponentialPower.MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ExponentialPower.MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Blocks

    public static final RegistryObject<Block> ENDER_GENERATOR = BLOCKS.register("ender_generator", EnderGenerator::new);
    public static final RegistryObject<Block> ADV_ENDER_GENERATOR = BLOCKS.register("advanced_ender_generator", AdvancedEnderGenerator::new);

    public static final RegistryObject<Block> ENDER_STORAGE = BLOCKS.register("ender_storage", EnderStorage::new);
    public static final RegistryObject<Block> ADV_ENDER_STORAGE = BLOCKS.register("advanced_ender_storage", AdvancedEnderStorage::new);

    // Items

    public static final RegistryObject<Item> ENDER_CELL = ITEMS.register("ender_cell", EnderCell::new);

    public static final RegistryObject<Item> ENDER_GENERATOR_ITEM = ITEMS.register("ender_generator", () -> new BlockItem(ENDER_GENERATOR.get(), new Item.Properties().fireResistant().tab(ITEM_GROUP)));
    public static final RegistryObject<Item> ADV_ENDER_GENERATOR_ITEM = ITEMS.register("advanced_ender_generator", () -> new BlockItem(ADV_ENDER_GENERATOR.get(), new Item.Properties().fireResistant().tab(ITEM_GROUP)));
    public static final RegistryObject<Item> ENDER_STORAGE_ITEM = ITEMS.register("ender_storage", () -> new EnderStorageItem(ENDER_STORAGE.get(), StorageTE.StorageTier.REGULAR));
    public static final RegistryObject<Item> ADV_ENDER_STORAGE_ITEM = ITEMS.register("advanced_ender_storage", () -> new EnderStorageItem(ADV_ENDER_STORAGE.get(), StorageTE.StorageTier.ADVANCED));

    // Tile Entities

    public static final RegistryObject<BlockEntityType<EnderGeneratorTE>> ENDER_GENERATOR_TE = TILES.register("ender_generator", () -> BlockEntityType.Builder.of(EnderGeneratorTE::new, ENDER_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<AdvancedEnderGeneratorTE>> ADV_ENDER_GENERATOR_TE = TILES.register("advanced_ender_generator", () -> BlockEntityType.Builder.of(AdvancedEnderGeneratorTE::new, ADV_ENDER_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<EnderStorageTE>> ENDER_STORAGE_TE = TILES.register("ender_storage", () -> BlockEntityType.Builder.of(EnderStorageTE::new, ENDER_STORAGE.get()).build(null));
    public static final RegistryObject<BlockEntityType<AdvancedEnderStorageTE>> ADV_ENDER_STORAGE_TE = TILES.register("advanced_ender_storage", () -> BlockEntityType.Builder.of(AdvancedEnderStorageTE::new, ADV_ENDER_STORAGE.get()).build(null));

    // Containers

    public static final RegistryObject<MenuType<ContainerEnderGeneratorTE>> ENDER_GENERATOR_CONTAINER = CONTAINERS.register("ender_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level level = inv.player.getLevel();
        GeneratorTE te = (GeneratorTE) level.getBlockEntity(pos);
        return new ContainerEnderGeneratorTE(windowId, inv, te);
    }));
}
