package net.mcreator.gorp.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.gorp.world.inventory.GorpophoneScreenMenu;
import net.mcreator.gorp.procedures.GorpophoneScreenValueProcedure;
import net.mcreator.gorp.procedures.GorpophoneRequirementTooltipDisplayProcedure;
import net.mcreator.gorp.network.GorpophoneScreenButtonMessage;
import net.mcreator.gorp.init.GorpModScreens;
import net.mcreator.gorp.GorpMod;

import java.util.stream.Collectors;
import java.util.Arrays;

import com.mojang.blaze3d.systems.RenderSystem;

public class GorpophoneScreenScreen extends AbstractContainerScreen<GorpophoneScreenMenu> implements GorpModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox gorpcastmsg;
	private ImageButton imagebutton_button;

	public GorpophoneScreenScreen(GorpophoneScreenMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("gorpcastmsg"))
				gorpcastmsg.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = new ResourceLocation("gorp:textures/screens/gorpophone_screen.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		gorpcastmsg.render(guiGraphics, mouseX, mouseY, partialTicks);
		boolean customTooltipShown = false;
		if (GorpophoneRequirementTooltipDisplayProcedure.execute(entity))
			if (mouseX > leftPos + 119 && mouseX < leftPos + 143 && mouseY > topPos + 24 && mouseY < topPos + 48) {
				String hoverText = GorpophoneScreenValueProcedure.execute(entity);
				if (hoverText != null) {
					guiGraphics.renderComponentTooltip(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
				}
				customTooltipShown = true;
			}
		if (!customTooltipShown)
			this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(new ResourceLocation("gorp:textures/screens/empty_gorp_coin_slot.png"), this.leftPos + 80, this.topPos + 28, 0, 0, 16, 16, 16, 16);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (gorpcastmsg.isFocused())
			return gorpcastmsg.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String gorpcastmsgValue = gorpcastmsg.getValue();
		super.resize(minecraft, width, height);
		gorpcastmsg.setValue(gorpcastmsgValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.gorp.gorpophone_screen.label_gorpophone"), 8, 6, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		gorpcastmsg = new EditBox(this.font, this.leftPos + 8, this.topPos + 59, 160, 18, Component.translatable("gui.gorp.gorpophone_screen.gorpcastmsg"));
		gorpcastmsg.setMaxLength(8192);
		gorpcastmsg.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "gorpcastmsg", content, false);
		});
		gorpcastmsg.setHint(Component.translatable("gui.gorp.gorpophone_screen.gorpcastmsg"));
		this.addWidget(this.gorpcastmsg);
		imagebutton_button = new ImageButton(this.leftPos + 121, this.topPos + 27, 20, 18, 0, 0, 18, new ResourceLocation("gorp:textures/screens/atlas/imagebutton_button.png"), 20, 36, e -> {
			int x = GorpophoneScreenScreen.this.x;
			int y = GorpophoneScreenScreen.this.y;
			if (true) {
				GorpMod.PACKET_HANDLER.sendToServer(new GorpophoneScreenButtonMessage(0, x, y, z));
				GorpophoneScreenButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_button);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		gorpcastmsg.tick();
	}
}