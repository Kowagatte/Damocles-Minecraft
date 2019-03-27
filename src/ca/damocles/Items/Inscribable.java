package ca.damocles.Items;

import java.util.Map;

import ca.damocles.Runes.Rune;

public interface Inscribable {
	
	public Map<Rune, Integer> getAppliedRunes();
	
	public int getAvailableSlots();
	
	public void addRune(Rune rune, int level);
	
}
