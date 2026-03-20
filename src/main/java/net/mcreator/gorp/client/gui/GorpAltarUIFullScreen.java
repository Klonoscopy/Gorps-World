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

import net.mcreator.gorp.world.inventory.GorpAltarUIFullMenu;
import net.mcreator.gorp.procedures.GorpAltarTypeSelectorProcedure;
import net.mcreator.gorp.procedures.GorpAltarMoltenSelectorProcedure;
import net.mcreator.gorp.procedures.GorpAltarIcySelectorProcedure;
import net.mcreator.gorp.network.GorpAltarUIFullButtonMessage;
import net.mcreator.gorp.init.GorpModScreens;
import net.mcreator.gorp.GorpMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class GorpAltarUIFullScreen extends AbstractContainerScreen<GorpAltarUIFullMenu> implements GorpModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox GorpMessage;
	private EditBox GorpResponse;
	private ImageButton imagebutton_gorp;

	public GorpAltarUIFullScreen(GorpAltarUIFullMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 276;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("GorpMessage"))
				GorpMessage.setValue(stringState);
			else if (name.equals("GorpResponse"))
				GorpResponse.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = new ResourceLocation("gorp:textures/screens/gorp_altar_ui_full.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		GorpMessage.render(guiGraphics, mouseX, mouseY, partialTicks);
		GorpResponse.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		if (GorpAltarMoltenSelectorProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("gorp:textures/screens/molten_gorp_bubble.png"), this.leftPos + 12, this.topPos + 101, 0, 0, 26, 20, 26, 20);
		}
		if (GorpAltarIcySelectorProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("gorp:textures/screens/icy_gorp_bubble.png"), this.leftPos + 12, this.topPos + 101, 0, 0, 26, 20, 26, 20);
		}
		if (GorpAltarTypeSelectorProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(new ResourceLocation("gorp:textures/screens/gorp_bubble.png"), this.leftPos + 12, this.topPos + 101, 0, 0, 26, 20, 26, 20);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (GorpMessage.isFocused())
			return GorpMessage.keyPressed(key, b, c);
		if (GorpResponse.isFocused())
			return GorpResponse.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String GorpMessageValue = GorpMessage.getValue();
		String GorpResponseValue = GorpResponse.getValue();
		super.resize(minecraft, width, height);
		GorpMessage.setValue(GorpMessageValue);
		GorpResponse.setValue(GorpResponseValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.gorp.gorp_altar_ui_full.label_gorp"), 10, 7, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.gorp.gorp_altar_ui_full.label_the_altar_allows_you_to_speak_wi"), 58, 30, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.gorp.gorp_altar_ui_full.label_with_gorp"), 58, 40, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.gorp.gorp_altar_ui_full.label_say_hello_to_gorp"), 58, 87, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.gorp.gorp_altar_ui_full.label_the_language_of_gorps_to_english"), 58, 50, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.gorp.gorp_altar_ui_full.label_channeled_energy_from_the_ground"), 58, 60, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.gorp.gorp_altar_ui_full.label_ground"), 58, 70, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		GorpMessage = new EditBox(this.font, this.leftPos + 11, this.topPos + 139, 230, 18, Component.translatable("gui.gorp.gorp_altar_ui_full.GorpMessage"));
		GorpMessage.setMaxLength(8192);
		GorpMessage.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "GorpMessage", content, false);
		});
		GorpMessage.setHint(Component.translatable("gui.gorp.gorp_altar_ui_full.GorpMessage"));
		this.addWidget(this.GorpMessage);
		GorpResponse = new EditBox(this.font, this.leftPos + 40, this.topPos + 107, 225, 18, Component.translatable("gui.gorp.gorp_altar_ui_full.GorpResponse"));
		GorpResponse.setMaxLength(8192);
		GorpResponse.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "GorpResponse", content, false);
		});
		GorpResponse.setHint(Component.translatable("gui.gorp.gorp_altar_ui_full.GorpResponse"));
		this.addWidget(this.GorpResponse);
		imagebutton_gorp = new ImageButton(this.leftPos + 246, this.topPos + 139, 20, 18, 0, 0, 18, new ResourceLocation("gorp:textures/screens/atlas/imagebutton_gorp.png"), 20, 36, e -> {
			int x = GorpAltarUIFullScreen.this.x;
			int y = GorpAltarUIFullScreen.this.y;
			if (true) {
				GorpMod.PACKET_HANDLER.sendToServer(new GorpAltarUIFullButtonMessage(0, x, y, z));
				GorpAltarUIFullButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_gorp);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		GorpMessage.tick();
		GorpResponse.tick();
	}
}