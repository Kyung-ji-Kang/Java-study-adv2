package annotation.validation;

public class Team {

    @NotEmpty(message =  "이름이 비어있습니다.")
    private String name;

    @Range(min = 1, max = 999, message = "회원 수는 1과 999 사이여야 합니다.")
    private int membercount;

    public Team(String name, int membercount) {
        this.name = name;
        this.membercount = membercount;
    }

    public String getName() {
        return name;
    }

    public int getMembercount() {
        return membercount;
    }


}
