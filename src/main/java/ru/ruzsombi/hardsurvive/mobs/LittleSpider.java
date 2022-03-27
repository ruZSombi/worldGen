package ru.ruzsombi.hardsurvive.mobs;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class LittleSpider extends EntityMob {
    public LittleSpider(World p_i1738_1_) {
        super(p_i1738_1_);
        this.setSize(0.6F, 0.6F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.65D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.25D);
    }

    protected String getLivingSound()
    {
        return "mob.spider.say";
    }

    protected String getHurtSound()
    {
        return "mob.spider.say";
    }

    protected String getDeathSound()
    {
        return "mob.spider.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.spider.step", 0.15F, 1.0F);
    }
}
