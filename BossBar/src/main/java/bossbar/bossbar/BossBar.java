package bossbar.bossbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class BossBar extends JavaPlugin implements Listener {
    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    FileConfiguration config = getConfig();

    private int taskID = -1;

    private org.bukkit.boss.BossBar bar;

    String webshop = this.config.getString("webshop.szoveg");

    String discord = this.config.getString("discord.szoveg");

    String szerverek = this.config.getString("szerverek.szoveg");

    String eventek = this.config.getString("eventek.szoveg");

    String vasarlasok = this.config.getString("vasarlasok.szoveg");

    public void onEnable() {
        saveDefaultConfig();
        createBar();
        getServer().getPluginManager().registerEvents(this, (Plugin) this);
        if (Bukkit.getOnlinePlayers().size() > 0)
            for (Player on: Bukkit.getOnlinePlayers())
                this.bar.addPlayer(on);
    }

    public void onDisable() {
        this.bar.removeAll();
    }

    public void createBar() {
        BarColor color = BarColor.BLUE;
        if (this.config.getString("webshop.szin").equals("kék")) {
            color = BarColor.BLUE;
        } else if (this.config.getString("webshop.szin").equals("fehér")) {
            color = BarColor.WHITE;
        } else if (this.config.getString("webshop.szin").equals("zöld")) {
            color = BarColor.GREEN;
        } else if (this.config.getString("webshop.szin").equals("pink")) {
            color = BarColor.PINK;
        } else if (this.config.getString("webshop.szin").equals("lila")) {
            color = BarColor.PURPLE;
        } else if (this.config.getString("webshop.szin").equals("piros")) {
            color = BarColor.RED;
        } else if (this.config.getString("webshop.szin").equals("sárga")) {
            color = BarColor.YELLOW;
        }
        this.bar = Bukkit.createBossBar(format(this.webshop), color, BarStyle.SOLID, new org.bukkit.boss.BarFlag[0]);
        this.bar.setVisible(true);
        cast();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!getBar().getPlayers().contains(event.getPlayer()))
            this.bar.addPlayer(event.getPlayer());
    }

    public void cast() {
        this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable() {
            int count = -1;

            double progress = 1.0D;

            double time = 1.0D / BossBar.this.config.getInt("idozito");

            public void run() {
                BossBar.this.bar.setProgress(this.progress);
                switch (this.count) {
                    case -1:
                        break;
                    case 0:
                        if (BossBar.this.config.getString("discord.szin").equals("kék")) {
                            BossBar.this.bar.setColor(BarColor.BLUE);
                        } else if (BossBar.this.config.getString("discord.szin").equals("fehér")) {
                            BossBar.this.bar.setColor(BarColor.WHITE);
                        } else if (BossBar.this.config.getString("discord.szin").equals("zöld")) {
                            BossBar.this.bar.setColor(BarColor.GREEN);
                        } else if (BossBar.this.config.getString("discord.szin").equals("pink")) {
                            BossBar.this.bar.setColor(BarColor.PINK);
                        } else if (BossBar.this.config.getString("discord.szin").equals("lila")) {
                            BossBar.this.bar.setColor(BarColor.PURPLE);
                        } else if (BossBar.this.config.getString("discord.szin").equals("piros")) {
                            BossBar.this.bar.setColor(BarColor.RED);
                        } else if (BossBar.this.config.getString("discord.szin").equals("sárga")) {
                            BossBar.this.bar.setColor(BarColor.YELLOW);
                        }
                        BossBar.this.bar.setTitle(BossBar.this.format(BossBar.this.discord));
                        break;
                    case 1:
                        if (BossBar.this.config.getString("szerverek.szin").equals("kék")) {
                            BossBar.this.bar.setColor(BarColor.BLUE);
                        } else if (BossBar.this.config.getString("szerverek.szin").equals("fehér")) {
                            BossBar.this.bar.setColor(BarColor.WHITE);
                        } else if (BossBar.this.config.getString("szerverek.szin").equals("zöld")) {
                            BossBar.this.bar.setColor(BarColor.GREEN);
                        } else if (BossBar.this.config.getString("szerverek.szin").equals("pink")) {
                            BossBar.this.bar.setColor(BarColor.PINK);
                        } else if (BossBar.this.config.getString("szerverek.szin").equals("lila")) {
                            BossBar.this.bar.setColor(BarColor.PURPLE);
                        } else if (BossBar.this.config.getString("szerverek.szin").equals("piros")) {
                            BossBar.this.bar.setColor(BarColor.RED);
                        } else if (BossBar.this.config.getString("szerverek.szin").equals("sárga")) {
                            BossBar.this.bar.setColor(BarColor.YELLOW);
                        }
                        BossBar.this.bar.setTitle(BossBar.this.format(BossBar.this.szerverek));
                        break;
                    case 2:
                        if (BossBar.this.config.getString("eventek.szin").equals("kék")) {
                            BossBar.this.bar.setColor(BarColor.BLUE);
                        } else if (BossBar.this.config.getString("eventek.szin").equals("fehér")) {
                            BossBar.this.bar.setColor(BarColor.WHITE);
                        } else if (BossBar.this.config.getString("eventek.szin").equals("zöld")) {
                            BossBar.this.bar.setColor(BarColor.GREEN);
                        } else if (BossBar.this.config.getString("eventek.szin").equals("pink")) {
                            BossBar.this.bar.setColor(BarColor.PINK);
                        } else if (BossBar.this.config.getString("eventek.szin").equals("lila")) {
                            BossBar.this.bar.setColor(BarColor.PURPLE);
                        } else if (BossBar.this.config.getString("eventek.szin").equals("piros")) {
                            BossBar.this.bar.setColor(BarColor.RED);
                        } else if (BossBar.this.config.getString("eventek.szin").equals("sárga")) {
                            BossBar.this.bar.setColor(BarColor.YELLOW);
                        }
                        BossBar.this.bar.setTitle(BossBar.this.format(BossBar.this.eventek));
                        break;
                    case 3:
                        if (BossBar.this.config.getString("vasarlasok.szin").equals("kék")) {
                            BossBar.this.bar.setColor(BarColor.BLUE);
                        } else if (BossBar.this.config.getString("vasarlasok.szin").equals("fehér")) {
                            BossBar.this.bar.setColor(BarColor.WHITE);
                        } else if (BossBar.this.config.getString("vasarlasok.szin").equals("zöld")) {
                            BossBar.this.bar.setColor(BarColor.GREEN);
                        } else if (BossBar.this.config.getString("vasarlasok.szin").equals("pink")) {
                            BossBar.this.bar.setColor(BarColor.PINK);
                        } else if (BossBar.this.config.getString("vasarlasok.szin").equals("lila")) {
                            BossBar.this.bar.setColor(BarColor.PURPLE);
                        } else if (BossBar.this.config.getString("vasarlasok.szin").equals("piros")) {
                            BossBar.this.bar.setColor(BarColor.RED);
                        } else if (BossBar.this.config.getString("vasarlasok.szin").equals("sárga")) {
                            BossBar.this.bar.setColor(BarColor.YELLOW);
                        }
                        BossBar.this.bar.setTitle(BossBar.this.format(BossBar.this.vasarlasok));
                        break;
                    default:
                        if (BossBar.this.config.getString("webshop.szin").equals("kék")) {
                            BossBar.this.bar.setColor(BarColor.BLUE);
                        } else if (BossBar.this.config.getString("webshop.szin").equals("fehér")) {
                            BossBar.this.bar.setColor(BarColor.WHITE);
                        } else if (BossBar.this.config.getString("webshop.szin").equals("zöld")) {
                            BossBar.this.bar.setColor(BarColor.GREEN);
                        } else if (BossBar.this.config.getString("webshop.szin").equals("pink")) {
                            BossBar.this.bar.setColor(BarColor.PINK);
                        } else if (BossBar.this.config.getString("webshop.szin").equals("lila")) {
                            BossBar.this.bar.setColor(BarColor.PURPLE);
                        } else if (BossBar.this.config.getString("webshop.szin").equals("piros")) {
                            BossBar.this.bar.setColor(BarColor.RED);
                        } else if (BossBar.this.config.getString("webshop.szin").equals("sárga")) {
                            BossBar.this.bar.setColor(BarColor.YELLOW);
                        }
                        BossBar.this.bar.setTitle(BossBar.this.format(BossBar.this.webshop));
                        this.count = -1;
                        break;
                }
                this.progress -= this.time;
                if (this.progress <= 0.0D) {
                    this.count++;
                    this.progress = 1.0D;
                }
            }
        }, 0L, 20L);
    }

    private String format(String msg) {
        if (Bukkit.getVersion().contains("1.18")) {
            Matcher match = this.pattern.matcher(msg);
            while (match.find()) {
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                match = this.pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public void addPlayer(Player player) {
        this.bar.addPlayer(player);
    }

    public void removePlayer(Player player) {
        this.bar.removePlayer(player);
    }

    public org.bukkit.boss.BossBar getBar() {
        return this.bar;
    }


    public int getTaskID() {
        return this.taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
}
