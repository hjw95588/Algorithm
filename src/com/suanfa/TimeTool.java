package com.suanfa;

import java.time.Duration;
import java.time.Instant;

public class TimeTool {

	public interface Task {
		void showConsumeTime();
	}

	public static void check(String title, Task task) {
		Instant start = Instant.now();
		task.showConsumeTime();
		Instant end = Instant.now();
		long timeElapsed = Duration.between(start, end).toMillis();
		System.out.println(title + "耗时：" + (timeElapsed / 1000.0) + "秒" +"----"+timeElapsed);
	}

}
