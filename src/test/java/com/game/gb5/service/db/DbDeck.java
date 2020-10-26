package com.game.gb5.service.db;

import com.game.gb5.model.deck.Position;
import java.util.Map;

public class DbDeck {

  public static final String TEST_DECK_1_CODE = "test-deck1";
  public static final String TEST_DECK_2_CDOE = "test-deck2";

  public static final Map<Position, String> TEST_DECK_MAP_1 = Map.of(
      Position.FIRST_BASE, "test-4",
      Position.SECOND_BASE, "test-3",
      Position.THIRD_BASE, "test-6",
      Position.MID_FIELDER, "test-2",
      Position.SHORT_STOP, "test-9",
      Position.BENCH1, "test-5",
      Position.BENCH2, "test-7",
      Position.BENCH3, "test-8"
  );

  public static final Map<Position, String> TEST_DECK_MAP_2 = Map.of(
      Position.FIRST_BASE, "test-1",
      Position.SECOND_BASE, "test-7",
      Position.THIRD_BASE, "test-9",
      Position.MID_FIELDER, "test-11",
      Position.SHORT_STOP, "test-8",
      Position.BENCH1, "test-4",
      Position.BENCH2, "test-2",
      Position.BENCH3, "test-3"
  );
}
