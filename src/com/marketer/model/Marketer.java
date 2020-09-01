
package com.marketer.model;

/**
 *Class Marketer - Models a Marketer working at ABC Enterprise
 * 
 * @author CHIDI
 */
public class Marketer implements Comparable<Marketer>{
   
    private int id;      
    private String firstname;
    private String lastname;
    private String gender;
    private int years;   
    private String productCode;  
    private int sales;   
    private int bonus;        
    
    private static int nextId = 0;
   

    /**
     * @param fName
     * @param lName
     */
       
    public Marketer(String fName,String lName)
    {
        super();
        this.firstname = fName;
        this.firstname = lName;
        this.gender = "xxxx";
        this. years = 0;
        this.productCode = "xxxx";
        this.sales = 0;
        this.bonus = 0;
        
        Marketer.nextId = Marketer.nextId + 1;
        this.id = nextId;
       
    }

    /**
     * @return marketer id
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * @param id
     */
    public void setId(int id)
    {
      
       this.id = nextId;
      
    }
    
    /**
     * @return marketer  first name
     */
    public String getFirstName()
    {
        return this.firstname;
    }
    /**
     * @return marketer  last name
     */
    public String getLastName()
    {
        return this.lastname;
    }
    /**
     * @return marketer gender
     */
    public String getGender()
    {
        return this.gender;
    }

    /**
     * @param aGender
     */
    public void setGender(String aGender)
    {
        this.gender = aGender;
    }
   
    /**
     * @return marketer years of work
     */
    public int getYears()
    {
        return this.years;
    }

    /**
     * @param aYears
     */
    public void setYears(int aYears)
    {
      
       this.years = aYears;
      
    }

    /**
     * @return marketer's productCode
     */
    public String getProductCode()
    {
        return this.productCode;
    }

    
    /**
     * @return marketer's product sales 
     */
    public int getSales()
    {
        return this.sales;
    }

    /**
     * @param aSales
     */
    public void setSales(int aSales)
    {
        this.sales = aSales;
    }

    /**
     * @param aProductName
     */
    public void setProductCode(String aProductName)
    {
        this.productCode = aProductName;
    }

    /**
     * @return marketer bonus
     */
    public int getBonus()
    {
        return this.bonus;
    }

    /**
     * @param aBonus
     */
    public void setBonus(int aBonus)
    {
        this.bonus = aBonus;
    }
    
     @Override
      public String toString() 
      {
         return firstname + " "+  lastname + " " + gender+ " " +"(Years:"+ years +",ID:"+ id +")ProductCode:" + productCode + " Sales:" + sales+ " Bonus:" + bonus; 
      } 
   
 /**
   * @param marketer
   * @reture bonus
   */
    @Override
 public int compareTo(Marketer marketer)
   {
       return  Integer.compare(this.getBonus(), marketer.getBonus());
   }
   
}
    
    
