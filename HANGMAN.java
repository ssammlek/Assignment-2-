package eg.edu.alexu.csd.datastructure.hangman.cs31;
import java.util.*;
import java.io.*;
public class HANGMAN implements IHangman {
//declaration
	//@SuppressWarnings("unused")
	private static int Guess; //max guesses
	private static String[] dictionary; //array of strings
	private static char[] blanks; //array of chars the user sees
	private static String blanks2; //after writing a new char
	private static String SECRET; //the secret word that we will randomly get it
public String[] Readfile(String path)
{
	//create array of strings
	String arr[]=new String[1000];
	try
	{
	//bufferreader
	BufferedReader br = new BufferedReader(new FileReader(path));
	//read strings
	for(int i=0;i<1000;i++)
	{
		arr[i]= br.readLine();
	}
	br.close(); //close stream we need throw IO exception 
	}
	catch (IOException e)
	{
		System.out.println("Error");
		e.printStackTrace();
	}
	return arr;
}
public void setDictionary(String[] words)
{
	dictionary = words; //to be declared for any other function
}
public String selectRandomSecretWord()
{
	Random rannum = new Random();
	int num = rannum.nextInt(1001); //to give me range from 0 to 1000
	String temp = dictionary[num];
	SECRET= temp; //now we picked a random secter word
	//now we make blanks
	for(int i=0;i<SECRET.length();i++)
	{
		blanks[i]='-';
		if(SECRET.charAt(i)==' ')
		{
			blanks[i]=' ';
		}
	}
	return temp;
}
public String guess(Character c) throws Exception
{
	if(c>='A' && c<='z')
	{
		if(SECRET.contains(Character.toLowerCase(c)+"")) //if the char is exist
		{
			for(int j=0;j<SECRET.length();j++) 
			{
				if(SECRET.toLowerCase().charAt(j)==Character.toLowerCase(c)) //check how many times it is exist
				{
					blanks[j]=Character.toLowerCase(c);
				}
			}
			
		}
		else
		{
			Guess--;
		}
		blanks2 = new String(blanks); 
		if(blanks2.equals(SECRET))
		{
			System.out.println("we have a winner");
			return null;
		}
		
		if(Guess==0)
		{
			System.out.println("unfortunately you have lost");
			return null;
		}
		return blanks2; //show to user the blanks after guessing  word
	}
	else 
	{
		throw new Exception("Choose valid letter");
	}
	
}
public void setMaxWrongGuesses(Integer max)
{
	Guess = max;
	if(max==null || max<1)
	{
		Guess = 1;
	}
}
}
