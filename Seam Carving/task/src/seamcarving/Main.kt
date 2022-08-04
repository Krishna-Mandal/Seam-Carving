package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.sqrt

fun main(args:Array<String>) {
    val inFile = File(args[1])
    val outFile = File(args[3])
    val inImage = ImageIO.read(inFile)
    var maxEnergy: Double = Double.MIN_VALUE
    val energy = Array(inImage.width) {DoubleArray(inImage.height)}

    for (x in 0 until inImage.width) {
        for (y in 0 until inImage.height) {

            val currEnergy = energy(x, y, inImage)
            energy[x][y] = currEnergy
            if (currEnergy > maxEnergy) {
                maxEnergy = currEnergy
            }
        }
    }

    for (x in 0 until inImage.width) {
        for (y in 0 until inImage.height) {
            val currEnergy = energy[x][y]
            val intensity = intensity(currEnergy, maxEnergy)
            inImage.setRGB(x, y, Color(intensity, intensity, intensity).rgb)

        }
    }


    ImageIO.write(inImage, "png", outFile)
}

fun grad2(axis: String, x: Int, y: Int, image: BufferedImage): Double {
    val pixel1 = Color(image.getRGB(if (axis == "x") x - 1 else x, if (axis == "y") y - 1 else y))
    val pixel2 = Color(image.getRGB(if (axis == "x") x + 1 else x, if (axis == "y") y + 1 else y))
    val rDiff = pixel2.red - pixel1.red
    val gDiff = pixel2.green - pixel1.green
    val bDiff = pixel2.blue - pixel1.blue
    return (rDiff * rDiff + gDiff * gDiff + bDiff * bDiff).toDouble()
}

fun energy(x: Int, y: Int, im: BufferedImage): Double {
    return sqrt(grad2("x", x.coerceIn(1, im.width - 2), y, im) + grad2("y", x, y.coerceIn(1, im.height - 2), im))
}

fun intensity(energy: Double, maxEnergyValue: Double): Int {
    val intensity = (255.0 * energy / maxEnergyValue).toInt()
    return intensity
}