package snownee.jade.util;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.config.Theme;

public class ThemeSerializer implements JsonDeserializer<Theme>, JsonSerializer<Theme> {

	@Override
	public JsonElement serialize(Theme src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject o = new JsonObject();
		o.addProperty("id", src.id.toString());
		o.addProperty("backgroundColor", src.backgroundColor);
		o.addProperty("gradientStart", src.gradientStart);
		o.addProperty("gradientEnd", src.gradientEnd);
		o.addProperty("titleColor", src.stressedTextColor);
		o.addProperty("textColor", src.normalTextColor);
		o.addProperty("textShadow", src.textShadow);
		return o;
	}

	@Override
	public Theme deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject o = json.getAsJsonObject();
		ResourceLocation id = ResourceLocation.tryParse(o.get("id").getAsString());
		String backgroundColor = readColor(o, "backgroundColor");
		String gradientStart = readColor(o, "gradientStart");
		String gradientEnd = readColor(o, "gradientEnd");
		String titleColor = readColor(o, "titleColor");
		String textColor = readColor(o, "textColor");
		boolean textShadow = o.get("textShadow").getAsBoolean();
		return new Theme(id, backgroundColor, gradientStart, gradientEnd, titleColor, textColor, textShadow);
	}

	private String readColor(JsonObject o, String s) {
		JsonPrimitive e = o.get(s).getAsJsonPrimitive();
		if (e.isString()) {
			try {
				Color.valueOf(e.getAsString());
			} catch (Throwable e2) {
				return "#000";
			}
			return e.getAsString();
		} else {
			return Color.rgb(e.getAsInt()).getHex();
		}
	}

}
