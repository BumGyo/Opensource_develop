package homework3;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
	static void question() {
		Random random = new Random();
		int answer = random.nextInt(100);
		System.out.println("수를 결정하였습니다. 맞추어 보세요");
		System.out.println("0-99");
		
		Scanner scanner = new Scanner(System.in);
		
		int i = 1;
		int x = 0;
		int y = 99;
		
		while(true) {
			System.out.printf("%d>>", i);
			int user_answer = scanner.nextInt();
			i++;
			if(user_answer == answer) {
				System.out.println("맞았습니다");
				break;
			}
			else if(user_answer > answer) {
				System.out.println("더 낮게");
				y = user_answer;
				System.out.printf("%d-%d\n", x,y);
			}
			else {
				System.out.println("더 높게");
				x = user_answer;
				System.out.printf("%d-%d\n", x,y);
			}
		}
		System.out.print("다시하시겠습니까(y/n)>>");
		String text = scanner.next();
		if(text.equals("y"))
			question();
		else if(text.equals("n")) 
			System.exit(0);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		question();
	}
}
