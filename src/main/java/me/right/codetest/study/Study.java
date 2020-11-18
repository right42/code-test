package me.right.codetest.study;

import me.right.codetest.member.Member;

public class Study {

    private String name;

    private Member owner;

    public Study(String name, Member owner) {
        this.name = name;
        this.owner = owner;
    }

    public Study() {}

    public void setOwner(Member member) {
        this.owner = member;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Member getOwner() {
        return owner;
    }
}
