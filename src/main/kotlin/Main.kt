import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream

fun main() {
    val start = System.currentTimeMillis()
    log("--Start--")
    openFile()
    log("--End--")
    val end = System.currentTimeMillis()
    log("Process Completed in ${(end - start).toFloat() / 1000} seconds")
}

fun openFile() {
    val resPath = "src/main/resources"
    val resourceFolder = File(resPath)
    val fileName = "input.xlsx"
    val inputFile = File("$resPath/$fileName")
    val excelFile = FileInputStream(inputFile)

    val workbook = XSSFWorkbook(excelFile)
    val count = workbook.numberOfSheets

    val sheetModelArray = ArrayList<SheetModel>()

    log("There are $count Sheets")

    workbook.sheetIterator().forEach { sheet ->
        log("Parsing Sheet == ${sheet.sheetName}")
        parseSheet(sheet) { sheetModel ->
            sheetModelArray.add(sheetModel)
        }
    }

    log("Parsing Done")

    log("Deleting Old Files")

    resourceFolder.listFiles().forEach { file ->
        if (file != inputFile) {
            log("Deleting == ${file.name}")
            file.delete()
        }
    }

    log("Generating Files")

    sheetModelArray.forEach {
        log("Generating --> ${it.name} String File")
        it.generateStings(Language.english)
        it.generateStings(Language.portugies)
        it.generateStings(Language.spanish)
        it.generateStings(Language.german)
        it.generateStings(Language.russian)
        it.generateStings(Language.french)
        it.generateStings(Language.italian)
        it.generateStings(Language.greek)
        it.generateStings(Language.hebrew)
    }

    workbook.close()
    excelFile.close()
}

fun parseSheet(sheet: Sheet, parsedData: (SheetModel) -> Unit) {

    val rows = sheet.iterator()
    val checkRow = sheet.iterator().iterator().next()

    if (checkRow.getCell(0).stringCellValue == "ios_key") {

        val sheetModel = SheetModel()
        sheetModel.name = sheet.sheetName

        val dataArray = ArrayList<RowData>()

        rows.forEach { currentRow ->

            /***
            val iosKey = currentRow.getCell(0)
            val androidKey = currentRow.getCell(1)
            val english = currentRow.getCell(2)
            val portugies = currentRow.getCell(3)
            val spanish = currentRow.getCell(4)
            val german = currentRow.getCell(5)
            val polish = currentRow.getCell(6)
            val russian = currentRow.getCell(7)
            val french = currentRow.getCell(8)
            val italian = currentRow.getCell(9)
            val greek = currentRow.getCell(10)
            val arabic = currentRow.getCell(11)
            val hebrew = currentRow.getCell(12)
            val japanese = currentRow.getCell(13)
            val chinese = currentRow.getCell(14)
             */

            if (currentRow.getCell(0).stringCellValue != "") {

                val data = RowData(
                    iosKey = currentRow.getCell(0),
                    androidKey = currentRow.getCell(1),
                    english = currentRow.getCell(2),
                    portugies = currentRow.getCell(3),
                    spanish = currentRow.getCell(4),
                    german = currentRow.getCell(5),
                    polish = currentRow.getCell(6),
                    russian = currentRow.getCell(7),
                    french = currentRow.getCell(8),
                    italian = currentRow.getCell(9),
                    greek = currentRow.getCell(10),
                    arabic = currentRow.getCell(11),
                    hebrew = currentRow.getCell(12),
                    japanese = currentRow.getCell(13),
                    chinese = currentRow.getCell(14)
                )

                dataArray.add(data)

            }
        }

        sheetModel.rowDataArray = dataArray
        parsedData(sheetModel)
        log("----------")
    }
}