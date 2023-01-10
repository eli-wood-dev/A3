import java.util.Scanner;
import java.io.*;
import Tools.*;

/**
 * The Lunar Ark Program
 *
 * @author Eli Wood
 * @version v100
 */
class Main {
    public static void main() {
        int maxAnimals = 1200;
        
        Scanner keybd = new Scanner(System.in);
        Tools t = new Tools();
        
        String animal = "";
        String option = "";
        String filename = "";
        
        String[] ark = new String[maxAnimals];
        
        int animalCount = 0;
        int emptySpots = 0;
        
        while (true) {
            emptySpots = maxAnimals - animalCount;
            
            t.pl("\tLunarArk");
            t.pl("("+"Animals: "+ animalCount + " Empty Spots: " + emptySpots + ")\n");
            t.pl("0. Load from file");
            t.pl("1. Save to file");
            t.pl("2. Add an Animal");
            t.pl("3. Delete an Animal");
            t.pl("4. Find Animal");
            t.pl("5. View the Animals");
            t.pl("6. Update Cloud");
            t.pl("Q. to quit");
            option = t.askForString("Select (e.g.1 ) > ");
            
            if (option.equalsIgnoreCase("Q")) {
                break;
            }
            
            if (option.equals("0")) {
                ark = loadArkFromFile(ark, animalCount, maxAnimals);
                animalCount = numAnimals(ark);
            }
            
            if (option.equals("1")) {
                saveArkToFile(ark, animalCount);
            }
            
            if ( option.equals("2")) {
                ark = addAnimal(ark, animalCount, maxAnimals);
                animalCount += 1;
            }
            
            if ( option.equals("3")) {
                ark = deleteAnimal(ark, animalCount);
                animalCount -= 1;
            }
            
            if ( option.equals("4")) {
                findAnimal(ark,  animalCount);
            }
            
            if ( option.equals("5")) {
                viewArk(ark, animalCount);
            }
            
            if ( option.equals("6")) {
                writeHTMLFile(ark, animalCount);
            }
        }
        t.pl("Done!");
    } //main
    
    /**
     * File reading
     * 
     * @author Eli Wood
     * @version v100
     */
    public static String[] loadArkFromFile(String[] ark, int animalCount, int maxAnimals) {
        Tools t = new Tools();
        
        String filename;
      
        filename = t.askForString("\nEnter the animal filename (e.g. animals.txt) >");
        
        try {
            File temp = new File(filename);
            Scanner in = new Scanner(temp);
            
            for (int i = 0; i < ark.length; i++) {
                ark[i] = null;
            }
            
            int count = 0;
            while(in.hasNextLine()) {
                ark[count] = in.nextLine().toLowerCase();
                count++;
            }
            
            t.quickSort(ark, 0, count-1);
            removeDupes(ark, count);
        } catch(Exception e) {
            t.pl("" + e);
        }
        
        return ark;
    }
    
    /**
     * File writing
     * 
     * @author Eli Wood
     * @version v100
     */
    public static void saveArkToFile(String[] ark, int animalCount){
        Tools t = new Tools();
        
        String filename = t.askForString("\nEnter the name of the file (e.g. animals.txt) >");
        
        t.quickSort(ark, 0, animalCount);
        
        try{
            FileWriter out = new FileWriter(filename);
            for (int i = 0; i < ark.length; i++) {
                if(ark[i] == null) {
                    break;
                }
                
                out.write(ark[i] + "\n");
            }
        } catch (Exception e) {
            t.pl(e + "");
        }
        
    }
    
    /**
     * adds an animal to the array
     * 
     * @author Eli Wood
     * @version v100
     */
    public static String[] addAnimal(String[] ark, int animalCount, int maxSize) {
        Tools t = new Tools();
        
        String animal;
        
        animal = t.askForString("\nAdd animal >");
        
        if(animalCount >= maxSize) {
            t.pl("The ark is already full");
        } else {
            ark[animalCount] = animal;
            t.quickSort(ark, 0, animalCount);
        }
        
        return ark;
    }
    
    /**
     * removes an animal from the array
     * 
     * @author Eli Wood
     * @version v100
     */
    public static String[] deleteAnimal(String[] ark, int animalCount) {
        Tools t = new Tools();
        
        String animal;
        
        animal = t.askForString("\nRemove which animal >");
        if(hasAnimal(ark, animalCount, animal)) {
            ark = delete(ark, animalCount, animal, false);
            t.pl(animal + " has been removed");
        } else {
            t.pl("That animal was not on the ark");
        }
        
        return ark;
    }
    
    /**
     * helper method to deleteAnimal
     * 
     * @author Eli Wood
     * @version v100
     */
    public static String[] delete(String[] arr, int count, String target, boolean repeat) {
        for(int i = 0; i < count; i++) {
            if(arr[i].equalsIgnoreCase(target)) {
                for(int j = i+1; j < count-1; j++) {
                    arr[j-1] = arr[j];
                }
                
                if(repeat = false) {
                    break;
                }
                i -= 1;
            }
        }
        return arr;
    }
    
    public static String[] removeDupes(String[] arr, int count) {
        for(int i = 0; i < count-1; i++) {
            if(arr[i] == arr[i+1]) {
                delete(arr, count, arr[i], false);
                i-= 1;
            }
        }
        
        return arr;
    }
    
    /**
     * checks if a specific animal is in the array
     * 
     * @author Eli Wood
     * @version v100
     */
    public static void findAnimal(String[] ark, int count) {
        Tools t = new Tools();
        
        String animal;
     
        animal = t.askForString("\nFind which animal >");
        
        if(hasAnimal(ark, count, animal)) {
            t.pl("The ark has that animal");
        } else {
            t.pl("That animal is not present");
        }
    }
    
    /**
     * helper to findAnimal
     * 
     * @author Eli Wood
     * @version v100
     */
    public static boolean hasAnimal(String[] ark, int count, String target) {
        for(int i = 0; i < count; i++) {
            if(ark[i].equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * displays every element in the array
     * 
     * @author Eli Wood
     * @version v100
     */
    public static void viewArk(String[] ark, int animalCount) {
        Tools t = new Tools();
        
        t.p("\nThe Lunar Ark");
        for(int i=0; i< animalCount; i++)
        {
            if ((i % 20) == 0) {
                t.pl("");
            }
            t.p(ark[i] + " ");
        }
        t.pl("\n");
    }
    
    /**
     * updates the html word cloud
     * 
     * @author Eli Wood
     * @version v100
     */
    public static void writeHTMLFile(String[] ark, int animalCount) {
        
    }
    
    /**
     * finds the number of animals
     * 
     * @author Eli Wood
     * @version v100
     */
    public static int numAnimals(String[] arr) {
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != null) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}

