package fail.toepic.lazylazy.sample.color.data

import android.content.Context
import fail.toepic.lazylazy.sample.R
import fail.toepic.lazylazy.sample.color.model.Color


val html5WebSafePair = "web safe" to  listOf(
    R.color.black,
    R.color.navy,
    R.color.darkBlue,
    R.color.mediumBlue,
    R.color.blue,
    R.color.darkGreen,
    R.color.green,
    R.color.teal,
    R.color.darkCyan,
    R.color.deepSkyBlue,
    R.color.darkTurquoise,
    R.color.mediumSpringGreen,
    R.color.lime,
    R.color.springGreen,
    R.color.aqua,
    R.color.cyan,
    R.color.midnightBlue,
    R.color.dodgerBlue,
    R.color.lightseaGreen,
    R.color.forestGreen,
    R.color.seaGreen,
    R.color.darkSlateGray,
    R.color.darkSlateGrey,
    R.color.limeGreen,
    R.color.mediumSeaGreen,
    R.color.turquoise,
    R.color.royalBlue,
    R.color.steelBlue,
    R.color.darkSlateBlue,
    R.color.mediumTurquoise,
    R.color.indigo,
    R.color.darkOliveGreen,
    R.color.cadetBlue,
    R.color.cornflowerBlue,
    R.color.rebeccaPurple,
    R.color.mediumAquamarine,
    R.color.dimGray,
    R.color.dimGrey,
    R.color.slateBlue,
    R.color.oliveDrab,
    R.color.slateGray,
    R.color.slateGrey,
    R.color.lightSlateGray,
    R.color.lightSlateGrey,
    R.color.mediumSlateBlue,
    R.color.lawnGreen,
    R.color.chartreuse,
    R.color.aquamarine,
    R.color.maroon,
    R.color.purple,
    R.color.olive,
    R.color.gray,
    R.color.grey,
    R.color.skyBlue,
    R.color.lightSkyBlue,
    R.color.blueViolet,
    R.color.darkRed,
    R.color.darkMagenta,
    R.color.saddleBrown,
    R.color.darkSeaGreen,
    R.color.lightGreen,
    R.color.mediumPurple,
    R.color.darkViolet,
    R.color.paleGreen,
    R.color.darkOrchid,
    R.color.yellowGreen,
    R.color.sienna,
    R.color.brown,
    R.color.darkGray,
    R.color.darkGrey,
    R.color.lightBlue,
    R.color.greenYellow,
    R.color.paleTurquoise,
    R.color.lightSteelBlue,
    R.color.powderBlue,
    R.color.firebrick,
    R.color.darkGoldenrod,
    R.color.mediumOrchid,
    R.color.rosyBrown,
    R.color.darkKhaki,
    R.color.silver,
    R.color.mediumVioletRed,
    R.color.indianRed,
    R.color.peru,
    R.color.chocolate,
    R.color.tan,
    R.color.lightGray,
    R.color.lightGrey,
    R.color.thistle,
    R.color.orchid,
    R.color.goldenrod,
    R.color.paleVioletRed,
    R.color.crimson,
    R.color.gainsboro,
    R.color.plum,
    R.color.burlyWood,
    R.color.lightCyan,
    R.color.lavender,
    R.color.darkSalmon,
    R.color.violet,
    R.color.paleGoldenrod,
    R.color.lightCoral,
    R.color.khaki,
    R.color.aliceBlue,
    R.color.honeydew,
    R.color.azure,
    R.color.sandyBrown,
    R.color.wheat,
    R.color.beige,
    R.color.whiteSmoke,
    R.color.mintCream,
    R.color.ghostWhite,
    R.color.salmon,
    R.color.antiqueWhite,
    R.color.linen,
    R.color.lightGoldenRodYellow,
    R.color.oldLace,
    R.color.red,
    R.color.fuchsia,
    R.color.magenta,
    R.color.deepPink,
    R.color.orangeRed,
    R.color.tomato,
    R.color.hotPink,
    R.color.coral,
    R.color.darkOrange,
    R.color.lightSalmon,
    R.color.orange,
    R.color.lightPink,
    R.color.pink,
    R.color.gold,
    R.color.peachPuff,
    R.color.navajoWhite,
    R.color.moccasin,
    R.color.bisque,
    R.color.mistyRose,
    R.color.blanchedAlmond,
    R.color.papayaWhip,
    R.color.lavenderBlush,
    R.color.seashell,
    R.color.cornSilk,
    R.color.lemonChiffon,
    R.color.floralWhite,
    R.color.snow,
    R.color.yellow,
    R.color.lightYellow,
    R.color.ivory,
    R.color.white
)

val colorSpacePrimary = "rgb - color space - primary" to listOf(R.color.red,R.color.green,R.color.blue)
val colorSpaceSecondary = "rgb - color space - secondary" to listOf(R.color.yellow,R.color.magenta,R.color.cyan)
val colorSpaceTertiary = "rgb - color space - tertiary" to listOf(R.color.azure,R.color.violet,R.color.rose,R.color.orange,
    R.color.chartreuse,R.color.springGreen)



val colorMap = mutableMapOf<Int,String>()

object ColorData{
    fun loadData(context: Context): List<Color> {
        var list = mutableListOf<ColorInternal>()
        try {
            reloadColorMap()

            list = loadList(list,html5WebSafePair, context)
            list =loadList(list,colorSpacePrimary, context)
            list =loadList(list,colorSpaceSecondary, context)
            list =loadList(list,colorSpaceTertiary, context)
            return list.asSequence().groupBy {
                it.hex
            }.map {(hex, li) ->
                val names = li.map { it.name }.distinct()
                val categories = li.map { it.category }.distinct()
                val resid = li[0].colorResInt
                Color(hex = hex,colorResId = resid, names = names,categories = categories)
            }
        } finally {
            clearColorMap()
        }

    }

    private fun loadList(
        target: MutableList<ColorInternal>,
        source: Pair<String, List<Int>>,
        context: Context
    ): MutableList<ColorInternal> {
        val items = source.second.map {
            Color.fromResId(context, it,source.first )
        }

        target.addAll(items)
        return target
    }

    /** R.color.XXX 를 불러와서 맵에 밀어 넣어둔다.  사용 후 clearColorMap 을 사용하여 맵을 해제 하는 편이 좋습니다.    */
    private fun reloadColorMap() {
        colorMap.clear()
        for (field in R.color::class.java.fields) {
            colorMap[field.getInt(R.color::javaClass)] = field.name
        }
    }

    /** 사용된 컬러맵을 클리어 합니다. */
    private fun clearColorMap(){
        colorMap.clear()
    }
}

private fun Color.Companion.fromResId(context: Context, resId: Int,category : String): ColorInternal {
    @Suppress("DEPRECATION") val intColor = context.resources.getColor(resId)
    val hexColor = String.format("#%06X", 0xFFFFFF and intColor)
    return ColorInternal(hexColor,resId, colorMap[resId]?: "",category)
}


private class ColorInternal(
    val hex: String = "",
    val colorResInt: Int,
    val name : String,
    val category: String
)