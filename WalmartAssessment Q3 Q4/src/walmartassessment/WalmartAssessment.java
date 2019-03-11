/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package walmartassessment;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * Walmart Assessment Test
 * @author ruthvickramdin
 */
public class WalmartAssessment {

    /**
     * This is the main method
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        /********  Question #3  ********/
        System.out.println("\nQuestion #3");
        //Input string
        String input = "Walmart Technology is reinventing the way the world shops and we’ve only just begun." 
                + "Our team includes @Walmart Labs in Silicon Valley and Bengaluru, which powers the eCommerce experience, "
                + "as well as technology teams across data and analytics, retail, back office, "
                + "and more who help power store and digital experiences. The best thing about Walmart is its rich history!";
 
        //wordCount() will take input string and generate the map with the word counts
        //printWordCount() will print the entries from the output HashMap returned from the wordCount() 
        printWordCount(wordCount(input)); // Test Case 1: Original input
        
        printWordCount(wordCount(""));  // Test Case 2: An empty string
        
        
        /*******  Question #4  ********/
        System.out.println("\nQuestion #4");
        // Active Array
        List<String> activeArray = new ArrayList<>();
        activeArray.add("Smith");
        activeArray.add("Brown");
        
        ArrayList<Person> personList = loadPersonData();
        setActiveStatus(personList, activeArray);   
        printPeopleData(personList);   
    }
    
    private static String readFileAsString(String fileName)throws Exception 
    { 
      String data = ""; 
      data = new String(Files.readAllBytes(Paths.get(fileName))); 
      return data; 
    } 
    
    /**
     * Walmart Assessment: Question #3
     * This method takes a string as input and returns a HashMap that contains every word and its count
     * @param input
     * @return outputMap
     */
    private static Map<String, Integer> wordCount(String input) {
        
        //Output map to store the words and their count
        Map<String, Integer> outputMap = new HashMap<> ();
        
        if(input != null && !input.isEmpty()) {
            
            //Split the input string based on whitespaces, ',', '!', '@', '.'
            //More symbols can be added in the array [] followed by \\
            String [] strArray = input.split("[,\\@\\.\\!\\ ]");

            //Loop through every string in the string array
            for (String s : strArray) {

              //check if the string is not empty
              //exlude if it is empty
              if(!s.isEmpty()) {
                
                //Option 1:
                //Get hashcode for the string and check, as HashMap check for entries based on hashCodes in the Map 
                //int hashCode = s.hashCode();
                //And check for !outputMap.containsKey(hashCode)
                
                //Check if the outputMap contains Key (hashCode)
                //If not present then add the word
                if (!outputMap.containsKey(s)) {  
                    outputMap.put(s, 1);
                }
                //else increment the count of the key
                else {
                    int count = outputMap.get(s);
                    outputMap.put(s, count + 1);
                }
              }
            }
        }
        return outputMap; 
    }
    
    /**
     * This method prints all the entries in the HashMap
     * @param ipMap input map
     */
    private static void printWordCount(Map<String, Integer> ipMap) {
        
        //Check if the HashMap is null and has entries in it
        if(ipMap != null && ipMap.size() > 0) {
            
            List<JSONObject> lst = new ArrayList<>();
            
            Iterator it = ipMap.entrySet().iterator();
            
            while(it.hasNext()) {
                
                Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
                JSONObject jObj = new JSONObject();
                jObj.put( entry.getKey(), entry.getValue());
                lst.add(jObj);
            }
            System.out.print(lst);
        }
        else {
            System.out.println("\nInput is null or empty");
        }
    }
    
    /**
     * This method loads the Person data into a List
     * This data can be read and loaded from an external file as well.
     * @return list of Person
     */
    private static ArrayList<Person> loadPersonData() {
        
        ArrayList<Person> list = new ArrayList<>();
        
        list.add(new Person("John", "100 N. Main", "+1-(123)-456-7890", "Smith"));
        list.add(new Person("John", "100 N. Main", "+1-(123)-456-7890", "Doe"));
        list.add(new Person("John", "100 N. Main", "+1-(123)-456-7890", "Brown"));
        list.add(new Person("John", "100 N. Main", "+1-(123)-456-7890", "Akins"));
        
        return list;   
    }
    
    /**
     * This method sets the active status for a person record
     * @param personList
     * @param activeArray 
     */
    private static void setActiveStatus(ArrayList<Person> personList, List<String> activeArray) {
        
        if(personList != null && personList.size() > 0) {
            
            for(Person p : personList) {
                p.active = activeArray.contains(p.lName);
            }
        }
    }
    
    /**
     * This method prints the people data
     * @param personObjList 
     */
    private static void printPeopleData(ArrayList<Person> personObjList) {
        
        if(personObjList != null && personObjList.size() > 0) {
            
            personObjList.sort((Person p1, Person p2)->p1.lName.compareTo(p2.lName));
            
            List<JSONObject> lst = new ArrayList<>();
            
            for(Person p : personObjList) {
                JSONObject obj = new JSONObject();
                obj.put("name", p.getFullName());
                obj.put("addess", p.address);
                obj.put("phone", p.phone);
                obj.put("active", p.active.toString());
                lst.add(obj);
            }  
            
            System.out.println(lst);
        }   
    }
    
    /**
     * Person class as per the Data Supplement
     */
    static class Person {
    
        String fName;
        String address;
        String lName;
        String phone;
        Boolean active;

        //Constructor
        Person(String firstName, String addr, String phNum, String lastName) {
            fName = firstName;
            address = addr;
            phone = phNum;
            lName = lastName;
            active = false;
        }

        /**
         * This methods returns a full name by concatenating first name and last name   
         * @return 
         */
        private String getFullName() {
            return (this.fName + " " + this.lName);
        }
    }
}


