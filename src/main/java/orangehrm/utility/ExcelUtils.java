package orangehrm.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    // Load Excel file and sheet
    public static void setExcelFile(String excelPath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(excelPath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);   // ✅ no casting required
    }

    // Get data from a specific cell
    public static String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return "";
        }
        return cell.toString();
    }

    // Get total number of rows
    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }
    
    // ✅ NEW: Get total number of columns
    public static int getColCount() {
        Row row = sheet.getRow(0); // take header row
        if (row == null) return 0;
        return row.getPhysicalNumberOfCells();
    }
}
