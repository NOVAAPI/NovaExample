/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nova.sample.worldgen;

import nova.core.component.renderer.ItemRenderer;
import nova.core.item.Item;

/**
 *
 * @author ExE Boss
 */
public class ItemSteelIngot extends Item {

	public ItemSteelIngot() {
		components.add(new ItemRenderer(this)).setTexture(NovaWorldgen.steelIngotTexture);
	}
}
