package ca.damocles.Menus.Book;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ca.damocles.utils.NMSUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.NBTTagInt;
import net.minecraft.server.v1_13_R2.NBTTagList;
import net.minecraft.server.v1_13_R2.NBTTagString;

public class Book {
	
    private String title;
    private String author;
    private List<String> pages = new ArrayList<String>();
  
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
  
    public ItemStack build() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        net.minecraft.server.v1_13_R2.ItemStack nmsBook = CraftItemStack.asNMSCopy(book);
        NBTTagCompound tag = new NBTTagCompound();
        tag.set("generation", new NBTTagInt(0));
        tag.setString("author", author);
        tag.setString("title", title);
        NBTTagList pages = new NBTTagList();
        for (String page : this.pages) pages.add(new NBTTagString(page));
        tag.set("pages", pages);
        nmsBook.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsBook);
    }
  
    public void openBook(Player p) {
        int slot = p.getInventory().getHeldItemSlot();
        org.bukkit.inventory.ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, build());

       ByteBuf buf = Unpooled.buffer(256);
       buf.setByte(0, (byte)0);
       buf.writerIndex(1);
       
       try {
           Constructor<?> serializerConstructor = NMSUtils.getNMSClass("PacketDataSerializer").getConstructor(ByteBuf.class);
           Object packetDataSerializer = serializerConstructor.newInstance(buf);

           Constructor<?> keyConstructor = NMSUtils.getNMSClass("MinecraftKey").getConstructor(String.class);
           Object bookKey = keyConstructor.newInstance("minecraft:book_open");
          
           Constructor<?> titleConstructor = NMSUtils.getNMSClass("PacketPlayOutCustomPayload").getConstructor(bookKey.getClass(), NMSUtils.getNMSClass("PacketDataSerializer"));
           Object payload = titleConstructor.newInstance(bookKey, packetDataSerializer);

           NMSUtils.sendPacket(p, payload);
       } catch (Exception e) {
           e.printStackTrace();
       }
       
        p.getInventory().setItem(slot, old);
    }
    
    public void addPage(List<String> lines){
    	String page = "";
    	for(String s : lines){
    		page = page + s + "\n";
    	}
    	this.pages.add(page);
    }
}
