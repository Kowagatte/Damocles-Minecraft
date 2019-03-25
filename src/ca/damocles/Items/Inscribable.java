package ca.damocles.Items;

import java.util.List;

import ca.damocles.Runes.Rune;

public interface Inscribable {
	
	public List<Rune> getAppliedRunes();
	
	public int getAvailableSlots();
	
}
