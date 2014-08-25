package ch.digitalmeat.company;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;

public class Colors {
	public final static Color TRANSPARENT_WHITE = new Color(1f, 1f, 1f, 0f);
	public final static Color TRANSPARENT_BLACK = new Color(0f, 0f, 0f, 0f);
	
	public static final Color COMPANY_1 = new Color(0x88FF00FF);
	public static final Color COMPANY_2 = new Color(0x9800FFFF);
	public static final Color COMPANY_3 = new Color(0xFF0083FF);
	public static final Color COMPANY_4 = new Color(0x00FF61FF);
	public static final Color COMPANY_5 = new Color(0xFF9900FF);
	
	public static final Map<Integer, Color> companyColors;
	
	static {
		companyColors = new HashMap<Integer, Color>();
		companyColors.put(1, COMPANY_1);
		companyColors.put(2, COMPANY_2);
		companyColors.put(3, COMPANY_3);
		companyColors.put(4, COMPANY_4);
		companyColors.put(5, COMPANY_5);
	}
}
