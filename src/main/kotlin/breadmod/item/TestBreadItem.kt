package breadmod.item

import breadmod.BreadMod
import breadmod.gui.BreadModCreativeTab
import breadmod.util.raycast
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult

class TestBreadItem : Item(Properties().food(FoodProperties.Builder().nutrition(6).build()).tab(BreadModCreativeTab)) {
    override fun use(pLevel: Level, pPlayer: Player, pHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (pHand == InteractionHand.MAIN_HAND) {
            when(val hit = pPlayer.raycast(50, false)) {
                is BlockHitResult -> { pLevel.setBlockAndUpdate(hit.blockPos, Blocks.AIR.defaultBlockState()) }
                is EntityHitResult -> { pLevel.explode(pPlayer, hit.location.x, hit.location.y, hit.location.z, 5f, Explosion.BlockInteraction.DESTROY) }
            }
        }
        return super.use(pLevel, pPlayer, pHand)
    }

    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: Level?,
        pTooltip: MutableList<Component>,
        pFlag: TooltipFlag
    ) {
        pTooltip.add(1,Component.translatable("item.${BreadMod.ID}.test_bread.desc").withStyle(ChatFormatting.GOLD))
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag)
    }
}