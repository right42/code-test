package me.right.codetest.study;

import me.right.codetest.member.Member;

import java.time.LocalDateTime;

public class Study {

    private String name;

    private Member owner;

    private StudyStatus studyStatus = StudyStatus.CLOSED;

    private LocalDateTime openedDateTime;

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

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public LocalDateTime getOpenedDateTime() {
        return openedDateTime;
    }

    public void open() {
        this.studyStatus = StudyStatus.OPENED;
        this.openedDateTime = LocalDateTime.now();
    }
}
