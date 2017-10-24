package com.riskitbiskit.fundamentalsbasketballdrills.Drills;

import java.io.Serializable;

public class Drill implements Serializable {

    public static final int SKILL_LEVEL_FROSH = 1;
    public static final int SKILL_LEVEL_SOPH = 2;
    public static final int SKILL_LEVEL_JUNIOR = 3;
    public static final int SKILL_LEVEL_SENIOR = 4;

    public static final int SKILL_TYPE_DRIBBLE = 10;
    public static final int SKILL_TYPE_PASS = 20;
    public static final int SKILL_TYPE_SHOOT = 30;

    private int id;
    private int skillLevel;
    private int skillType;
    private String skillName;
    private String skillVideoId;
    private String skillChallenge;

    public Drill(int id, int skillLevel, int skillType, String skillName, String skillVideoId, String skillChallenge) {
        this.id = id;
        this.skillLevel = skillLevel;
        this.skillType = skillType;
        this.skillName = skillName;
        this.skillVideoId = skillVideoId;
        this.skillChallenge = skillChallenge;
    }

    public int getId() {
        return id;
    }

    public int getSkillLevel() {
        switch (skillLevel) {
            case SKILL_LEVEL_FROSH:
                return SKILL_LEVEL_FROSH;
            case SKILL_LEVEL_SOPH:
                return SKILL_LEVEL_SOPH;
            case SKILL_LEVEL_JUNIOR:
                return SKILL_LEVEL_JUNIOR;
            case SKILL_LEVEL_SENIOR:
                return SKILL_LEVEL_SENIOR;
            default:
                return SKILL_LEVEL_FROSH;
        }
    }

    public int getSkillType() {
        switch (skillType) {
            case SKILL_TYPE_DRIBBLE:
                return SKILL_TYPE_DRIBBLE;
            case SKILL_TYPE_PASS:
                return SKILL_TYPE_PASS;
            case SKILL_TYPE_SHOOT:
                return SKILL_TYPE_SHOOT;
            default:
                return SKILL_TYPE_DRIBBLE;
        }
    }

    public String getSkillName() {
        return skillName;
    }

    public String getSkillVideoId() {
        return skillVideoId;
    }

    public String getSkillChallenge() {
        return skillChallenge;
    }
}
