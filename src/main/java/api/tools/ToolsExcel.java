package api.tools;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static api.utils.Constants.*;

public class ToolsExcel {

    private ToolsExcel() {}

    public static List<List<String>> getDataFromExcelSheet(String filePath) {
        List<List<String>> sheetData = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            if (rows.hasNext()) {
                rows.next();
            }
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                List<String> data = new ArrayList<>();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    if (cell.toString().equals(NULL)) {
                        data.add(null);
                    } else if (cell.toString().equals(NONE)) {
                        data.add("");
                    } else {
                        data.add(cell.toString());
                    }
                }
                sheetData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetData;
    }

}