package nova.sample.worldgen;

import nova.core.block.BlockFactory;
import nova.core.block.BlockManager;
import nova.core.item.ItemFactory;
import nova.core.item.ItemManager;
import nova.core.loader.Loadable;
import nova.core.loader.Mod;
import nova.core.recipes.RecipeManager;
import nova.core.render.RenderManager;
import nova.core.render.texture.BlockTexture;
import nova.core.render.texture.ItemTexture;
import nova.worldgen.WorldgenManager;
import nova.worldgen.ore.Ore;
import nova.worldgen.ore.OreHeight;
import nova.worldgen.util.EnumSelector;

@Mod(id = NovaWorldgen.id, name = "Nova Worldgen Example", version = "0.0.1", novaVersion = "0.0.1")
public class NovaWorldgen implements Loadable {
	public static final String id = "novaexampleworldgen";

	public static BlockFactory blockSteelOre;

	public static ItemFactory itemBlockSteelOre;
	public static ItemFactory itemSteelIngot;

	public static Ore oreSteel;

	public static BlockTexture steelOreTexture;
	public static ItemTexture steelIngotTexture;

	public final BlockManager blockManager;
	public final ItemManager itemManager;
	public final RenderManager renderManager;
	public final RecipeManager recipeManager;
	public final WorldgenManager worldgenManager;

	public NovaWorldgen(BlockManager blockManager,
	                    ItemManager itemManager,
	                    RenderManager renderManager,
	                    RecipeManager recipeManager,
	                    WorldgenManager worldgenManager) {
		this.blockManager = blockManager;
		this.itemManager = itemManager;
		this.renderManager = renderManager;
		this.recipeManager = recipeManager;
		this.worldgenManager = worldgenManager;
	}

	@Override
	public void preInit() {
		steelOreTexture = renderManager.registerTexture(new BlockTexture(id, "ore_steel"));
		steelIngotTexture = renderManager.registerTexture(new ItemTexture(id, "ingot_steel"));

		blockSteelOre = blockManager.register("steel_ore", BlockSteelOre::new);
		itemSteelIngot = itemManager.register("steel_ingot", ItemSteelIngot::new);

		itemBlockSteelOre = itemManager.getItemFromBlock(blockSteelOre);

		EnumSelector<OreHeight> oreSteelEnumSelector = EnumSelector.of(OreHeight.class);
		oreSteelEnumSelector.blockAll();
		oreSteelEnumSelector.apart(OreHeight.DEEP);
		oreSteelEnumSelector.lock();
		oreSteel = worldgenManager.register(new Ore("steel_ore", blockSteelOre, 1, 1, oreSteelEnumSelector));
	}
}
