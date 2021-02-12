import java.io.*;
import java.lang.Character.UnicodeBlock;
import java.util.*;
public class Chinese {

	public static void main(String[] args) throws FileNotFoundException 
	{
		
		Hashtable<String, String> Translate = new Hashtable<String, String>();
		int count = 0;
		String [] split = null;
		ArrayList<String> store = new ArrayList<String>();
		ArrayList<String> storetext = new ArrayList<String>();
		Scanner scan = new Scanner(new FileInputStream("ceDict4.u8"), "UTF-8");
		Scanner scan1 = new Scanner(new FileInputStream("translate.txt"), "UTF-8");
		while (scan.hasNextLine())
		{
		String line = scan.nextLine();
		split = line.split("\t");
		String pair1 = getChinese(split[0]);
		String pair2 = split[1];
		store.add(pair1);
		store.add(pair2);
		count+=2;
		}
		for (int k = 0; k<store.size()-1; k+=2)
		{
			Translate.put(store.get(k), store.get(k+1));
		
		}
		while (scan1.hasNextLine())
		{
		String line1 = scan1.nextLine();
		storetext.add(line1);
		
		}
		int listsize = 0;
		while (listsize<storetext.size())
		{
		String word1 = storetext.get(listsize);
		for (int b = 0; b<=word1.length()-1; b++)
		{
			for (int i = word1.length(); i>=b; i--)
			{
				String temp = word1.substring(b, i);
				if (Translate.containsKey(temp))
				{
					System.out.print(Translate.get(temp) + " ");
				
				}
			}
		}
	
		System.out.println();
		listsize++;
		
		}
		
	}
	
	public static String getChinese(String input)
	{
	Set<UnicodeBlock> chineseUnicodeBlocks = new HashSet<UnicodeBlock>() {{
	add(UnicodeBlock.CJK_COMPATIBILITY);
	add(UnicodeBlock.CJK_COMPATIBILITY_FORMS);
	add(UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS);
	add(UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
	add(UnicodeBlock.CJK_RADICALS_SUPPLEMENT);
	add(UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
	add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
	add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
	add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
	add(UnicodeBlock.KANGXI_RADICALS);
	add(UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS);
	}};
	ArrayList<Character> words = new ArrayList<Character>();
	for (char c : input.toCharArray()) {
	if (chineseUnicodeBlocks.contains(UnicodeBlock.of(c))) {
	words.add(c);
	}
	}
	String result = "";
	for(Character c : words)
	result += c;
	return result;
	}

}
