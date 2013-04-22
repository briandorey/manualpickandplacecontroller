package com.briandorey.projects.abelectronics.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class BoardComponents {
	public static List<Components> ITEMS = new ArrayList<Components>();
	public static Map<String, Components> ITEM_MAP = new HashMap<String, Components>();
	static {
		addItem(new Components("1", 
				"4,4,5,5,6,6,6,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,3,3", 
				"/,/,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,/,/")); // adc pi
		addItem(new Components("2", 
				"3,3,4,4,5,5,6,6,6,6", 
				"0,0,[,[,[,[,0,0,[,[")); // delta sigma pi
		addItem(new Components("3", 
				"3,3,5,6,6,6,6,6", 
				"0,0,0,0,0,],],]")); // rtc
		addItem(new Components("4", 
				"1,1,5,5,8,a",
				"0,0,0,],0,0")); // rtc alarm
		addItem(new Components("5", 
				"3,3,5,5,6,6,6,6,6,6,6,6,6,6", 
				"],],0,0,0,0,0,0,0,0,0,0,0,0")); // IO Pi
		addItem(new Components("6", 
				"2,b,9,5,5,5,5,5,5", 
				"0,.,0,0,0,0,0,.,.")); // com pi
		addItem(new Components("7", 
				"b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2", 
				"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0")); // Level Converter
		addItem(new Components("8", 
				"2,5,9,b",
				"[,0,0,0")); // 1 wire
		addItem(new Components("9", 
				"5,5,5,5,5",
				"0,0,0,0,0")); // Serial Pi
		addItem(new Components("10", 
				"0", 
				"0")); // Manual Control 
	}
	private static void addItem(Components item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}
	public static class Components {
		public String id;
		public String content;
		public String rotation;


		public Components(String id, String content, String rotation) {
			this.id = id;
			this.content = content;

			this.rotation = rotation;
		}
		@Override
		public String toString() {
			return content;
		}
		
	}
}
