package humansVsGoblins;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import drop_tables.ChestDrops;
import drop_tables.GoblinDrops;
import entities.Player;
import humansVsGoblins.InventoryPanel.FrameDragListener;
import items.Item;

/*
 * LootWindow represents a Loot Drop Screen for the HumanVsPlayers game.
 * Uses Java Swing for a GUI representation and communicates directly with the
 * Player class to display stats and the items that the Player has. Communicates
 * with the GoblinDrops and ChestDrops classes to gather the items dropped from
 * their specific loot rolls. The LootWindow only allows for players to gather
 * loot from drops, no equipping or using of items.
 */

public class LootWindow extends JFrame {
    
    // Tile Size and Scale modifier
    final int tileSize = 16;
    final int scale = 3;

    // Tile Size after scale
    final int scaledTileSize = tileSize * scale;
    
    // Max amount of slots for inventory space | 15 slots
    final int maxScreenColumns = 3;
    final int maxScreenRows = 5;

    Player player;                          // Player object that is interacting with the lootwindow.
    GamePanel gPanel;                       // Panel that callled the loot window.
    ArrayList<InventorySlot> lootItems;     // The list of slots for the loot rolled.
    ArrayList<InventorySlot> pouchItems;    // The list of slots that represent the player's inventory.
    ArrayList<Item> itemObjs;               // The list of items that have been rolled from GoblinDrops or ChestDrops.


    // Constructor that calls loot rolls for the items that
    // can be inserted into the player's inventory in this
    // window. Closes the Key and Mouse listeners for the
    // game panel while the lootwindow is open. Initializes
    // basic JFrame settings and calls initializeDisplay().
    LootWindow(GamePanel panel, Player p, boolean chest) {
        this.player = p;
        this.gPanel = panel;
        itemObjs= null;

        if(!chest)
            itemObjs = GoblinDrops.rollMultiple(3);
        else
            itemObjs = ChestDrops.rollMultiple(3);

        // Grab random loot items and check if
        // the player rolled all null items.
        boolean allNull = true;
        for(Item i: itemObjs){
            if(i != null) allNull = false;
        }

        // Remove the keyboard and mouse listeners
        // from the panel during lootwindow runtime.
        gPanel.removeKeyListener(gPanel.getKeyboard());
        gPanel.removeMouseListener(gPanel.getMouse());
        addWindowListener(new WindowAdapter() {
            // If window closed, return the listeners to the panel.
            @Override
            public void windowClosing(WindowEvent e) {
                gPanel.addListeners();
            }
        });
        
        // Initialize basic JFrame settings.
        this.setTitle("Loot Window");
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(500, 850));
        this.setSize(new Dimension(500, 850));
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setLocationRelativeTo(null);
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Insert framedraglistener allowing movement
        // for loot window.
        FrameDragListener fdl = new FrameDragListener(this);
        this.addMouseListener(fdl);
        this.addMouseMotionListener(fdl);
        
        initializeDisplay();
        
        // If the loot drops result in no items close
        // the loot window and return to the game.
        if(allNull) {
            gPanel.addListeners();
            this.dispose();
        }
    }

    // updateSprites() will call the InventorySlot.update()
    // methods to change the sprite that needs to be shown 
    // on that current tick of the game. This method allows
    // for animation throughout all items in the LootWindow.
    public void updateSprites(int spriteNum) {
        for(InventorySlot ls: lootItems) {
            ls.update(spriteNum);
            ls.displayItem();
        }
        for(InventorySlot is: pouchItems) {
            is.update(spriteNum);
            is.displayItem();
        }
    }

    // initializeLootSlots() will create all the Loot slots needed
    // to display the chest or goblin drops and additional empty slots
    // if they dropped nothing on the roll.
    public void initializeLootSlots() {
        lootItems = new ArrayList<>();

        int i;
        for(i=0; i < itemObjs.size(); i++) {
            if(itemObjs.get(i) == null) {
                itemObjs.remove(i);
                i--;
                continue;
            }
            InventorySlot lootItem = new InventorySlot(itemObjs.get(i));
            {
                LootWindow l = this;
                lootItem.addActionListener(new ActionListener() {
                    // Loot item slots have an ActionListener that
                    // will insert the looted item into the player's
                    // inventory as long as the player's inventory is not
                    // full.
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(player.getInventory().size() < maxScreenColumns*maxScreenRows) {
                            lootItems.remove(lootItem);
                            itemObjs.remove(lootItem.itemInSlot);
                            player.addItemToInventory(lootItem.itemInSlot);
                            initializeDisplay();

                            boolean allNull = true;
                            for(Item i: itemObjs){
                                if(i != null) allNull = false;
                            }
                            if(allNull) {
                                gPanel.addListeners();
                                l.dispose();
                            }
                        }
                    }
                });
            }
            lootItems.add(lootItem);
        }
        for(int j=i; j < maxScreenColumns; j++) {
            lootItems.add(new InventorySlot(null));
        }

        revalidate();
        repaint();
    }

    // initializeInventorySlots() will create all the inventory slots
    // needed to display the player's items and additional empty slots
    // to be filled when the player acquires more items.
    public void initializeInventorySlots() {
        pouchItems = new ArrayList<>();

        int i;
        for(i=0; i < player.getInventory().size(); i++) {
            InventorySlot existingItems = new InventorySlot(player.getInventory().get(i));
            pouchItems.add(existingItems);
        }
        for(int j=i; j < maxScreenColumns*maxScreenRows; j++) {
            pouchItems.add(new InventorySlot(null));
        }

        revalidate();
        repaint();
    }

    // initializeDisplay() is used to create all the JPanels, JLabels, etc
    // needed to display the inventory screen. This class is recalled on every
    // inventory change and sprite update allowing for a GUI refresh.
    public void initializeDisplay() {

        this.getContentPane().removeAll();
        initializeLootSlots();
        initializeInventorySlots();

        JPanel frameContainer = new JPanel();
        frameContainer.setLayout(new BoxLayout(frameContainer, BoxLayout.Y_AXIS));
        frameContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // BACK BUTTON
        JPanel buttonCont = new JPanel();
        buttonCont.setSize(new Dimension(400, 30));                      // 400 30
        buttonCont.setPreferredSize(new Dimension(400, 30));

        JButton backButton = new JButton("Return");
        backButton.setFont(new Font("Sans-serif", Font.BOLD, 16));
        backButton.setFocusable(false);
        {
            LootWindow p = this;
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // If window closed, return listeners to panel.
                    gPanel.addListeners();
                    p.dispose();
                }
            });
        }
        buttonCont.add(backButton);
        frameContainer.add(buttonCont);

        // LOOT AREA
        JPanel lootLabelCont = new JPanel();
        lootLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        lootLabelCont.setSize(new Dimension(400, 30));
        lootLabelCont.setPreferredSize(new Dimension(400, 30));        // 400 30

        JLabel lootLabel = new JLabel("Loot:");
        lootLabel.setForeground(Color.BLACK);
        lootLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        lootLabelCont.add(lootLabel);
        frameContainer.add(lootLabelCont);

        JPanel lootContainer = new JPanel();
        lootContainer.setLayout(new GridLayout(1, 3, 10, 5));
        lootContainer.setSize(new Dimension(300, 360));                 // 300 400
        lootContainer.setPreferredSize(new Dimension(300, 360));

        for(InventorySlot t: lootItems) {
            t.displayItem();
            lootContainer.add(t);
        }
        frameContainer.add(lootContainer);

        // POUCH AREA
        JPanel pouchLabelCont = new JPanel();
        pouchLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        pouchLabelCont.setSize(new Dimension(400, 30));
        pouchLabelCont.setPreferredSize(new Dimension(400, 30));        // 400 30

        JLabel pouchLabel = new JLabel("Pouch:");
        pouchLabel.setForeground(Color.BLACK);
        pouchLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        pouchLabelCont.add(pouchLabel);
        frameContainer.add(pouchLabelCont);

        JPanel pouchContainer = new JPanel();
        pouchContainer.setLayout(new GridLayout(5, 3, 10, 5));
        pouchContainer.setSize(new Dimension(300, 600));                 // 300 460
        pouchContainer.setPreferredSize(new Dimension(300, 600));

        for(InventorySlot t: pouchItems) {
            t.displayItem();
            pouchContainer.add(t);
        }
        frameContainer.add(pouchContainer);
        this.add(frameContainer, BorderLayout.CENTER);
        this.pack();
    }

}
