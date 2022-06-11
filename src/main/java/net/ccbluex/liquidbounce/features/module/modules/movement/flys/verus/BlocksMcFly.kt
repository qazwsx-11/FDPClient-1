package net.ccbluex.liquidbounce.features.module.modules.movement.flys.ncp

import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.event.StepEvent
import net.ccbluex.liquidbounce.features.module.modules.movement.flys.FlyMode
import net.ccbluex.liquidbounce.utils.MovementUtils.strafe
import net.ccbluex.liquidbounce.value.FloatValue
import net.minecraft.network.Packet
import net.minecraft.network.play.client.*
import net.minecraft.network.play.INetHandlerPlayServer
import net.minecraft.network.play.server.S08PacketPlayerPosLook
import net.minecraft.network.play.client.C03PacketPlayer
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition

class BlocksMcFly : FlyMode("BlocksMcNew") {

    override fun onEnable() {
        if (!mc.thePlayer.onGround) {
            return
        }

        repeat(65) {
            mc.netHandler.addToSendQueue(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.00042, mc.thePlayer.posZ, false))
            mc.netHandler.addToSendQueue(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false))
        }
        mc.netHandler.addToSendQueue(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.1, mc.thePlayer.posZ, true))

        mc.thePlayer.motionX *= 0.08
        mc.thePlayer.motionZ *= 0.08
        mc.netHandler.addToSendQueue(c08)
        packet.onGround = false
        mc.thePlayer.swingItem()
    }

    override fun onUpdate(event: UpdateEvent) {
        mc.thePlayer.motionY = (-motionValue.get()).toDouble()

        if (mc.gameSettings.keyBindSneak.isKeyDown) mc.thePlayer.motionY = -0.498
        strafe()
    }

    override fun onPacket(event: PacketEvent) {
        val packet = event.packet

        if (packet is C03PacketPlayer) {
            packet.onGround = true
          
        if (packet is C00PacketKeepAlive) {
                    event.cancelEvent()
        }
    }
    
    if (event.packet is S08PacketPlayerPosLook) {
        val s08 = event.packet
        mc.thePlayer.posY *= 0.002
        mc.timer.timerSpeed = 0.32F
      
        override fun onStep(event: StepEvent) {
        event.stepHeight = 0f
    }
}
