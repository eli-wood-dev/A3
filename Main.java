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
                animalCount = loadArkFromFile(ark, animalCount);
            }
            
            if (option.equals("1")) {
                saveArkToFile(ark);
            }
            
            if ( option.equals("2")) {
                animalCount = addAnimal(ark, animalCount);
            }
            
            if ( option.equals("3")) {
                animalCount = deleteAnimal(ark, animalCount);
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
    
    public static int loadArkFromFile(String[] ark, int animalCount) {
        Tools t = new Tools();
        
        String filename;
      
        filename = t.askForString("\nEnter the animal filename (e.g. animals.txt) >");
        return animalCount;
    }
    
    public static void saveArkToFile(String[] ark){
        Tools t = new Tools();
        
        String filename = t.askForString("\nEnter the name of the file (e.g. animals.txt) >");
        
        t.quickSort(ark, 0, ark.length);
        
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
    
    public static int addAnimal(String[] ark, int animalCount) {
        Tools t = new Tools();
        
        String animal;
        
        animal = t.askForString("\nAdd animal >");
        return animalCount;
    }
    
    public static int deleteAnimal(String[] ark, int animalCount) {
        Tools t = new Tools();
        
        String animal;
        
        animal = t.askForString("\nRemove which animal >");
        
        return animalCount;
    }
    
    public static void findAnimal(String[] ark, int n) {
        Tools t = new Tools();
        
        String animal;
     
        t.askForString("\nFind which animal >");
    }
    
    public static void viewArk(String[] ark, int animalCount) {
        Tools t = new Tools();
        
        t.p("\nThe Lunar Ark");
        for(int i=0; i< animalCount; i++)
        {
            if ((i % 21) == 0) {
                t.pl("");
            }
            t.p(ark[i] + " ");
        }
    }
    
    public static void writeHTMLFile(String[] ark, int animalCount) {
        
    }
}

