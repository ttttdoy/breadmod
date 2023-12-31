package breadmod.util

import net.minecraft.world.item.DyeableArmorItem
import net.minecraft.world.item.ItemStack
import java.awt.Color

@Suppress("MemberVisibilityCanBePrivate")
/**
 * Adapted from [DyeableArmorItem]
 */
object StackColor {
    private const val TAG_COLOR = "color"
    private const val TAG_DISPLAY = "display"
    val defaultColor = Color(255,255,255)

    /* https://stackoverflow.com/a/54913292/7693129 */
    fun mixColors(vararg colors: Color): Color {
        val ratio = 1f / colors.size.toFloat()
        var r = 0
        var g = 0
        var b = 0
        var a = 0
        for (color in colors) {
            r = (r + color.red * ratio).toInt()
            g = (g + color.green * ratio).toInt()
            b = (b + color.blue * ratio).toInt()
            a = (a + color.alpha * ratio).toInt()
        }
        return Color(r, g, b, a)
    }

    fun ItemStack.getColor(): Color = getTagElement(TAG_DISPLAY).let {
        if (it != null && it.contains(TAG_COLOR, 99)) Color(it.getInt(TAG_COLOR)) else defaultColor
    }

    fun ItemStack.hasColor(): Boolean = getTagElement(TAG_DISPLAY)?.contains(TAG_COLOR, 99) ?: false
    fun ItemStack.clearColor() { getTagElement(TAG_DISPLAY)?.remove(TAG_COLOR) }
    fun ItemStack.setColor(color: Color) = getOrCreateTagElement(TAG_DISPLAY).putInt(TAG_COLOR, color.rgb)
    fun ItemStack.applyColor(color: Color) = setColor(mixColors(color, getColor()))
}