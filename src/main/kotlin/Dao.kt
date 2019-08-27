import org.apache.poi.ss.usermodel.Cell
import java.io.File


enum class Language {
    english,
    portugies,
    spanish,
    german,
    polish,
    russian,
    french,
    italian,
    greek,
    arabic,
    hebrew,
    japanese,
    chinese
}

data class RowData(
    val iosKey: Cell,
    val androidKey: Cell,
    val english: Cell,
    val portugies: Cell,
    val spanish: Cell,
    val german: Cell,
    val polish: Cell,
    val russian: Cell,
    val french: Cell,
    val italian: Cell,
    val greek: Cell,
    val arabic: Cell,
    val hebrew: Cell,
    val japanese: Cell,
    val chinese: Cell
) {

    fun getIosKey(): String {
        return iosKey.stringCellValue
    }

    fun getAndroidKey(): String {
        return androidKey.stringCellValue
    }

    fun getEnglish(): String {
        return english.stringCellValue
    }

    fun getPortugies(): String {
        return portugies.stringCellValue
    }

    fun getSpanish(): String {
        return spanish.stringCellValue
    }

    fun getGerman(): String {
        return german.stringCellValue
    }

    fun getPolish(): String {
        return polish.stringCellValue
    }

    fun getRussian(): String {
        return russian.stringCellValue
    }

    fun getFrench(): String {
        return french.stringCellValue
    }

    fun getItalian(): String {
        return italian.stringCellValue
    }

    fun getGreek(): String {
        return greek.stringCellValue
    }

    fun getArablic(): String {
        return arabic.stringCellValue
    }

    fun getHebrew(): String {
        return hebrew.stringCellValue
    }

    fun getJapanese(): String {
        return japanese.stringCellValue
    }

    fun getChinese(): String {
        return chinese.stringCellValue
    }

    fun getCellData(cell: Cell): String {
        return cell.stringCellValue
    }

    override fun toString(): String {
        return "${getIosKey()} | " +
                "${getAndroidKey()} | " +
                "${getEnglish()} | " +
                "${getPortugies()} | " +
                "${getSpanish()} | " +
                "${getGerman()} | " +
                "${getPolish()} | " +
                "${getRussian()} | " +
                "${getFrench()} | " +
                "${getItalian()} | " +
                "${getGreek()} | " +
                "${getArablic()} | " +
                "${getHebrew()} | " +
                "${getJapanese()} | " +
                "${getChinese()} | "
    }
}

data class SheetModel(
    var name: String = "",
    var rowDataArray: ArrayList<RowData> = ArrayList()
) {
    private fun stringMakerAndorid(key: String, value: String): String {
        return "<string name=\"$key\">$value</string>\n"
    }

    private fun stringMakerAndoridIOS(key: String, value: String): String {
        return "\"$key\" = \"$value\";\n"
    }

    fun generateStings(inputName: Language) {

        val result = Result()

        val stringAndroid = File("src/main/resources/android_${inputName.toString().toLowerCase()}_string.xml")
        val stringIos = File("src/main/resources/ios_${inputName.toString().toLowerCase()}.strings")

        log("Adding --> $inputName ")

        var resultAndroid = if (stringAndroid.exists()) stringAndroid.readText() else ""
        var resultIos = if (stringIos.exists()) stringIos.readText() else ""

        rowDataArray.forEach { data ->

            if (inputName == Language.english) {
                resultAndroid += stringMakerAndorid(data.getAndroidKey(), data.getCellData(data.english))
                resultIos += stringMakerAndoridIOS(data.getIosKey(), data.getCellData(data.english))
            }

            if (inputName == Language.portugies) {
                resultAndroid += stringMakerAndorid(data.getAndroidKey(), data.getCellData(data.portugies))
                resultIos += stringMakerAndoridIOS(data.getIosKey(), data.getCellData(data.portugies))
            }

            if (inputName == Language.spanish) {
                resultAndroid += stringMakerAndorid(data.getAndroidKey(), data.getCellData(data.spanish))
                resultIos += stringMakerAndoridIOS(data.getIosKey(), data.getCellData(data.spanish))
            }

            if (inputName == Language.german) {
                resultAndroid += stringMakerAndorid(data.getAndroidKey(), data.getCellData(data.german))
                resultIos += stringMakerAndoridIOS(data.getIosKey(), data.getCellData(data.german))
            }

            if (inputName == Language.russian) {
                resultAndroid += stringMakerAndorid(data.getAndroidKey(), data.getCellData(data.russian))
                resultIos += stringMakerAndoridIOS(data.getIosKey(), data.getCellData(data.russian))
            }

            if (inputName == Language.french) {
                resultAndroid += stringMakerAndorid(data.getAndroidKey(), data.getCellData(data.french))
                resultIos += stringMakerAndoridIOS(data.getIosKey(), data.getCellData(data.french))
            }

            if (inputName == Language.italian) {
                resultAndroid += stringMakerAndorid(data.getAndroidKey(), data.getCellData(data.italian))
                resultIos += stringMakerAndoridIOS(data.getIosKey(), data.getCellData(data.italian))
            }

            if (inputName == Language.greek) {
                resultAndroid += stringMakerAndorid(data.getAndroidKey(), data.getCellData(data.greek))
                resultIos += stringMakerAndoridIOS(data.getIosKey(), data.getCellData(data.greek))
            }

            if (inputName == Language.hebrew) {
                resultAndroid += stringMakerAndorid(data.getAndroidKey(), data.getCellData(data.hebrew))
                resultIos += stringMakerAndoridIOS(data.getIosKey(), data.getCellData(data.hebrew))
            }

        }

        result.android = resultAndroid
        result.ios = resultIos

        stringAndroid.writeText(resultAndroid)
        stringIos.writeText(resultIos)

    }

}

data class Result(
    var android: String = "",
    var ios: String = ""
)