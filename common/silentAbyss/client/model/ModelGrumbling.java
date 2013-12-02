package silentAbyss.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelGrumbling extends ModelBase {

    // fields
    ModelRenderer body;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer rightear;
    ModelRenderer leftear;
    ModelRenderer nose;

    public ModelGrumbling() {

        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 0, 0);
        body.addBox(-5F, 0F, -4F, 10, 8, 8);
        body.setRotationPoint(0F, 11F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 13, 17);
        rightleg.addBox(-1F, 0F, -1F, 2, 5, 2);
        rightleg.setRotationPoint(-3F, 19F, 0F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 13, 17);
        leftleg.addBox(-1F, 0F, -1F, 2, 5, 2);
        leftleg.setRotationPoint(3F, 19F, 0F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        rightear = new ModelRenderer(this, 0, 23);
        rightear.addBox(-1F, -3F, 0F, 2, 3, 1);
        rightear.setRotationPoint(-3F, 11F, -1F);
        rightear.setTextureSize(64, 32);
        rightear.mirror = true;
        setRotation(rightear, 0F, 0F, 0F);
        leftear = new ModelRenderer(this, 0, 23);
        leftear.addBox(-1F, -3F, 0F, 2, 3, 1);
        leftear.setRotationPoint(3F, 11F, -1F);
        leftear.setTextureSize(64, 32);
        leftear.mirror = true;
        setRotation(leftear, 0F, 0F, 0F);
        nose = new ModelRenderer(this, 0, 17);
        nose.addBox(-2F, -1F, -1F, 4, 3, 2);
        nose.setRotationPoint(0F, 15F, -5F);
        nose.setTextureSize(64, 32);
        nose.mirror = true;
        setRotation(nose, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
        rightear.render(f5);
        leftear.render(f5);
        nose.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {

        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {

        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
