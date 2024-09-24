package com.uplus.miniproject2.util;

import com.uplus.miniproject2.entity.proflie.MBTI;
import java.util.EnumMap;

public class MbtiFamiliarity {

    private static final EnumMap<MBTI, EnumMap<MBTI, Integer>> familiarityMap = new EnumMap<>(MBTI.class);

    static {
        EnumMap<MBTI, Integer> infjMap = new EnumMap<>(MBTI.class);
        infjMap.put(MBTI.INFJ, 97); infjMap.put(MBTI.INTJ, 90); infjMap.put(MBTI.INTP, 65);
        infjMap.put(MBTI.INFP, 75); infjMap.put(MBTI.ENFJ, 90); infjMap.put(MBTI.ENFP, 100);
        infjMap.put(MBTI.ENTP, 95); infjMap.put(MBTI.INTJ, 90); infjMap.put(MBTI.ISFJ, 50);
        infjMap.put(MBTI.ISTJ, 50); infjMap.put(MBTI.ISTP, 55); infjMap.put(MBTI.ISFP, 60);
        infjMap.put(MBTI.ESTJ, 40); infjMap.put(MBTI.ESFJ, 60); infjMap.put(MBTI.ESFP, 85);
        infjMap.put(MBTI.ESTP, 80);
        familiarityMap.put(MBTI.INFJ, infjMap);

        EnumMap<MBTI, Integer> intjMap = new EnumMap<>(MBTI.class);
        intjMap.put(MBTI.INFJ, 90); intjMap.put(MBTI.INTJ, 97); intjMap.put(MBTI.INTP, 75);
        intjMap.put(MBTI.INFP, 90); intjMap.put(MBTI.ENFJ, 70); intjMap.put(MBTI.ENFP, 95);
        intjMap.put(MBTI.ENTP, 90); intjMap.put(MBTI.INTP, 75); intjMap.put(MBTI.ISFJ, 45);
        intjMap.put(MBTI.ISTJ, 50); intjMap.put(MBTI.ISTP, 60); intjMap.put(MBTI.ISFP, 65);
        intjMap.put(MBTI.ESTJ, 40); intjMap.put(MBTI.ESFJ, 40); intjMap.put(MBTI.ESFP, 85);
        intjMap.put(MBTI.ESTP, 85);
        familiarityMap.put(MBTI.INTJ, intjMap);

        EnumMap<MBTI, Integer> intpMap = new EnumMap<>(MBTI.class);
        intpMap.put(MBTI.INFJ, 65); intpMap.put(MBTI.INTJ, 75); intpMap.put(MBTI.INTP, 97);
        intpMap.put(MBTI.INFP, 97); intpMap.put(MBTI.ENFJ, 55); intpMap.put(MBTI.ENFP, 95);
        intpMap.put(MBTI.ENTP, 90); intpMap.put(MBTI.INTP, 75); intpMap.put(MBTI.ISFJ, 40);
        intpMap.put(MBTI.ISTJ, 50); intpMap.put(MBTI.ISTP, 50); intpMap.put(MBTI.ISFP, 50);
        intpMap.put(MBTI.ESTJ, 40); intpMap.put(MBTI.ESFJ, 50); intpMap.put(MBTI.ESFP, 75);
        intpMap.put(MBTI.ESTP, 75);
        familiarityMap.put(MBTI.INTP, intpMap);

        EnumMap<MBTI, Integer> enfpMap = new EnumMap<>(MBTI.class);
        enfpMap.put(MBTI.INFJ, 100); enfpMap.put(MBTI.INTJ, 95); enfpMap.put(MBTI.INTP, 90);
        enfpMap.put(MBTI.INFP, 97); enfpMap.put(MBTI.ENFJ, 97); enfpMap.put(MBTI.ENFP, 100);
        enfpMap.put(MBTI.ENTP, 97); enfpMap.put(MBTI.ISFJ, 60); enfpMap.put(MBTI.ISTJ, 55);
        enfpMap.put(MBTI.ISTP, 75); enfpMap.put(MBTI.ISFP, 85); enfpMap.put(MBTI.ESTJ, 40);
        enfpMap.put(MBTI.ESFJ, 70); enfpMap.put(MBTI.ESFP, 85); enfpMap.put(MBTI.ESTP, 85);
        familiarityMap.put(MBTI.ENFP, enfpMap);

        EnumMap<MBTI, Integer> enfjMap = new EnumMap<>(MBTI.class);
        enfjMap.put(MBTI.INFJ, 90); enfjMap.put(MBTI.INTJ, 70); enfjMap.put(MBTI.INTP, 55);
        enfjMap.put(MBTI.INFP, 90); enfjMap.put(MBTI.ENFJ, 97); enfjMap.put(MBTI.ENFP, 97);
        enfjMap.put(MBTI.ENTP, 90); enfjMap.put(MBTI.ISFJ, 60); enfjMap.put(MBTI.ISTJ, 50);
        enfjMap.put(MBTI.ISTP, 70); enfjMap.put(MBTI.ISFP, 85); enfjMap.put(MBTI.ESTJ, 40);
        enfjMap.put(MBTI.ESFJ, 80); enfjMap.put(MBTI.ESFP, 85); enfjMap.put(MBTI.ESTP, 75);
        familiarityMap.put(MBTI.ENFJ, enfjMap);

        EnumMap<MBTI, Integer> entjMap = new EnumMap<>(MBTI.class);
        entjMap.put(MBTI.INFJ, 75); entjMap.put(MBTI.INTJ, 70); entjMap.put(MBTI.INTP, 70);
        entjMap.put(MBTI.INFP, 75); entjMap.put(MBTI.ENFJ, 90); entjMap.put(MBTI.ENFP, 95);
        entjMap.put(MBTI.ENTP, 95); entjMap.put(MBTI.ISFJ, 45); entjMap.put(MBTI.ISTJ, 50);
        entjMap.put(MBTI.ISTP, 65); entjMap.put(MBTI.ISFP, 70); entjMap.put(MBTI.ESTJ, 75);
        entjMap.put(MBTI.ESFJ, 65); entjMap.put(MBTI.ESFP, 85); entjMap.put(MBTI.ESTP, 90);
        familiarityMap.put(MBTI.ENTJ, entjMap);

        EnumMap<MBTI, Integer> infpMap = new EnumMap<>(MBTI.class);
        infpMap.put(MBTI.INFJ, 75); infpMap.put(MBTI.INTJ, 90); infpMap.put(MBTI.INTP, 97);
        infpMap.put(MBTI.INFP, 100); infpMap.put(MBTI.ENFJ, 90); infpMap.put(MBTI.ENFP, 97);
        infpMap.put(MBTI.ENTP, 90); infpMap.put(MBTI.ISFJ, 55); infpMap.put(MBTI.ISTJ, 60);
        infpMap.put(MBTI.ISTP, 65); infpMap.put(MBTI.ISFP, 75); infpMap.put(MBTI.ESTJ, 40);
        infpMap.put(MBTI.ESFJ, 55); infpMap.put(MBTI.ESFP, 75); infpMap.put(MBTI.ESTP, 65);
        familiarityMap.put(MBTI.INFP, infpMap);

        EnumMap<MBTI, Integer> isfjMap = new EnumMap<>(MBTI.class);
        isfjMap.put(MBTI.INFJ, 50); isfjMap.put(MBTI.INTJ, 45); isfjMap.put(MBTI.INTP, 40);
        isfjMap.put(MBTI.INFP, 55); isfjMap.put(MBTI.ENFJ, 60); isfjMap.put(MBTI.ENFP, 60);
        isfjMap.put(MBTI.ENTP, 60); isfjMap.put(MBTI.ISFJ, 97); isfjMap.put(MBTI.ISTJ, 50);
        isfjMap.put(MBTI.ISTP, 45); isfjMap.put(MBTI.ISFP, 50); isfjMap.put(MBTI.ESTJ, 80);
        isfjMap.put(MBTI.ESFJ, 90); isfjMap.put(MBTI.ESFP, 100); isfjMap.put(MBTI.ESTP, 75);
        familiarityMap.put(MBTI.ISFJ, isfjMap);

        EnumMap<MBTI, Integer> istjMap = new EnumMap<>(MBTI.class);
        istjMap.put(MBTI.INFJ, 50); istjMap.put(MBTI.INTJ, 50); istjMap.put(MBTI.INTP, 50);
        istjMap.put(MBTI.INFP, 60); istjMap.put(MBTI.ENFJ, 50); istjMap.put(MBTI.ENFP, 55);
        istjMap.put(MBTI.ENTP, 55); istjMap.put(MBTI.ISFJ, 50); istjMap.put(MBTI.ISTJ, 97);
        istjMap.put(MBTI.ISTP, 45); istjMap.put(MBTI.ISFP, 50); istjMap.put(MBTI.ESTJ, 80);
        istjMap.put(MBTI.ESFJ, 80); istjMap.put(MBTI.ESFP, 90); istjMap.put(MBTI.ESTP, 75);
        familiarityMap.put(MBTI.ISTJ, istjMap);

        EnumMap<MBTI, Integer> istpMap = new EnumMap<>(MBTI.class);
        istpMap.put(MBTI.INFJ, 55); istpMap.put(MBTI.INTJ, 60); istpMap.put(MBTI.INTP, 50);
        istpMap.put(MBTI.INFP, 65); istpMap.put(MBTI.ENFJ, 70); istpMap.put(MBTI.ENFP, 75);
        istpMap.put(MBTI.ENTP, 75); istpMap.put(MBTI.ISFJ, 45); istpMap.put(MBTI.ISTJ, 45);
        istpMap.put(MBTI.ISTP, 97); istpMap.put(MBTI.ISFP, 95); istpMap.put(MBTI.ESTJ, 50);
        istpMap.put(MBTI.ESFJ, 55); istpMap.put(MBTI.ESFP, 85); istpMap.put(MBTI.ESTP, 100);
        familiarityMap.put(MBTI.ISTP, istpMap);

        EnumMap<MBTI, Integer> isfpMap = new EnumMap<>(MBTI.class);
        isfpMap.put(MBTI.INFJ, 60); isfpMap.put(MBTI.INTJ, 65); isfpMap.put(MBTI.INTP, 50);
        isfpMap.put(MBTI.INFP, 75); isfpMap.put(MBTI.ENFJ, 85); isfpMap.put(MBTI.ENFP, 85);
        isfpMap.put(MBTI.ENTP, 85); isfpMap.put(MBTI.ISFJ, 50); isfpMap.put(MBTI.ISTJ, 50);
        isfpMap.put(MBTI.ISTP, 95); isfpMap.put(MBTI.ISFP, 97); isfpMap.put(MBTI.ESTJ, 45);
        isfpMap.put(MBTI.ESFJ, 65); isfpMap.put(MBTI.ESFP, 100); isfpMap.put(MBTI.ESTP, 95);
        familiarityMap.put(MBTI.ISFP, isfpMap);

        EnumMap<MBTI, Integer> esfjMap = new EnumMap<>(MBTI.class);
        esfjMap.put(MBTI.INFJ, 60); esfjMap.put(MBTI.INTJ, 40); esfjMap.put(MBTI.INTP, 50);
        esfjMap.put(MBTI.INFP, 55); esfjMap.put(MBTI.ENFJ, 80); esfjMap.put(MBTI.ENFP, 70);
        esfjMap.put(MBTI.ENTP, 65); esfjMap.put(MBTI.ISFJ, 90); esfjMap.put(MBTI.ISTJ, 80);
        esfjMap.put(MBTI.ISTP, 55); esfjMap.put(MBTI.ISFP, 65); esfjMap.put(MBTI.ESTJ, 97);
        esfjMap.put(MBTI.ESFJ, 97); esfjMap.put(MBTI.ESFP, 90); esfjMap.put(MBTI.ESTP, 80);
        familiarityMap.put(MBTI.ESFJ, esfjMap);

        EnumMap<MBTI, Integer> estjMap = new EnumMap<>(MBTI.class);
        estjMap.put(MBTI.INFJ, 40); estjMap.put(MBTI.INTJ, 40); estjMap.put(MBTI.INTP, 40);
        estjMap.put(MBTI.INFP, 40); estjMap.put(MBTI.ENFJ, 75); estjMap.put(MBTI.ENFP, 40);
        estjMap.put(MBTI.ENTP, 75); estjMap.put(MBTI.ISFJ, 80); estjMap.put(MBTI.ISTJ, 80);
        estjMap.put(MBTI.ISTP, 50); estjMap.put(MBTI.ISFP, 45); estjMap.put(MBTI.ESTJ, 97);
        estjMap.put(MBTI.ESFJ, 97); estjMap.put(MBTI.ESFP, 90); estjMap.put(MBTI.ESTP, 75);
        familiarityMap.put(MBTI.ESTJ, estjMap);

        EnumMap<MBTI, Integer> estpMap = new EnumMap<>(MBTI.class);
        estpMap.put(MBTI.INFJ, 80); estpMap.put(MBTI.INTJ, 85); estpMap.put(MBTI.INTP, 75);
        estpMap.put(MBTI.INFP, 65); estpMap.put(MBTI.ENFJ, 75); estpMap.put(MBTI.ENFP, 85);
        estpMap.put(MBTI.ENTP, 90); estpMap.put(MBTI.ISFJ, 75); estpMap.put(MBTI.ISTJ, 75);
        estpMap.put(MBTI.ISTP, 100); estpMap.put(MBTI.ISFP, 95); estpMap.put(MBTI.ESTJ, 75);
        estpMap.put(MBTI.ESFJ, 80); estpMap.put(MBTI.ESFP, 90); estpMap.put(MBTI.ESTP, 97);
        familiarityMap.put(MBTI.ESTP, estpMap);

        EnumMap<MBTI, Integer> esfpMap = new EnumMap<>(MBTI.class);
        esfpMap.put(MBTI.INFJ, 85); esfpMap.put(MBTI.INTJ, 85); esfpMap.put(MBTI.INTP, 75);
        esfpMap.put(MBTI.INFP, 75); esfpMap.put(MBTI.ENFJ, 85); esfpMap.put(MBTI.ENFP, 85);
        esfpMap.put(MBTI.ENTP, 85); esfpMap.put(MBTI.ISFJ, 100); esfpMap.put(MBTI.ISTJ, 90);
        esfpMap.put(MBTI.ISTP, 85); esfpMap.put(MBTI.ISFP, 97); esfpMap.put(MBTI.ESTJ, 90);
        esfpMap.put(MBTI.ESFJ, 90); esfpMap.put(MBTI.ESFP, 97); esfpMap.put(MBTI.ESTP, 90);
        familiarityMap.put(MBTI.ESFP, esfpMap);

        EnumMap<MBTI, Integer> entpMap = new EnumMap<>(MBTI.class);
        entpMap.put(MBTI.INFJ, 95); entpMap.put(MBTI.INTJ, 100); entpMap.put(MBTI.INTP, 100);
        entpMap.put(MBTI.INFP, 90); entpMap.put(MBTI.ENFJ, 90); entpMap.put(MBTI.ENFP, 97);
        entpMap.put(MBTI.ENTP, 85); entpMap.put(MBTI.ISFJ, 97); entpMap.put(MBTI.ISTJ, 60);
        entpMap.put(MBTI.ISTP, 60); entpMap.put(MBTI.ISFP, 80); entpMap.put(MBTI.ESTJ, 55);
        entpMap.put(MBTI.ESFJ, 55); entpMap.put(MBTI.ESFP, 75); entpMap.put(MBTI.ESTP, 75);

        familiarityMap.put(MBTI.ENTP, entpMap);
    }

    public static int getFamiliarityScore(MBTI mbti1, MBTI mbti2) {
        return familiarityMap.get(mbti1).get(mbti2);
    }
}
