package com.game.gb5.utils.random;

import java.util.Random;

public class RandomMaker {
	public Long makeSeed() {
		return new Random().nextLong();
	}
}
