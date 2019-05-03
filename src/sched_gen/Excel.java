package sched_gen;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;



public class Excel {

	private static String[] columns = {"Week", "Team 1", "Team 2", "Location"};
	
	public static boolean write(ArrayList<ArrayList<Game>> games, String file) throws IOException, InvalidFormatException {
		boolean resol = true;
		
		try {
			File removeFile = new File(file + "\\\\teamSchedule.xlsx");
			if(removeFile.delete()) 
	        { 
	            System.out.println("File deleted successfully"); 
	        } 
	        else
	        { 
	            System.out.println("Failed to delete the file"); 
	        } 
		}
		catch(Exception e) {
			resol = false;
		}
		
		Workbook wb = new XSSFWorkbook();

		Sheet sheet = wb.createSheet("Schedule");
		
		 Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
		
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        Row headerRow = sheet.createRow(0);
        
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        int rowNum = 1;
        for (int i = 0; i < games.size(); i++){
        	for (int j = 0; j < games.get(i).size(); j++){
        		Row row = sheet.createRow(rowNum++);
        		
        		row.createCell(0).setCellValue(games.get(i).get(j).getWeek());
        		row.createCell(1).setCellValue(games.get(i).get(j).getFirstTeam());
        		row.createCell(2).setCellValue(games.get(i).get(j).getSecondTeam());
        		row.createCell(3).setCellValue(games.get(i).get(j).getLocation());
        	}
        }
        
        for(int i=1; i < columns.length; i++) {
        	sheet.autoSizeColumn(i);
        }
        
        FileOutputStream export = new FileOutputStream(file + "\\teamSchedule.xlsx");
        wb.write(export);
        export.close();
        wb.close();
		return resol;
	}
}
