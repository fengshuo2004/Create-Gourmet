package net.silvergold.gourmet;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.silvergold.gourmet.item.specialitem.DreamyFoodItems;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(modid = "gourmet")
public class EventHandler {
    static float alpha = 0.0f;
    static int alphaTimer = 0;
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if (event.player.isUsingItem()) {
                ItemStack itemStack = event.player.getUseItem();
                if (itemStack.getItem() instanceof DreamyFoodItems) {
                    alphaTimer += 1;
                    if (alphaTimer > 45) {
                        alpha = alpha + (0.07f - alpha) * 0.008f;
                    }
                    //System.out.println("Eating Dreamy Food");
                }
            } else {
                alpha = alpha + (0.0f - alpha) * 0.2f;
                alphaTimer = 0;
            }
        }
    }

    @SubscribeEvent
    public static void renderOverlay(RenderGuiOverlayEvent.Post event) {
        //float alpha = 0.1f;
        var gui = Minecraft.getInstance().gui;

        float screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        float screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        //
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        TextureAtlasSprite textureatlassprite = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getParticleIcon(Blocks.NETHER_PORTAL.defaultBlockState());
        float u0 = textureatlassprite.getU0();
        float v0 = textureatlassprite.getV0();
        float u1 = textureatlassprite.getU1();
        float v1 = textureatlassprite.getV1();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(0.0D, (double)screenHeight, -90.0D).uv(u0, v1).endVertex();
        bufferbuilder.vertex((double)screenWidth, (double)screenHeight, -90.0D).uv(u1, v1).endVertex();
        bufferbuilder.vertex((double)screenWidth, 0.0D, -90.0D).uv(u1, v0).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(u0, v0).endVertex();
        tesselator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
