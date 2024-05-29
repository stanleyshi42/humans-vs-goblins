package humansVsGoblins;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Player;

public class InventoryPanel extends JPanel {
    final int tileSize = 16;
    final int scale = 3;

    final int scaledTileSize = tileSize * scale;
    final int maxScreenColumns = 3;
    final int maxScreenRows = 3;

    Player player;
    InventorySlot weapon;
    InventorySlot armor;
    ArrayList<InventorySlot> pouchItems = new ArrayList<>();

    InventoryPanel(Player p) {

        this.player = p;

        // Grab all the items the player has in its inventory.
        int i;
        for(i=0; i < player.getInventory().size(); i++) {
            pouchItems.add(new InventorySlot(scaledTileSize, player.getInventory().get(i)));
        }
        for(int j=i; j < maxScreenColumns*maxScreenRows; j++) {
            pouchItems.add(new InventorySlot(scaledTileSize, null));
        }

        // Grab the armor and sword the player has equipped.
        weapon = new InventorySlot(scaledTileSize, player.getEquipment().get("weapon"));
        armor = new InventorySlot(scaledTileSize, player.getEquipment().get("armor"));

        this.setPreferredSize(new Dimension(500, 800));
        this.setSize(new Dimension(500, 800));
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10,10,10,10));

        // INVENTORY TITLE
        JPanel titleCont = new JPanel();
        titleCont.setSize(new Dimension(400, 20));                      // 400 20
        JLabel title = new JLabel("INVENTORY");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Sans-serif", Font.BOLD, 36));
        titleCont.add(title);
        this.add(titleCont);

        // PLAYER STATS
        JPanel statsLabelCont = new JPanel();
        statsLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        statsLabelCont.setSize(new Dimension(400, 60));
        JLabel statsLabel = new JLabel("Stats:");
        statsLabel.setForeground(Color.BLACK);
        statsLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        statsLabelCont.add(statsLabel);
        this.add(statsLabelCont);

        JPanel statsContainer = new JPanel();
        statsContainer.setSize(new Dimension(300, 100));                // 300 100
        statsContainer.setLayout(new GridLayout(2, 2, 40, 5));
        statsContainer.setPreferredSize(new Dimension(300, 100));

        JLabel hpLabel = new JLabel("HP: "+player.getHp()+"/"+player.getMaxHp());
        hpLabel.setFont(new Font("Sans-serif", Font.BOLD, 20));
        hpLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel attackLabel = new JLabel("Attack: "+player.getAttack());
        attackLabel.setFont(new Font("Sans-serif", Font.BOLD, 20));
        attackLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel defenseLabel = new JLabel("Defense: "+player.getDefense());
        defenseLabel.setFont(new Font("Sans-serif", Font.BOLD, 20));
        defenseLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel speedLabel = new JLabel("Speed: "+player.getSpeed());
        speedLabel.setFont(new Font("Sans-serif", Font.BOLD, 20));
        speedLabel.setHorizontalAlignment(JLabel.CENTER);

        statsContainer.add(hpLabel);
        statsContainer.add(attackLabel);
        statsContainer.add(defenseLabel);
        statsContainer.add(speedLabel);
        this.add(statsContainer);

        this.add(Box.createRigidArea(new Dimension(40,20)));            // 40 40

        // EQUIPMENT AREA
        JPanel equipLabelCont = new JPanel();
        equipLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        equipLabelCont.setSize(new Dimension(400, 60));
        JLabel equipLabel = new JLabel("Equipment:");
        equipLabel.setForeground(Color.BLACK);
        equipLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        equipLabelCont.add(equipLabel);
        this.add(equipLabelCont);

        JPanel equipContainer = new JPanel();
        equipContainer.setSize(new Dimension(300, 200));                // 300 300
        equipContainer.setLayout(new GridLayout(1, 3, 40, 5));
        equipContainer.setPreferredSize(new Dimension(400, 200));

        armor.displayItem();
        armor.setIconTextGap(10);
        armor.setFont(new Font("Sans-serif", Font.BOLD, 18));
        weapon.displayItem();
        weapon.setIconTextGap(20);
        weapon.setFont(new Font("Sans-serif", Font.BOLD, 18));
        equipContainer.add(armor);
        equipContainer.add(weapon);
        this.add(equipContainer);

        this.add(Box.createRigidArea(new Dimension(40,20)));            // 40 20

        // POUCH AREA
        JPanel pouchLabelCont = new JPanel();
        pouchLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        pouchLabelCont.setSize(new Dimension(400, 60));
        JLabel pouchLabel = new JLabel("Pouch:");
        pouchLabel.setForeground(Color.BLACK);
        pouchLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        pouchLabelCont.add(pouchLabel);
        this.add(pouchLabelCont);

        JPanel pouchContainer = new JPanel();
        pouchContainer.setLayout(new GridLayout(3, 3, 10, 5));
        pouchContainer.setSize(new Dimension(300, 300));                 // 300 300
        pouchContainer.setPreferredSize(new Dimension(300, 300));

        for(InventorySlot t: pouchItems) {
            t.displayItem();
            pouchContainer.add(t);
        }
        this.add(pouchContainer);

    }

}
