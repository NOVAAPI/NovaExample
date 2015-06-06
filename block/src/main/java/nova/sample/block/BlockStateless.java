package nova.sample.block;

import nova.core.block.Block;
import nova.core.block.component.StaticBlockRenderer;
import nova.core.component.Category;
import nova.core.component.misc.Collider;
import nova.core.component.renderer.ItemRenderer;
import nova.core.network.Syncable;
import nova.core.network.Packet;
import nova.core.util.Identifiable;

import java.util.Optional;

/**
 * Literally, this is a test block.
 * @author Calclavia
 */
public class BlockStateless extends Block implements Syncable {

	public BlockStateless() {
		add(new StaticBlockRenderer(this)).setTexture((dir) -> Optional.of(NovaBlock.steelTexture));

		add(new Collider());

		add(new ItemRenderer(this));

		add(new Category("buildingBlocks"));
		rightClickEvent.add(this::onRightClick);
	}

	public void onRightClick(RightClickEvent evt) {
		System.out.println("Sending Packet: 1234");
		NovaBlock.networkManager.sync(this);
	}

	@Override
	public void read(Packet packet) {
		System.out.println("Received packet: " + packet.readInt());
	}

	@Override
	public void write(Packet packet) {
		packet.writeInt(1234);
	}

	@Override
	public String getID() {
		return "simple";
	}

	@Override
	public boolean sameType(Identifiable other) {
		return other.getID().equals(getID());
	}
}
