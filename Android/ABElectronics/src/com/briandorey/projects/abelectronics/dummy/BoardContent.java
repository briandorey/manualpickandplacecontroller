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
public class BoardContent {
	public static List<BoardItem> ITEMS = new ArrayList<BoardItem>();
	public static Map<String, BoardItem> ITEM_MAP = new HashMap<String, BoardItem>();
	static {
		addItem(new BoardItem("1", "ADC Pi", "adcpi"));
		addItem(new BoardItem("2", "Delta Sigma Pi", "deltasigmapi"));
		addItem(new BoardItem("3", "RTC Pi", "rtcpi"));
		addItem(new BoardItem("4", "RTC Alarm Pi", "rtcalarmpi"));
		addItem(new BoardItem("5", "IO Pi", "iopi"));
		addItem(new BoardItem("6", "Com Pi", "compi"));
		addItem(new BoardItem("7", "Level Converter", "levelconverter"));
		addItem(new BoardItem("8", "1-Wire Pi", "1wire"));
		addItem(new BoardItem("9", "Serial Pi", "1wire"));
		addItem(new BoardItem("10", "Manual Control", "adcpi"));
	} 
	private static void addItem(BoardItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}
	public static class BoardItem {
		public String id;
		public String content;
		public String imageprefix;

		public BoardItem(String id, String content, String imageprefix) {
			this.id = id;
			this.content = content;
			this.imageprefix = imageprefix;
		}
		@Override
		public String toString() {
			return content;
		}
		
		public String toimageprefix() {
			return imageprefix;
		}
	}
}
