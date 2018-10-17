import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Description for HashMap:
 * I created 2 options for the user. First is to display all the parks in Seattle on the map,
 * and the other is to display selected parks by entering the id.
 * 
 * I created two HashMaps, one for the park names and the other for the coordinates.
 * I used HashMap to link the ID of the parks to the park names and coordinates.
 * When the user enters the ID of the park, they will obtain the name and coordinates for the park,
 * and the parks with the ID entered will be shown on the google map.
 * 
 */

public class myMap  {
    static String title;
    static String latitude;
    static String longitude;
    static int zoom;
    static int width;
    static int height;
    static int scale;
    static String type;
    static String mark;
    
public static void main(String[] args) throws FileNotFoundException { 
    bigData();
    new Map(title,latitude, longitude,zoom, width, height, scale, type, mark); 
  }
 
public static void bigData() throws FileNotFoundException{ 
	
    title="Seattle Parks";
    width=800;
    height=600;    
    latitude="47.610378";     //coordinates cover all of Seattle
    longitude="-122.330576";
    zoom=11;
    scale=1;
    type="roadmap"; 
	
	Scanner userinput = new Scanner(System.in);
	Scanner input = new Scanner(new File("big data seattle park.csv"));   
	input.nextLine();
	HashMap<String, String> hmname = new HashMap<String, String>();
	HashMap<String, String> hmcoord = new HashMap<String, String>();
	
	System.out.println("(1) Display all parks \n(2) Find individual parks");
	String response1 = userinput.nextLine();
	
	//case if user chooses to find individual parks
	if(response1.equalsIgnoreCase("2") || response1.equalsIgnoreCase("find individual parks"))
	{
		while(input.hasNextLine())
		{
			Park p = new Park();
			String line = input.nextLine();
			String [] details = line.split(",");
			p.id = details[0];
			p.name = details[2];
			p.zip = details[4];
			p.latitude = details[5];
			p.longitude = details[6];
			
			hmname.put(p.id, p.name);
			hmcoord.put(p.id, p.longitude+","+p.latitude);
			System.out.println(p.id+" "+p.name+"  ("+p.longitude+","+p.latitude+")");
		}
		
		int i=0;
		boolean correct = false;
		
		while(!correct)
		{
			i++;
			
			System.out.print("Enter PMAID to get location: ");
			String response2 = userinput.next();
			            
			mark+= "&markers=color:red%7Clabel:"+i+"%7C"+hmcoord.get(response2); 
			
			System.out.println("Find another park? (y/n): ");
			
			if(userinput.next().equalsIgnoreCase("n"))
				correct=true;
		}
	}
	
	//case if user chooses to display all parks in Seattle
	else if(response1.equalsIgnoreCase("1") || response1.equalsIgnoreCase("display all parks"))
	{
		while(input.hasNextLine())
		{
			Park p = new Park();
			String line = input.nextLine();
			String [] details = line.split(",");
			p.id = details[0];
			p.name = details[2];
			p.zip = details[4];
			p.latitude = details[5];
			p.longitude = details[6];
			
			mark+="&markers=color:red%7Clabel:P%7C"+p.longitude+","+p.latitude;
		}
	}
	
    }
}

class Park
{
	String id, name, zip, latitude, longitude;
} 
