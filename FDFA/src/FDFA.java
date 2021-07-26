import java.util.ArrayList;
import java.util.Stack;

public class FDFA 
{
	static String [] accepted;
	 //States
	 static ArrayList<String> states = new ArrayList<String>();
	 static ArrayList<String> oneTrans = new ArrayList<String>();
	 static ArrayList<String> zeroTrans = new ArrayList<String>();
	 static ArrayList<String> action = new ArrayList<String>();
	 static Stack<String> statesStack = new Stack<>();
	 static String element = "0";
	 static boolean accept = false;
	 static String actions ="";
	 static String peek = "";
	 static String finalp = "";
    
public FDFA(String dfa) 
{
	String [] temp1 = dfa.split("\\#");
	String [] temp2 = temp1[0].split(";");
	accepted = temp1[1].split(",");
	for (int i = 0 ; i < temp2.length ; i++) 
	{
		String [] temp3 = temp2[i].split(",");
		states.add(temp3[0]);
		zeroTrans.add(temp3[1]);
		oneTrans.add(temp3[2]);
		action.add(temp3[3]);
	}
}


public static String Run(String input) 
{
	statesStack = new Stack<>();
	element = "0";
	accept = false;
	
	for ( int i = 0 ; i < input.length() ; i++) 
	{
		if (i == input.length()-1 ) 
		{

			if (input.charAt(i) == '0') 
			{
				element = zeroTrans.get(states.indexOf(element));
				statesStack.push(element);
				accept = checkGoal(statesStack.peek());
				if (accept == true) {
					actions = actions + action.get(states.indexOf(statesStack.peek()));
					break;
				}
				else 
				{  
					peek = statesStack.peek();
					while (!statesStack.isEmpty())
					{
					 if (checkGoal(statesStack.peek())) 
					 {
						 actions =actions+ action.get(states.indexOf(statesStack.peek()));
						 Run(input.substring(statesStack.size(), input.length()));
						 break;
					 }
					 else if(statesStack.size() == 1) {
						 if (checkGoal(statesStack.peek())) 
						 {
							 actions =actions+ action.get(states.indexOf(statesStack.peek()));
							 statesStack.pop();	
							 
						 }
						 else {
							actions = actions + action.get(states.indexOf(peek));
							statesStack.pop();		
						 }
						}
					 else 
					 {
						 statesStack.pop();	 
					 }	
					}
				}
			}
			else if (input.charAt(i) == '1') 
			{
				element = oneTrans.get(states.indexOf(element));
				statesStack.push(element);
				accept = checkGoal(statesStack.peek());
				if (accept == true) {
					actions = actions + action.get(states.indexOf(statesStack.peek()));
				break;
			}
				else 
				{
					peek = statesStack.peek();
					while (!statesStack.isEmpty())
					{
					 if (checkGoal(statesStack.peek())) 
					 {
						
						 actions =actions+ action.get(states.indexOf(statesStack.peek()));
						 Run(input.substring(statesStack.size(), input.length()));
						 break;
					 }
					 else if(statesStack.size() == 1) {
						 if (checkGoal(statesStack.peek())) 
						 {
							 actions =actions+ action.get(states.indexOf(statesStack.peek()));
							 statesStack.pop();	
							 
						 }
						 else {
							actions = actions + action.get(states.indexOf(peek));
							statesStack.pop();	
							
						 }
						}
					 else 
					 {
						 statesStack.pop();	 
					 }	
					}
				}
			}
		}
		else 
		{
			if (input.charAt(i) == '0') 
			{
				element = zeroTrans.get(states.indexOf(element));
				statesStack.push(element);
			}
			if (input.charAt(i) == '1') 
			{
				element = oneTrans.get(states.indexOf(element));
				statesStack.push(element);
			}
		}	
	}
	return actions;	
}

public static boolean checkGoal(String x)
{
  for (int i = 0 ; i < accepted.length ; i++) {
	   if (accepted[i].equals(x)) {
			  return true;
	   }
		  
  }
  return false;
}

public static void main(String[] args) {
//
//	String ad = "0,0,1,A;1,2,3,B;2,3,0,C;3,5,4,D;4,2,5,E;5,1,5,F#2,5";
//	FDFA b = new FDFA(ad);
//	System.out.println(Run("11001"));
//	actions ="";
//	System.out.println(Run("100101"));
//	actions ="";
//   System.out.println(Run("01011"));
//	actions ="";
//	System.out.println(Run("010100"));
//	actions ="";
//     System.out.println(Run("110011"));
     
     String cd = "0,1,2,A;1,3,2,B;2,5,3,C;3,4,0,D;4,5,3,E;5,5,5,F#4,5";
 	FDFA a = new FDFA(cd);
 	System.out.println(Run("101"));
 	actions ="";
 	System.out.println(Run("0111"));
 	actions ="";
    System.out.println(Run("11011"));
 	actions ="";
 	System.out.println(Run("0110111"));
 	actions ="";
      System.out.println(Run("1111"));
}
   
}
