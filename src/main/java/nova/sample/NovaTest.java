package nova.sample;

import nova.core.block.Block;
import nova.core.game.Game;
import nova.core.loader.Loadable;
import nova.core.loader.NovaMod;
import nova.core.render.BlockTexture;

/**
 * A test Nova Mod
 * @author Calclavia
 */
@NovaMod(id = "novatest", name = "Nova Test", version = "0.0.1", novaVersion = "0.0.1")
public class NovaTest implements Loadable {

	public Block blockTest;
	public static BlockTexture blockTestTexture;

	@Override
	public void preInit() {
		blockTest = Game.instance.get().blockManager.registerBlock(BlockTest.class);
		blockTestTexture = Game.instance.get().renderManager.registerTexture(new BlockTexture("blockSteel"));
	}
}
