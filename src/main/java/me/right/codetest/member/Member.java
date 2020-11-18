package me.right.codetest.member;

public class Member {

    private Long memberId;

    private String name;

    public Member() {}

    public Member(Long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
