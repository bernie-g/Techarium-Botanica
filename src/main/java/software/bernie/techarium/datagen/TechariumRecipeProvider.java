package software.bernie.techarium.datagen;

import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import net.minecraftforge.fluids.FluidStack;
import software.bernie.techarium.Techarium;
import software.bernie.techarium.integration.ModIntegrations;
import software.bernie.techarium.recipes.recipe.ArboretumRecipe;
import software.bernie.techarium.recipes.recipe.BotariumRecipe;
import software.bernie.techarium.registry.TagRegistry;

import java.util.function.Consumer;

public class TechariumRecipeProvider extends ForgeRecipeProvider {
    public TechariumRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        registerVanillaBotariumRecipes(consumer);
        registerVanillaArboretumRecipes(consumer);
        ModIntegrations.getIntegrations().forEach(wrapper -> wrapper.get().ifPresent(o -> o.generateRecipes(consumer)));
    }

    private void registerVanillaBotariumRecipes(Consumer<IFinishedRecipe> consumer) {
        buildBotariumRecipe(Items.CARROT, Ingredient.of(Items.CARROT), 1000, 800, consumer);
        buildBotariumRecipe(Items.COCOA_BEANS, Ingredient.of(Items.COCOA_BEANS), Ingredient.of(Items.JUNGLE_LOG), 1000, 800, consumer);
        buildBotariumRecipe(Items.POTATO, Ingredient.of(Items.POTATO), 1000, 800, consumer);
        buildBotariumRecipe(Items.WHEAT_SEEDS, Ingredient.of(Items.WHEAT), 1000, 800, consumer);
        buildBotariumRecipe(Items.BEETROOT_SEEDS, Ingredient.of(Items.BEETROOT), 1000, 800, consumer);
        buildBotariumRecipe(Items.SWEET_BERRIES, Ingredient.of(Items.SWEET_BERRIES), 1000, 800, consumer);

        buildBotariumRecipe(Items.SUGAR_CANE, Ingredient.of(Items.SUGAR_CANE), Ingredient.of(Items.SAND), 1000, 600, consumer);
        buildBotariumRecipe(Items.CACTUS, Ingredient.of(Items.CACTUS), Ingredient.of(Items.SAND), 1000, 600, consumer);

        buildBotariumRecipe(Items.NETHER_WART, Ingredient.of(Items.NETHER_WART), Ingredient.of(Blocks.SOUL_SAND), new FluidStack(Fluids.LAVA, 50), 1000, consumer);

        buildBotariumRecipe(Items.RED_MUSHROOM, Ingredient.of(Items.RED_MUSHROOM), Ingredient.of(Items.STONE, Items.MYCELIUM), 50, 1000, consumer);
        buildBotariumRecipe(Items.BROWN_MUSHROOM, Ingredient.of(Items.BROWN_MUSHROOM), Ingredient.of(Items.STONE, Items.MYCELIUM), 50, 1000, consumer);

        buildBotariumRecipe(Items.MELON_SEEDS, Ingredient.of(Items.MELON), 1500, 1200, consumer);
        buildBotariumRecipe(Items.PUMPKIN_SEEDS, Ingredient.of(Items.PUMPKIN), 1500, 1200, consumer);

        buildBotariumRecipe(Items.KELP, Ingredient.of(Items.KELP), 3000, 1000, consumer);

        buildBotariumFlowerRecipe((FlowerBlock)Blocks.DANDELION, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.POPPY, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.BLUE_ORCHID, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.ALLIUM, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.AZURE_BLUET, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.RED_TULIP, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.ORANGE_TULIP, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.WHITE_TULIP, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.PINK_TULIP, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.OXEYE_DAISY, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.CORNFLOWER, consumer);
        buildBotariumFlowerRecipe((FlowerBlock)Blocks.LILY_OF_THE_VALLEY, consumer);

    }

    private void registerVanillaArboretumRecipes(Consumer<IFinishedRecipe> consumer) {
        buildArboretumRecipe(Items.OAK_SAPLING, Ingredient.of(Items.OAK_LOG, Items.OAK_SAPLING), 1000, 1000, consumer);
    }

    public static void buildBotariumFlowerRecipe(FlowerBlock flowerBlock, Consumer<IFinishedRecipe> consumer) {
        buildBotariumRecipe(flowerBlock.asItem(), Ingredient.of(flowerBlock), Ingredient.of(Blocks.GRASS_BLOCK), 1000, 1000, consumer);
    }

    public static void buildBotariumRecipe(Item seed, Ingredient drop, int amountWater, int time, Consumer<IFinishedRecipe> consumer) {
        buildBotariumRecipe(seed, drop, Ingredient.of(TagRegistry.DIRT), amountWater, time, consumer);
    }

    public static void buildBotariumRecipe(Item seed, Ingredient drop, Ingredient soil, int amountWater, int time, Consumer<IFinishedRecipe> consumer) {
        buildBotariumRecipe(seed, drop, soil, new FluidStack(Fluids.WATER, amountWater), time, consumer);
    }

    private static void buildBotariumRecipe(Item seed, Ingredient drop, Ingredient soil, FluidStack fluid, int time, Consumer<IFinishedRecipe> consumer) {
        BotariumRecipe.builder()
                .cropType(Ingredient.of(seed))
                .soilIn(soil)
                .fluidIn(fluid)
                .maxProgress(time)
                .rfPerTick(10)
                .progressPerTick(1)
                .output(drop)
                .construct()
                .build(consumer,
                        new ResourceLocation(Techarium.ModID,
                                "botarium/minecraft/" + seed.getRegistryName().getPath()));
    }

    public void buildArboretumRecipe(Item sapling, Ingredient drop, int amountWater, int time, Consumer<IFinishedRecipe> consumer) {
        buildArboretumRecipe(sapling, drop, Ingredient.of(TagRegistry.DIRT), amountWater, time, consumer);
    }

    public void buildArboretumRecipe(Item sapling, Ingredient drop, Ingredient soil, int amountWater, int time, Consumer<IFinishedRecipe> consumer) {
        buildArboretumRecipe(sapling, drop, soil, new FluidStack(Fluids.WATER, amountWater), time, consumer);
    }

    private void buildArboretumRecipe(Item sapling, Ingredient drop, Ingredient soil, FluidStack fluid, int time, Consumer<IFinishedRecipe> consumer) {
        ArboretumRecipe.builder()
                .cropType(Ingredient.of(sapling))
                .soilIn(soil)
                .fluidIn(fluid)
                .maxProgress(time)
                .rfPerTick(10)
                .progressPerTick(1)
                .output(drop)
                .construct()
                .build(consumer,
                        new ResourceLocation(Techarium.ModID,
                                "arboretum/minecraft/" + sapling.getRegistryName().getPath()));
    }
}
