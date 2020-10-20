package syntaxConstructions;

public class SyntaxConstructions {
	public static void main(String[] args) {
		////////
		// if //
		////////////////////////
		// if (condition) op1;//
		// else op2;          //
		////////////////////////
		int a1 = 10;
		
		if (10 == a1)
			System.out.println("a == 10? " + true);
		else
			System.out.println("a == 10" + false);
		
		/////////////
		// if-else //
		//////////////////////////////
		// if (condition) op1;      //
		// else if (condition) op2; //
		// else if (condition) op3; //
		// ...                      //
		// else opn                 //
		//////////////////////////////
		int a2 = 10, b2 = 20;
		int n2 = 15;
		
		if ((n2 >= a2) && (n2 <= b2))
			System.out.println("n2 = " + n2 + " in range " + a2 + " - " + b2);
		else if (n2< a2)
			System.out.println("n2 = " + n2 + " is bellow the range");
		else 
			System.out.println("n2 = " + n2 + " is upon the range");
		
		////////////
		// switch //
		/////////////////////////
		// switch (condition){ //
		//    case value1:     //
		//       op1;          //
		//       break;        //
		//    case value2:     //
		//       op2;          //
		//       break;        //
		//    ...              //
		//    default:         //
		//       opn;          //
		// }                   //   
		/////////////////////////
		int season = 1;
		switch (season){
		case 1:
			System.out.println("Winter");
			break;
		case 2:
			System.out.println("Spring");
			break;
		case 3:
			System.out.println("Summer");
			break;
		case 4:
			System.out.println("Autumn'");
			break;
		default:
			System.out.println("undefined");
		}
		
		
		///////////
		// while //
		/////////////////////////
		// while (condition){  //
		//    op1;             //
		// }                   //   
		/////////////////////////
		int i = 5;
		while (i > 0){
			System.out.println(i--);
		}
		System.out.println("BOOM!");
		
		
		//////////////
		// do-while //
		/////////////////////////
		// do {                //
		//    op1;             //
		// }while (condition)  //   
		/////////////////////////
		
		
		/////////
		// for //
		/////////////////////////////////////
		// for(init; conditions; repeat){ //
		// 	  op1;                         //
		// }                               //
		/////////////////////////////////////
		
		
		//////////////
		// for-each //
		/////////////////////////////////////
		// for(type var:collection){ //
		// 	  op1;                         //
		// }                               //
		/////////////////////////////////////
		int[] mass = {1,2,3,4,5,6,7,8,9,0};
		for(int element:mass){
			System.out.print(element);
		}
	}
}
