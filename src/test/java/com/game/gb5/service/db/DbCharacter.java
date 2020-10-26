package com.game.gb5.service.db;

import com.game.gb5.model.character.Character;
import com.game.gb5.model.character.CharacterStatus;
import com.game.gb5.model.character.HittingPosition;
import java.util.List;

public class DbCharacter {

  public static final CharacterStatus HARU_STATUS =
      new CharacterStatus(31, 68, 87,
          85, 90, 52, 57,
          53, 44, 44, 72);
  public static final Character HARU = Character.builder()
      .code("test-1")
      .name("하루")
      .grade(2)
      .hittingPosition(HittingPosition.RIGHT)
      .hittingInclination(List.of(29.0, 13.0, 24.0, 1.0, 33.0))
      .characterStatus(HARU_STATUS).build();

  public static final CharacterStatus HANA_STATUS =
      new CharacterStatus(58, 68, 87, 68, 52, 52, 40, 73, 69, 44, 72);
  public static final Character HANA = Character.builder()
      .code("test-2")
      .name("하나")
      .grade(2)
      .hittingPosition(HittingPosition.RIGHT)
      .hittingInclination(List.of(9.0, 10.0, 27.0, 49.0, 5.0))
      .characterStatus(HANA_STATUS).build();

  public static final CharacterStatus YUJIN_STATUS =
      new CharacterStatus(74, 72, 54, 41, 39, 68, 36, 43, 41, 60, 63);
  public static final Character YUJIN = Character.builder()
      .code("test-3")
      .name("유")
      .grade(1)
      .hittingPosition(HittingPosition.RIGHT)
      .hittingInclination(List.of(23.0, 36.0, 7.0, 15.0, 19.0))
      .characterStatus(YUJIN_STATUS).build();

  public static final CharacterStatus SUYEON_STATUS =
      new CharacterStatus(40, 33, 81, 32, 63, 44, 42, 79, 52, 32, 77);
  public static final Character SUYEON = Character.builder()
      .code("test-4")
      .name("수연")
      .grade(1)
      .hittingPosition(HittingPosition.RIGHT)
      .hittingInclination(List.of(10.0, 27.0, 5.0, 9.0, 49.0))
      .characterStatus(SUYEON_STATUS).build();

  public static final CharacterStatus SENA_STATUS =
      new CharacterStatus(45, 37, 33, 41, 47, 70, 54, 51, 49, 65, 71);
  public static final Character SENA = Character.builder()
      .code("test-5")
      .name("세나")
      .grade(1)
      .hittingPosition(HittingPosition.RIGHT)
      .hittingInclination(List.of(7.0, 41.0, 1.0, 13.0, 38.0))
      .characterStatus(SENA_STATUS).build();

  public static final CharacterStatus YUKI_STATUS =
      new CharacterStatus(42, 25, 72, 73, 76, 29, 40, 37, 21, 63, 68);
  public static final Character YUKI = Character.builder()
      .code("test-6")
      .name("유키")
      .grade(1)
      .hittingPosition(HittingPosition.RIGHT)
      .hittingInclination(List.of(21.0, 7.0, 18.0, 3.0, 51.0))
      .characterStatus(YUKI_STATUS).build();

  public static final CharacterStatus LIA_STATUS =
      new CharacterStatus(72, 63, 72, 52, 48, 68, 44, 34, 29, 25, 30);
  public static final Character LIA = Character.builder()
      .code("test-7")
      .grade(1)
      .hittingPosition(HittingPosition.RIGHT)
      .hittingInclination(List.of(25.0, 27.0, 9.0, 38.0, 1.0))
      .characterStatus(LIA_STATUS).build();

  public static final CharacterStatus LILITH_STATUS =
      new CharacterStatus(72, 63, 72, 52, 48, 68, 44, 34, 29, 25, 30);
  public static final Character LILITH = Character.builder()
      .code("test-8")
      .grade(1)
      .hittingPosition(HittingPosition.RIGHT)
      .hittingInclination(List.of(25.0, 13.0, 19.0, 6.0, 37.0))
      .characterStatus(LILITH_STATUS).build();

  public static final CharacterStatus MEGU_STATUS =
      new CharacterStatus(67, 50, 67, 57, 51, 42, 39, 52, 34, 63, 62);
  public static final Character MEGU = Character.builder()
      .code("test-9")
      .grade(1)
      .hittingPosition(HittingPosition.RIGHT)
      .hittingInclination(List.of(3.0, 4.0, 46.0, 33.0, 14.0))
      .characterStatus(MEGU_STATUS).build();

  public static final CharacterStatus MAYU_STATUS =
      new CharacterStatus(53, 75, 34, 54, 21, 71, 58, 26, 62, 54, 66);
  public static final Character MAYU = Character.builder()
      .code("test-10")
      .grade(1)
      .hittingPosition(HittingPosition.LEFT)
      .hittingInclination(List.of(39.0, 20.0, 9.0, 7.0, 25.0))
      .characterStatus(MAYU_STATUS).build();

  public static final CharacterStatus EIONE_STATUS =
      new CharacterStatus(68, 42, 61, 70, 55, 37, 53, 60, 77, 12, 8);
  public static final Character EIONE = Character.builder()
      .code("test-11")
      .grade(1)
      .hittingPosition(HittingPosition.LEFT)
      .hittingInclination(List.of(19.0, 31.0, 30.0, 19.0, 1.0))
      .characterStatus(EIONE_STATUS).build();
}