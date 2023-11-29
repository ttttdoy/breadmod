package breadmod.datagen

import breadmod.BreadMod
import net.minecraft.data.DataGenerator
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper

class ItemModels (
    generator: DataGenerator,
    fileHelper: ExistingFileHelper,
) : ItemModelProvider(generator, BreadMod.ID, fileHelper) {
    override fun registerModels() {
//        singleTexture(Battery.id.path, mcLoc("item/generated"), "layer0", modLoc("item/battery"));


        withExistingParent("breadmod:bread_block", modLoc("block/bread_block"))
        singleTexture("breadmod:test_bread", mcLoc("item/generated"), "layer0", modLoc("items/test_bread"))
    }
}