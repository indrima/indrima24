package random;

import java.util.concurrent.ThreadLocalRandom;

import game.Game;
import main.collections.FastArrayList;
import util.AI;
import util.Context;
import util.Move;

import utils.AIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Qlearning;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class snapShot
{
    
       /**
	 * Constructor
	 */
	public snapshot()
	{
		this.friendlyName = "Snapshot";
	}
	
	//-------------------------------------------------------------------------
     
    public void snapshot(String[] args) 
    {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Q-Matrix");
          
        //Reading in the data that needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        for ( int i = 0; i < game.states; i++ ){
		data.put(i, new Object[] {game.states, Qlearning.qvalues});
	}
                  
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("snapshot.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("snapshot.xlsx written successfully on disk.");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
