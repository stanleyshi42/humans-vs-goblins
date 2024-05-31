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

public class LootWindow extends JFrame {
    
    final int tileSize = 16;
    final int scale = 3;

    final int scaledTileSize = tileSize * scale;
    final int maxScreenColumns = 3;
    final int maxScreenRows = 4;

    Player player;
    GamePanel gPanel;
    ArrayList<InventorySlot> lootItems;
    ArrayList<InventorySlot> pouchItems;
    ArrayList<Item> itemObjs;

    LootWindow(GamePanel panel, Player p, boolean chest) {
        this.player = p;
        this.gPanel = panel;

        if(chest == false)
            itemObjs = GoblinDrops.rollMultiple(3);
        else
            itemObjs = ChestDrops.rollMultiple(3);

        // Grab random loot items and check if
        // the player rolled all null items.
        boolean allNull = true;
        for(Item i: itemObjs){
            if(i != null) allNull = false;
        }

        gPanel.removeKeyListener(gPanel.getKeyboard());
        gPanel.removeMouseListener(gPanel.getMouse());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gPanel.addListeners();
            }
        });
        
        this.setTitle("Loot Window");
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(500, 700));
        this.setSize(new Dimension(500, 700));
        //this.setDoubleBuffered(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setLocationRelativeTo(null);
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        FrameDragListener fdl = new FrameDragListener(this);
        this.addMouseListener(fdl);
        this.addMouseMotionListener(fdl);
        
        initializeDisplay();
        if(allNull) {
            gPanel.addListeners();
            this.dispose();
        }
    }

    public void initializeLootSlots() {
        lootItems = new ArrayList<>();

        int i;
        for(i=0; i < itemObjs.size(); i++) {
            if(itemObjs.get(i) == null) {
                itemObjs.remove(i);
                i--;
                continue;
            }
            InventorySlot lootItem = new InventorySlot(scaledTileSize, itemObjs.get(i));
            {
                LootWindow l = this;
                lootItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        if(player.getInventory().size() < maxScreenColumns*maxScreenRows) {
                            System.out.println(lootItem.itemInSlot.getName()+" "+lootItem.itemInSlot.getId());
                            lootItems.remove(lootItem);
                            itemObjs.remove(lootItem.itemInSlot);
                            player.addItemToInventory(lootItem.itemInSlot);
                            initializeDisplay();

                            boolean allNull = true;
                            for(Item i: itemObjs){
                                System.out.println(i.getName()+" "+i.getId());
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
            lootItems.add(new InventorySlot(scaledTileSize, null));
        }

        revalidate();
        repaint();
    }

    public void initializeInventorySlots() {
        pouchItems = new ArrayList<>();

        int i;
        for(i=0; i < player.getInventory().size(); i++) {
            InventorySlot existingItems = new InventorySlot(scaledTileSize, player.getInventory().get(i));
            pouchItems.add(existingItems);
        }
        for(int j=i; j < maxScreenColumns*maxScreenRows; j++) {
            pouchItems.add(new InventorySlot(scaledTileSize, null));
        }

        revalidate();
        repaint();
    }

    public void initializeDisplay() {

        this.getContentPane().removeAll();
        initializeLootSlots();
        initializeInventorySlots();

        JPanel frameContainer = new JPanel();
        frameContainer.setLayout(new BoxLayout(frameContainer, BoxLayout.Y_AXIS));
        frameContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // BACK BUTTON
        JPanel buttonCont = new JPanel();
        buttonCont.setSize(new Dimension(400, 40));                      // 400 40
        buttonCont.setPreferredSize(new Dimension(400, 40));
        //buttonCont.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("Return");
        backButton.setFont(new Font("Sans-serif", Font.BOLD, 16));
        backButton.setFocusable(false);
        {
            LootWindow p = this;
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
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
        lootContainer.setSize(new Dimension(300, 420));                 // 300 420
        lootContainer.setPreferredSize(new Dimension(300, 420));

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
        pouchContainer.setLayout(new GridLayout(4, 3, 10, 5));
        pouchContainer.setSize(new Dimension(300, 420));                 // 300 420
        pouchContainer.setPreferredSize(new Dimension(300, 420));

        for(InventorySlot t: pouchItems) {
            t.displayItem();
            pouchContainer.add(t);
        }
        frameContainer.add(pouchContainer);
        this.add(frameContainer, BorderLayout.CENTER);
        this.pack();
    }


}
