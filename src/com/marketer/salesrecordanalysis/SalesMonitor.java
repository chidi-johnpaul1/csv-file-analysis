
package com.marketer.salesrecordanalysis;

import com.marketer.model.Marketer;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *Monitors the Marketer sales
 * 
 * @author CHIDI
 */
public class SalesMonitor {
  

   List<Marketer> marketers;

   public SalesMonitor( )
   {
      
     this.marketers = new  ArrayList<>();
   }

   public int computeBonus(Marketer aMarketer)
   {
      int bonus = (aMarketer.getSales() * aMarketer.getYears())/10;
      return bonus;
   }
   
   /**
    * read marketer data
    */
public void readMarketerData()
   {
      String fileName = "marketerData.txt";
      File aFile = new File(fileName);
      Scanner bufferedScanner = null;
      
      String firstname;
      String lastname;
      String gender;
      int years;
      String productCode;
      int sales;
            
      Scanner lineScanner;
      String currentLine;
      
      try
         {
            bufferedScanner = new Scanner(new BufferedReader(new FileReader(aFile)));
            while (bufferedScanner.hasNextLine())
               {
                  currentLine = bufferedScanner.nextLine();
                  lineScanner = new Scanner(currentLine);
                  lineScanner.useDelimiter(",");
                  
                  firstname = lineScanner.next();
                  lastname = lineScanner.next();
                  gender = lineScanner.next();
                  years = lineScanner.nextInt();
                  productCode = lineScanner.next();
                  sales = lineScanner.nextInt();
                  
                  Marketer marketer = new Marketer(firstname, lastname);
                  marketer.setGender(gender);
                  marketer.setYears(years);
                  marketer.setProductCode(productCode);
                  marketer.setSales(sales);
                  marketer.setBonus(computeBonus(marketer));
                  
                  marketers.add(marketer);
               }
            }
            catch (FileNotFoundException anException)
            {
               System.out.println("Error: " + anException);
            }
            finally
            {
            try
            {
            if(bufferedScanner != null){
            bufferedScanner.close();
            }
            }
            catch (Exception anException)
            {
            System.out.println("Error: " + anException);
            }
            }
   }
      
      /**
       * show marketer data in ascending order of their bonus written to marketersResults.txt file
       */
      public void  showBonusData()
      {
         List<Marketer> marketerList = new ArrayList<>();
         marketers.forEach((marketer) -> {
             marketerList.add(marketer);
         });
         Collections.sort(marketerList);
         
         String fileName = "marketersResults.txt";
         File marketerFile = new File(fileName);
         BufferedWriter bufferedFileWriter = null;
         
         try
            {
               bufferedFileWriter = new BufferedWriter(new FileWriter(marketerFile));
               bufferedFileWriter.write("====Marketer's sorted list===");
               bufferedFileWriter.newLine();
               
            for (Marketer marketer : marketerList)
               {
                  bufferedFileWriter.write(marketer.getFirstName() +" "+ marketer.getLastName() +",Gender:"
                  + marketer.getGender()  +"(Years:"
                  + marketer.getYears() +",ID:"
                  + marketer.getId() +")ProductCode:" 
                  + marketer.getProductCode() + " Sales:" 
                  + marketer.getSales()+ " Bonus:" 
                  + marketer.getBonus());
                  bufferedFileWriter.newLine();
               }
            }
            catch (IOException anException)
            {
               System.out.println("Error: " + anException);
            }
            finally
            {
            try
            {
            if(bufferedFileWriter != null){
            bufferedFileWriter.close();
            }
            }
            catch (IOException anException)
            {
            System.out.println("Error: " + anException);
            }
            }
      }    
   }

 
