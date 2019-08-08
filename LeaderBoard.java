
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Scanner;

/**
 * class for ordering the highest of four scores and determining the players position in leaderboard. 
 * @author Paramesh
 *
 */


public class LeaderBoard {

	public static void main(String args[]){
		char input[] = "A [7,10,2,6], B [2,9,8,9], C [2,10,1,2]".toCharArray();
		
		//		When input is given through console
		//		Scanner scanInput = new Scanner(System.in);
		//		char input[] = scanInput.nextLine().toCharArray();
		
		determinePosition(input);
	}
	
	
	
	private static void determinePosition(char[] input){
		
		Map<Integer,List<String>> resultMap = formMap(input);
		resultMap.keySet().stream().sorted(Comparator.reverseOrder()).forEach(key -> {

			List<String> resultList = resultMap.get(key);
			int size = resultList.size();
			String comma=",";
			for(int index = 0;index < size;index++){
				if(index==size-1){
					comma = "";
				}
				System.out.print(resultList.get(index)+comma);
			}
			System.out.println();
		});
		
	}
	
	
	private static Map<Integer,List<String>> formMap(char[] input){
		boolean digit = false;
		boolean prevDigit = false;
		String digitValue = "";
		int highest = 0;

		boolean letter = false;
		boolean prevLetter = false;
		String letterValue = "";
		String keyLetter = "";
		
		Map<Integer,List<String>> resultMap = new HashMap<>();
		
		for(char value : input){
			
			// arranging numeric values
			
			digit = Character.isDigit(value);
			
			if(digit){
				digitValue = digitValue+value;
				prevDigit = digit;
			}
			if(!digit && prevDigit){
				int compareValue = Integer.parseInt(digitValue);
				if(compareValue > highest){
					highest = compareValue;
				}
				prevDigit = false;
				digitValue="";
			}
			
			// arranging alphabets
			
			letter = Character.isLetter(value);
			
			if(letter){
				letterValue = letterValue+value;
				prevLetter = letter;
			}
			if(value==']'){
				List<String> playerList = resultMap.get(highest);
				if(playerList==null){
					playerList = new ArrayList<>();
				}
				playerList.add(keyLetter);
				resultMap.put(highest, playerList);
			}
			if(!letter && prevLetter){
				prevLetter = false;
				keyLetter = letterValue; 
				highest = 0;
				letterValue="";
			}
			
			
			
			
		}
		return resultMap;
	}
}
