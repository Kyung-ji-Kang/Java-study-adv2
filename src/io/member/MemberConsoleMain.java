package io.member;

import io.member.impl.DataMEmberRepository;
import io.member.impl.FileMemberRepository;
import io.member.impl.MemoryMemberRepository;

import java.util.List;
import java.util.Scanner;

public class MemberConsoleMain {

    //private static  final MemberRepository repository = new MemoryMemberRepository();
    //private static final FileMemberRepository repository = new FileMemberRepository();
    private static final DataMEmberRepository repository = new DataMEmberRepository();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("1.회원 등록 | 2.회원 목록 조회 | 3.종료");
            System.out.print("선택: ");
            int choice = sc.nextInt();
            sc.nextLine(); // newline 제거

            switch (choice){
                case 1:
                    // 회원 등록
                    registerMember(sc);
                    break;
                case 2:
                    // 회원 목록 조회
                    displayMembers();
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력하세요.");

            }
        }

    }



    private static void registerMember(Scanner sc) {
        System.out.print("ID입력: ");
        String id = sc.nextLine();

        System.out.print("Name 입력: ");
        String name = sc.nextLine();

        System.out.print("Age 입력: ");
        int age = sc.nextInt();
        sc.nextLine(); // newLine 제거

        Member member = new Member(id,name,age);
        repository.add(member);
        System.out.println("회원이 성공적으로 등록 되었습니다");
    }

    private static void displayMembers() {
        List<Member> members = repository.findAll();
        System.out.println("회원 목록: ");

        for(Member member : members){
            System.out.printf("[ID: %s, Name: %s, Age: %d]\n", member.getId(),
                    member.getName(), member.getAge());
        }
    }
}
