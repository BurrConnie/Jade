package snownee.jade.impl.ui;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.ui.Element;
import snownee.jade.overlay.DisplayHelper;
import snownee.jade.overlay.OverlayRenderer;

public class TextElement extends Element {

	public final Component component;

	public TextElement(Component component) {
		this.component = component;
	}

	@Override
	public Vec2 getSize() {
		Font font = Minecraft.getInstance().font;
		return new Vec2(font.width(component), font.lineHeight + 1);
	}

	@Override
	public void render(PoseStack matrixStack, float x, float y, float maxX, float maxY) {
		DisplayHelper.INSTANCE.drawText(matrixStack, component, x, y, OverlayRenderer.normalTextColorRaw);
	}

	@Override
	public @Nullable Component getMessage() {
		return component;
	}

}
