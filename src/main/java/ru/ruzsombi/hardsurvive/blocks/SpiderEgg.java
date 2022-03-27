package ru.ruzsombi.hardsurvive.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import ru.ruzsombi.hardsurvive.HardSurvive;

public class SpiderEgg extends Block {
    public SpiderEgg() {
        super(Material.rock);
        this.setBlockName("spiderEgg");
        this.setBlockUnbreakable();
        this.setResistance(6000000.0F);
        this.setStepSound(soundTypePiston);
        this.setLightLevel(0.25F);
        this.setBlockTextureName(HardSurvive.MODID + ":spiderEgg");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
