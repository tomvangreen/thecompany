package ch.digitalmeat.company;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

	public static void create() {

	}

	public Texture getTexture(String path) {
		return new Texture(Gdx.files.internal(path));
	}

	public Pixmap getPixmap(String path) {
		return new Pixmap(Gdx.files.internal(path));
	}

}
