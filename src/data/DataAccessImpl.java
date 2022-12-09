package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Domain.Media;

public class DataAccessImpl implements DataAccess{

    public DataAccessImpl() {
    }

    public List<List<String>> load(String path){
        List<List<String>> result = new ArrayList<>();
        try{
            File mediaFile = new File(path);
            //Scanner would not work with swedish chars, so charset ISO-8859-1 was needed.
            Scanner s = new Scanner(mediaFile, "ISO-8859-1");
            while(s.hasNextLine()){
                //Make a new List containing the elements from nextLine and split them on ";" coupled with some regex to remove potential whitespaces.
                //split returns an array, so the extra code converts the Array to an ArrayList to keep the same type as the one it is nested in.
                List<String> splitBySemi = new ArrayList<>(Arrays.asList(s.nextLine().split("\\s*;\\s*")));
                result.add(splitBySemi);
            }
            s.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return result;
    }

    //NOT YET IMPLEMENTED
    /*
    public void saveFav(String path, List<Media> data){
        try{
            File favFile = new File(path);
            PrintWriter pw = new PrintWriter(favFile);
            for(Media media : data){
                pw.println(media.toString());
            }
            pw.close();
        }catch (FileNotFoundException e){
            System.out.println("No file. Saving nothing");
        }
    }
*/
}
