package ru.ruzsombi.hardsurvive.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RedSpider extends EntitySpider {
    @Override
    public CombatTracker func_110142_aN() {
        return super.func_110142_aN();
    }

    public RedSpider(World p_i1743_1_) {
        super(p_i1743_1_);
    }

    @Override
    protected Entity findPlayerToAttack() {
        double d0 = 16.0D;
        return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
    }

    @Override
    protected void attackEntity(Entity p_70785_1_, float p_70785_2_) {
        if (p_70785_2_ > 2.0F && p_70785_2_ < 6.0F && this.rand.nextInt(10) == 0) {
            if (this.onGround) {
                double d0 = p_70785_1_.posX - this.posX;
                double d1 = p_70785_1_.posZ - this.posZ;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                this.motionX = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                this.motionZ = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                this.motionY = 0.4000000059604645D;
            }
        } else {
            super.attackEntity(p_70785_1_, p_70785_2_);
        }
    }
}
