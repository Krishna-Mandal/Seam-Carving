package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB
import java.io.File
import javax.imageio.ImageIO

fun main() {
    val width = readln().toInt()
    val height = readln().toInt()
    val outFile = File(readln())

    val bufferedImage = BufferedImage(width, height, TYPE_INT_RGB)
    val graphics = bufferedImage.createGraphics()
    graphics.color = Color.BLACK
    graphics.drawRect(0, 0, width - 1 , height - 1)
    graphics.color = Color.RED
    graphics.drawLine(0, 0, width - 1, height - 1)
    graphics.drawLine(width - 1, 0, 0, height - 1)

    ImageIO.write(bufferedImage, "png", outFile)
}
