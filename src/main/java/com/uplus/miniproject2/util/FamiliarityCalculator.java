package com.uplus.miniproject2.util;

import com.uplus.miniproject2.entity.hobby.Hobby;
import com.uplus.miniproject2.entity.proflie.MBTI;
import java.util.List;

public class FamiliarityCalculator {

    private FamiliarityCalculator() {}

    public static double calculateNameFamiliarity(String loginUserName, String clickUserName) {
        return NameFamiliarityCalculator.calculateNameFamiliarity(loginUserName, clickUserName);
    }

    public static double getMbtiFamiliarity(MBTI loginUserMbti, MBTI clickUserMbti) {
        return MbtiFamiliarity.getFamiliarityScore(loginUserMbti, clickUserMbti);
    }

    public static double calculateHobbyFamiliarity(List<Hobby> loginUserHobbies, List<Hobby> clickUserHobbies) {
        return HobbyFamiliarityCalculator.calculateHobbyFamiliarity(loginUserHobbies, clickUserHobbies);
    }
}
