package humansVsGoblins;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Player;
import items.Potion;

/*
 * InventoryPanel represents the Inventory Screen for the HumanVsPlayers game.
 * Uses Java Swing for a GUI representation and communicates directly with the
 * Player class to display stats and the items that the Player has. Allows for
 * equipping and using items.
 */
public class InventoryPanel extends JPanel {
    final int tileSize = 16;
    final int scale = 3;

    final int scaledTileSize = tileSize * scale;
    final int maxScreenColumns = 3;
    final int maxScreenRows = 4;

    Player player;
    InventorySlot weapon;
    InventorySlot armor;
    ArrayList<InventorySlot> pouchItems;

    // initializeInventorySlots() will create all the inventory slots
    // needed to display the player's items and additional empty slots
    // to be filled when the player acquires more items. This method
    // also creates an actionlistener to every InventorySlot that
    // works with Player.equipItem() and Player.usePotion() to use potions
    // and equip items directly from the slots.
    public void initializeInventorySlots() {

        pouchItems = new ArrayList<>();
        weapon = null;
        armor = null;

        int i;
        for(i=0; i < player.getInventory().size(); i++) {
            InventorySlot existingItems = new InventorySlot(scaledTileSize, player.getInventory().get(i));
            if(existingItems.itemInSlot instanceof Potion) {
                existingItems.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        if(player.getHp() < player.getMaxHp()) {
                            player.usePotion((Potion) existingItems.itemInSlot);
                            initializeDisplay();
                        }              
                    }
                });
            }
            else {
                existingItems.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        player.equipItem(existingItems.itemInSlot);
                        initializeDisplay();                   
                    }
                });
            }
            pouchItems.add(existingItems);
        }
        for(int j=i; j < maxScreenColumns*maxScreenRows; j++) {
            pouchItems.add(new InventorySlot(scaledTileSize, null));
        }

        // Grab the armor and sword the player has equipped.
        weapon = new InventorySlot(scaledTileSize, player.getEquipment().get("weapon"));
        armor = new InventorySlot(scaledTileSize, player.getEquipment().get("armor"));
        revalidate();
        repaint();
        // System.out.println("\nInventory:");
        // for (InventorySlot inventorySlot : pouchItems) {
        //     if(inventorySlot.itemInSlot != null) {
        //         System.out.println(inventorySlot.itemInSlot.getName());
        //     }
        // }
    }

    // initializeDisplay() is used to create all the JPanels, JLabels, etc
    // needed to display the inventory screen. This class is recalled on every
    // inventory change allowing for a GUI refresh. 
    public void initializeDisplay() {

        this.removeAll();
        initializeInventorySlots();
        
        // INVENTORY TITLE
        JPanel titleCont = new JPanel();
        titleCont.setSize(new Dimension(400, 40));                      // 400 30
        titleCont.setPreferredSize(new Dimension(400, 40));

        JLabel title = new JLabel("INVENTORY");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Sans-serif", Font.BOLD, 36));
        titleCont.add(title);
        this.add(titleCont);

        // BACK BUTTON
        JPanel buttonCont = new JPanel();
        buttonCont.setSize(new Dimension(400, 40));                      // 400 40
        buttonCont.setPreferredSize(new Dimension(400, 40));
        //buttonCont.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("Return");
        backButton.setFont(new Font("Sans-serif", Font.BOLD, 16));
        backButton.setFocusable(false);
        {
            InventoryPanel p = this;
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    p.setVisible(false);
                }
            });
        }
        buttonCont.add(backButton);
        this.add(buttonCont);

        // PLAYER STATS
        JPanel statsLabelCont = new JPanel();
        statsLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        statsLabelCont.setSize(new Dimension(400, 30));                 // 400 30
        statsLabelCont.setSize(new Dimension(400, 30));


        JLabel statsLabel = new JLabel("Stats:");
        statsLabel.setForeground(Color.BLACK);
        statsLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        statsLabelCont.add(statsLabel);
        this.add(statsLabelCont);

        JPanel statsContainer = new JPanel();
        statsContainer.setSize(new Dimension(300, 40));                 // 300 40
        statsContainer.setLayout(new GridLayout(2, 2, 40, 5));
        statsContainer.setPreferredSize(new Dimension(300, 40));

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

        this.add(Box.createRigidArea(new Dimension(40,10)));            // 40 10

        // EQUIPMENT AREA
        JPanel equipLabelCont = new JPanel();
        equipLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        equipLabelCont.setSize(new Dimension(400, 30));
        equipLabelCont.setPreferredSize(new Dimension(400, 30));

        JLabel equipLabel = new JLabel("Equipment:");
        equipLabel.setForeground(Color.BLACK);
        equipLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        equipLabelCont.add(equipLabel);
        this.add(equipLabelCont);

        JPanel equipContainer = new JPanel();
        equipContainer.setSize(new Dimension(300, 140));                // 300 140
        equipContainer.setLayout(new GridLayout(1, 3, 40, 5));
        equipContainer.setPreferredSize(new Dimension(400, 140));

        armor.displayMajorItem();
        armor.setIconTextGap(15);
        armor.setFont(new Font("Sans-serif", Font.BOLD, 18));
        weapon.displayMajorItem();
        weapon.setIconTextGap(15);
        weapon.setFont(new Font("Sans-serif", Font.BOLD, 18));
        equipContainer.add(armor);
        equipContainer.add(weapon);
        this.add(equipContainer);

        this.add(Box.createRigidArea(new Dimension(40,10)));            // 40 10

        // POUCH AREA
        JPanel pouchLabelCont = new JPanel();
        pouchLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        pouchLabelCont.setSize(new Dimension(400, 30));
        pouchLabelCont.setPreferredSize(new Dimension(400, 30));        // 400 30

        JLabel pouchLabel = new JLabel("Pouch:");
        pouchLabel.setForeground(Color.BLACK);
        pouchLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        pouchLabelCont.add(pouchLabel);
        this.add(pouchLabelCont);

        JPanel pouchContainer = new JPanel();
        pouchContainer.setLayout(new GridLayout(4, 3, 10, 5));
        pouchContainer.setSize(new Dimension(300, 420));                 // 300 420
        pouchContainer.setPreferredSize(new Dimension(300, 420));

        for(InventorySlot t: pouchItems) {
            t.displayItem();
            pouchContainer.add(t);
        }
        this.add(pouchContainer);
    }

    InventoryPanel(Player p) {

        // Grab all the items the player has in its inventory.
        this.player = p;
        this.setPreferredSize(new Dimension(500, 940));
        this.setSize(new Dimension(500, 940));
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10,10,10,10));
        initializeDisplay();
    }

}
