package snownee.jade.impl.config.entry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import snownee.jade.gui.config.WailaOptionsList;
import snownee.jade.gui.config.value.OptionValue;

public abstract class ConfigEntry<T> {

	public final ResourceLocation id;
	private final T defaultValue;
	private boolean synced;
	private T value;

	public ConfigEntry(ResourceLocation id, T defaultValue) {
		this.id = id;
		this.value = this.defaultValue = defaultValue;
	}

	public ResourceLocation getId() {
		return id;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public boolean isSynced() {
		return synced;
	}

	public void setSynced(boolean synced) {
		this.synced = synced;
	}

	public T getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = (T) value;
	}

	abstract public boolean isValidValue(Object value);

	@OnlyIn(Dist.CLIENT)
	abstract public OptionValue<?> createUI(WailaOptionsList options, String optionName);
}
