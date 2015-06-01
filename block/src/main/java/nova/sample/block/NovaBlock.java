package nova.sample.block;

import nova.core.block.BlockFactory;
import nova.core.block.BlockManager;
import nova.core.game.Game;
import nova.core.item.ItemFactory;
import nova.core.item.ItemManager;
import nova.core.loader.Loadable;
import nova.core.loader.NovaMod;
import nova.core.nativewrapper.NativeManager;
import nova.core.recipes.crafting.ItemIngredient;
import nova.core.recipes.crafting.ShapedCraftingRecipe;
import nova.core.render.RenderManager;
import nova.core.render.model.ModelProvider;
import nova.core.render.model.TechneModel;
import nova.core.render.texture.BlockTexture;
import nova.core.render.texture.EntityTexture;

/**
 * A test Nova Mod
 * 
 * @author Calclavia
 */
@NovaMod(id = NovaBlock.id, name = "Nova Example Block", version = "0.0.1", novaVersion = "0.0.1")
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

	public final BlockManager blockManager;
	public final ItemManager itemManager;
	public final RenderManager renderManager;
	public final NativeManager nativeManager;

	public NovaBlock(BlockManager blockManager, ItemManager itemManager, RenderManager renderManager, NativeManager nativeManager) {
		this.blockManager = blockManager;
		this.itemManager = itemManager;
		this.renderManager = renderManager;
		this.nativeManager = nativeManager;
	}

	@Override
	public void preInit() {
		blockStateful = blockManager.register(BlockStateful.class);
		blockStateless = blockManager.register(BlockStateless.class);

		itemBlockStateful = itemManager.getItemFromBlock(blockStateful);
		itemBlockStateless = itemManager.getItemFromBlock(blockStateless);

		steelTexture = renderManager.registerTexture(new BlockTexture(id, "blockSteel"));
		grinderTexture = renderManager.registerTexture(new BlockTexture(id, "grinder"));

		grinderEntityTexture = renderManager.registerTexture(new EntityTexture(id, "grinderEntity"));

		grinderModel = renderManager.registerModel(new TechneModel(id, "grinder"));

		// try to add a recipe
		ItemIngredient stickIngredient = ItemIngredient.forItem("minecraft:stick"); //TODO: This should be obtained from some dictonary too
		ItemIngredient ingotIngredient = ItemIngredient.forDictionary("ingotIron");

		Game.recipeManager().addRecipe(new ShapedCraftingRecipe(itemBlockStateless.makeItem(), "AAA-ABA-AAA", ingotIngredient, stickIngredient));
	}
}
