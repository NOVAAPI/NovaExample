package nova.sample.block;

import nova.core.block.BlockFactory;
import nova.core.block.BlockManager;
import nova.core.item.ItemFactory;
import nova.core.item.ItemManager;
import nova.core.loader.Loadable;
import nova.core.loader.Mod;
import nova.core.network.NetworkManager;
import nova.core.recipes.RecipeManager;
import nova.core.recipes.crafting.ItemIngredient;
import nova.core.recipes.crafting.ShapedCraftingRecipe;
import nova.core.render.RenderManager;
import nova.core.render.model.ModelProvider;
import nova.core.render.model.TechneModelProvider;
import nova.core.render.texture.BlockTexture;
import nova.core.render.texture.EntityTexture;

/**
 * A test Nova Mod
 *
 * @author Calclavia
 */
@Mod(id = NovaBlock.id, name = "Nova Example Block", version = "0.0.1", novaVersion = "0.0.1")
public class NovaBlock implements Loadable {

	public static final String id = "novablock";

	public static BlockFactory blockStateful;
	public static BlockFactory blockStateless;

	public static ItemFactory itemBlockStateful;
	public static ItemFactory itemBlockStateless;

	public static BlockTexture steelTexture;
	public static BlockTexture grinderTexture;

	public static EntityTexture grinderEntityTexture;

	public static ModelProvider grinderModel;

	public static NetworkManager networkManager;

	public final BlockManager blockManager;
	public final ItemManager itemManager;
	public final RenderManager renderManager;
	public final RecipeManager recipeManager;

	public NovaBlock(BlockManager blockManager,
					 ItemManager itemManager,
					 RenderManager renderManager,
					 NetworkManager networkManager,
					 RecipeManager recipeManager) {
		this.blockManager = blockManager;
		this.itemManager = itemManager;
		this.renderManager = renderManager;
		this.recipeManager = recipeManager;

		NovaBlock.networkManager = networkManager;
	}

	@Override
	public void preInit() {
		steelTexture = renderManager.registerTexture(new BlockTexture(id, "block_steel"));
		grinderTexture = renderManager.registerTexture(new BlockTexture(id, "grinder"));

		blockStateful = blockManager.register("stateful", BlockStateful::new);
		blockStateless = blockManager.register("simple", BlockStateless::new);

		itemBlockStateful = itemManager.getItemFromBlock(blockStateful);
		itemBlockStateless = itemManager.getItemFromBlock(blockStateless);

		grinderEntityTexture = renderManager.registerTexture(new EntityTexture(id, "grinder_entity"));

		grinderModel = renderManager.registerModel(new TechneModelProvider(id, "grinder"));

		// try to add a recipe
		ItemIngredient stickIngredient = ItemIngredient.forItem("minecraft:stick"); //TODO: This should be obtained from some dictonary too
		ItemIngredient ingotIngredient = ItemIngredient.forDictionary("ingotIron");

		recipeManager.addRecipe(new ShapedCraftingRecipe(itemBlockStateless.build(), "AAA-ABA-AAA", ingotIngredient, stickIngredient));
	}
}
