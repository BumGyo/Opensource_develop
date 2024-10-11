package homework2;

import java.util.Scanner;

public class GBBGameApp {

	public static void main(String[] args) {
		System.out.println("가위바위보 게임입니다. 가위, 바위, 보 중에서 입력하세요");
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("철수 >> ");
		String willyPick = scanner.next();
		System.out.print("영희 >> ");
		String shallyPick = scanner.next();
		
		if((willyPick.equals("가위") && shallyPick.equals("보")) || (willyPick.equals("바위") && shallyPick.equals("가위")) || (willyPick.equals("보") && shallyPick.equals("바위"))) {
			System.out.println("철수가 이겼습니다.");
		} else if((willyPick.equals("가위") && shallyPick.equals("가위")) || (willyPick.equals("바위") && shallyPick.equals("바위")) || (willyPick.equals("보") && shallyPick.equals("보"))) {
			System.out.println("비겼습니다.");
		} else {
			System.out.println("영희가 이겼습니다.");
		}
	}
}