package ca.damocles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PlayParticle {
	
	public void play(Particle particle, Location l, int amount){
		l.getWorld().spawnParticle(particle, l, amount);
	}
	
	public void playRedstone(Location l, int amount, int r, int g, int b){
		l.getWorld().spawnParticle(Particle.REDSTONE, l.getX(), l.getY(), l.getZ(), amount, r, g, b, 0);
	}
	
	public static final Vector rotateAroundAxisX(Vector vector, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double y = vector.getY() * cos - vector.getZ() * sin;
		double z = vector.getY() * sin + vector.getZ() * cos;
		return vector.setY(y).setZ(z);
	}

	public static final Vector rotateAroundAxisY(Vector vector, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double x = vector.getX() * cos + vector.getZ() * sin;
		double z = vector.getX() * -sin + vector.getZ() * cos;
		return vector.setX(x).setZ(z);
	}
	
	/*
	 * Draws a line of particles from one Location to Another at a specified rate and frquency.
	 * Particle is the particle effect being produced.
	 * Amount is the amount of particles being spawned at each location.
	 * p1 is the origin location.
	 * p2 is the destination location.
	 * Speed is the number in ticks each iteration spawns at.
	 * Frequency is the rate each particle spawns at, 1.0 appearing ontop of destinations, 0.1 appearing one tenth of the distance each iteration.
	 */
	public void drawLine(Particle particle, int amount, Location p1, Location p2, int speed, double frequency){
		new BukkitRunnable(){
			double t = 0;
			Vector direction = p2.toVector().subtract(p1.toVector());
			public void run(){
				t += frequency;
				double x = direction.getX()*t;
				double y = direction.getY()*t;
				double z = direction.getZ()*t;
				p1.add(x, y, z);
				play(particle, p1, amount);
				if(p1.toVector().distance(p2.toVector()) <= 0.5)
					this.cancel();
				p1.subtract(x, y, z);
			}
		}.runTaskTimer(Cardinal.getInstance(), 0, speed);
	}
	
	/*
	 * Draws a line of particles to an entity
	 */
	public void drawLine(Particle particle, int amount, Player player, Entity entity, int speed, double frequency) {
		new BukkitRunnable(){
			double t = 0;
			Vector direction;
			Location p2;
			Location p1 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY()+1, player.getLocation().getZ());
			public void run(){
				p2 = new Location(entity.getWorld(), entity.getLocation().getX(), entity.getLocation().getY()+1, entity.getLocation().getZ());
				direction = p2.toVector().subtract(p1.toVector());
				t += frequency;
				double x = direction.getX()*t;
				double y = direction.getY()*t;
				double z = direction.getZ()*t;
				p1.add(x, y, z);
				play(particle, p1, amount);
				if(p1.toVector().distance(p2.toVector()) <= 0.5)
					this.cancel();
				p1.subtract(x, y, z);
			}
		}.runTaskTimer(Cardinal.getInstance(), 0, speed);
	}
	
}
