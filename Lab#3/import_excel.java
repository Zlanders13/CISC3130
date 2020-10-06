//This program imports information from a .CSV file (excel) and exports it to a .txt
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
public class import_excel{
  public static void main(String[] args) throws IOException{
//Lab#2 Count number of times artists appear on spotify charts
    Scanner sc = new Scanner(new File("spotifyDaily_Czech.csv")); //Scanner object you read from .CSV file
    sc.useDelimiter(","); //Allows scanner to know when a piece of information ends
    File dest = new File("dest.txt"); // Creates file dest
    if(dest.exists()) // Handles compiler exception if file already exists
       throw new SecurityException("File already exists.");
    PrintWriter pw = new PrintWriter(new FileWriter(dest));
    int col = 5;
    int row = 201;
    String[][] myList = new String[row][col]; //Initiate 2D array, takes in info from CSV
    for(int i = 0;i < row;i++)
      for(int j = 0;j < col;j++){
        if(sc.hasNext()){
          myList[i][j] = sc.next();
          pw.print(myList[i][j] + "  ");
        }
      }
    sc.close();
    pw.close();
/*    Used this code to create a file that contained the Artists. This allowed me to compare my output.txt
      artist count.  *Not necessary for assignment*
    File artist = new File("artist.txt"); //Creates file
    PrintWriter pWriter=new PrintWriter(new FileWriter(artist));
    for(int i = 0; i < row;i++)     // Prints Artists from CSV
      pWriter.print(myList[i][2] + "\n");
    pWriter.close();   */
//Create new list to display Artists and times appeared  on chart
    String[][] newList = new String[130][2]; //New array that will contain columns for Artist and Times Appeared
    File output = new File("output.txt"); //Create file
    if(output.exists()) // Handles compiler exception if file already exists
       throw new SecurityException("File already exists.");
    PrintWriter printWriter = new PrintWriter(new FileWriter(output));//Print/Write file
    newList[0][0] = "Artist"; //Header for column 0
    newList[0][1] = "Times Appeared"; //Header for column 1
//Find artists from charts and count how many times they appeared
    int difference = 0;
    for(int i = 1; i < row;i++){
      int appeared = 1;
      if(myList[i][2] == "0") // Increments difference, which is used to remove gaps from newList
        difference++;
      else{
        newList[i-difference][0] = myList[i][2];
        newList[i-difference][1] = String.valueOf(appeared);
        for(int j = i+1;j<row;j++){
          if(myList[i][2].compareTo(myList[j][2]) == 0){
            myList[j][2] = "0"; //Basically crosses items off array
            appeared++; //Increments appeared for each time an artist is seen in chart
            newList[i-difference][1] = String.valueOf(appeared);
          }
        }
      }
    }
    String[] sortedArt = new String[130];
//Print results to file "output.txt"
      for(int a = 0;a < 130;a++){
            printWriter.print(newList[a][0] + "   " + newList[a][1] + "\n");
            sortedArt[a] = newList[a][0];
          }
      printWriter.close();

//Lab #3 Alphabetize list
  File sorted = new File("ArtistsSorted.txt"); //Create file
  if(sorted.exists()) // Handles compiler exception if file already exists
    throw new SecurityException("File already exists.");
  PrintWriter printWriter_sorted = new PrintWriter(new FileWriter(sorted));//Print/Write file
  List<String> sortedArtist = Arrays.asList(sortedArt);
  sortedArtist.sort(Comparator.naturalOrder());
  sortedArtist.forEach(str->printWriter_sorted.print(str + "\n"));
  printWriter_sorted.close();
 }
}
