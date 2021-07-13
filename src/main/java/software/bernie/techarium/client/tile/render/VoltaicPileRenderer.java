package software.bernie.techarium.client.tile.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import software.bernie.techarium.client.tile.model.ExchangeStationModel;
import software.bernie.techarium.client.tile.model.VoltaicPileModel;
import software.bernie.techarium.tile.exchangestation.ExchangeStationTile;
import software.bernie.techarium.tile.voltaicpile.VoltaicPileTile;

import javax.annotation.Nullable;

public class VoltaicPileRenderer extends GeoBlockRenderer<VoltaicPileTile>
{
	public VoltaicPileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn, new VoltaicPileModel());
	}


	@Override
	public RenderType getRenderType(VoltaicPileTile animatable, float partialTicks, MatrixStack stack,
									@Nullable IRenderTypeBuffer renderTypeBuffer,
									@Nullable IVertexBuilder vertexBuilder, int packedLightIn,
									ResourceLocation textureLocation) {
		return RenderType.entityCutoutNoCull(this.getTextureLocation(animatable));
	}

}
