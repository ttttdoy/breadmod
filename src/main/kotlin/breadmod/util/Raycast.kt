package breadmod.util

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraftforge.fluids.IFluidBlock

fun Entity.raycast(maxDistance: Int, includeFluids: Boolean): HitResult {
    val directionVector = Vec3.directionFromRotation(this.rotationVector)

    var lastVec3 = Vec3.ZERO
    var lastBlockPos = BlockPos.ZERO

    repeat(maxDistance) {
        lastVec3 = this.eyePosition.add(directionVector.scale(it.toDouble()))
        lastBlockPos = BlockPos(lastVec3)

        val block = this.level.getBlockState(lastBlockPos)
        if((block is IFluidBlock && includeFluids) || !block.isAir)
            return BlockHitResult(lastVec3, direction, lastBlockPos, true)

        val foundEntity = this.level.getEntities(this, AABB.ofSize(lastVec3, 1.0, 1.0, 1.0)).firstOrNull()
        if(foundEntity != null) return EntityHitResult(foundEntity, lastVec3)
    }

    return BlockHitResult.miss(lastVec3, direction, lastBlockPos)
}