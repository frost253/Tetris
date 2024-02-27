package blocks;

import java.awt.*;

public enum Colors {
	YELLOW(new Color(252, 241, 94)),
	LIGHT_BLUE(new Color(72, 229, 253)),
	RED(new Color(236, 45, 45)),
	GREEN(new Color(38, 243, 58)),
	ORANGE(new Color(247, 142, 21)),
	PINK(new Color(248, 66, 255)),
	PURPLE(new Color(146, 30, 213));

	final Color color;

	Colors(Color color) {
		this.color = color;
	}
}
