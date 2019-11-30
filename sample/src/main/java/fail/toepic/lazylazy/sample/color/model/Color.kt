package fail.toepic.lazylazy.sample.color.model

data class Color(
    val hex: String = "",
    val colorResId: Int,
    val names : List<String> = listOf(),
    val categories : List<String> = listOf()
) {
    companion object
}