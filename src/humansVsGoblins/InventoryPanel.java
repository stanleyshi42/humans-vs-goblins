package humansVsGoblins;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.Player;
import items.Potion;

/*
 * InventoryPanel represents the Inventory Screen for the HumanVsPlayers game.
 * Uses Java Swing for a GUI representation and communicates directly with the
 * Player class to display stats and the items that the Player has. Allows for
 * equipping and using items.
 */
public class InventoryPanel extends JFrame {

    // Max amount of InventorySlots representing pouch
    private final int maxScreenColumns = 3;
    private final int maxScreenRows = 4;

    private Player player;                          // Player object needed to grab inventory
    private InventorySlot weapon;                   // InventorySlot representing equipped weapon
    private InventorySlot armor;                    // InventorySlot representing equipped armor
    private ArrayList<InventorySlot> pouchItems;    // List of InventorySlots representing player's inventory.
    private GamePanel gPanel;                       // GamePanel that calls the InventoryPanel.

    // updateSprites() will call the InventorySlot.update()
    // methods to change the sprite that needs to be shown 
    // on that current tick of the game. This method allows
    // for animation throughout all items in the InventoryPanel.
    public void updateSprites(int spriteNum) {
        weapon.update(spriteNum);
        weapon.displayMajorItem();
        armor.update(spriteNum);
        armor.displayMajorItem();
        for(InventorySlot is: pouchItems) {
            is.update(spriteNum);
            is.displayItem();
        }
    }

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
            InventorySlot existingItems = new InventorySlot(player.getInventory().get(i));
            if(existingItems.itemInSlot instanceof Potion) {
                existingItems.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
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
            pouchItems.add(new InventorySlot(null));
        }

        // Grab the armor and sword the player has equipped.
        weapon = new InventorySlot(player.getEquipment().get("weapon"));
        armor = new InventorySlot(player.getEquipment().get("armor"));
        revalidate();
        repaint();
    }

    // initializeDisplay() is used to create all the JPanels, JLabels, etc
    // needed to display the inventory screen. This class is recalled on every
    // inventory change and sprite update allowing for a GUI refresh.
    public void initializeDisplay() {

        // Refresh GUI
        this.getContentPane().removeAll();
        initializeInventorySlots();

        JPanel frameContainer = new JPanel();
        frameContainer.setLayout(new BoxLayout(frameContainer, BoxLayout.Y_AXIS));
        frameContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        // INVENTORY TITLE
        JPanel titleCont = new JPanel();
        titleCont.setSize(new Dimension(400, 40));                      // 400 40
        titleCont.setPreferredSize(new Dimension(400, 40));

        JLabel title = new JLabel("INVENTORY");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Sans-serif", Font.BOLD, 36));
        titleCont.add(title);
        frameContainer.add(titleCont);

        // BACK BUTTON
        JPanel buttonCont = new JPanel();
        buttonCont.setSize(new Dimension(400, 30));                      // 400 30
        buttonCont.setPreferredSize(new Dimension(400, 30));
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
                    gPanel.addListeners();
                    p.dispose();
                }
            });
        }
        buttonCont.add(backButton);
        frameContainer.add(buttonCont);

        // PLAYER STATS
        JPanel statsLabelCont = new JPanel();
        statsLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        statsLabelCont.setSize(new Dimension(400, 30));                 // 400 30
        statsLabelCont.setSize(new Dimension(400, 30));


        JLabel statsLabel = new JLabel("Stats:");
        statsLabel.setForeground(Color.BLACK);
        statsLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        statsLabelCont.add(statsLabel);
        frameContainer.add(statsLabelCont);

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
        frameContainer.add(statsContainer);

        // EQUIPMENT AREA
        JPanel equipLabelCont = new JPanel();
        equipLabelCont.setLayout(new FlowLayout(FlowLayout.LEFT));
        equipLabelCont.setSize(new Dimension(400, 30));                 // 400 30
        equipLabelCont.setPreferredSize(new Dimension(400, 30));

        JLabel equipLabel = new JLabel("Equipment:");
        equipLabel.setForeground(Color.BLACK);
        equipLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        equipLabelCont.add(equipLabel);
        frameContainer.add(equipLabelCont);

        JPanel equipContainer = new JPanel();
        equipContainer.setSize(new Dimension(300, 140));                // 300 140
        equipContainer.setLayout(new GridLayout(1, 3, 40, 5));
        equipContainer.setPreferredSize(new Dimension(400, 140));

        armor.displayMajorItem();
        weapon.displayMajorItem();
        equipContainer.add(armor);
        equipContainer.add(weapon);
        frameContainer.add(equipContainer);

        frameContainer.add(Box.createRigidArea(new Dimension(40,10)));            // 40 10

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
        pouchContainer.setSize(new Dimension(300, 440));                 // 300 440
        pouchContainer.setPreferredSize(new Dimension(300, 440));

        for(InventorySlot t: pouchItems) {
            t.displayItem();
            pouchContainer.add(t);
        }
        frameContainer.add(pouchContainer);
        this.add(frameContainer, BorderLayout.CENTER);
        this.pack();
    }

    // Constructor that removes the Key and Mouse listeners
    // for the panel that calls it. Initializes many settings
    // such as size, starting position, and a FrameDragListener
    // for the InventoryPanel. Finally, calls initializeDisplay
    // to begin creating the GUI components.
    InventoryPanel(GamePanel panel, Player p) {

        // Grab all the items the player has in its inventory.
        this.player = p;
        this.gPanel = panel;

        gPanel.removeKeyListener(gPanel.getKeyboard());
        gPanel.removeMouseListener(gPanel.getMouse());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gPanel.addListeners();
            }
        });
        
        this.setTitle("Inventory");
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(500, 940));
        this.setSize(new Dimension(500, 940));
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
    }

    // FrameDragListener enables the ability to move the
    // frame around with your mouse during runtime. Since
    // the frame is set as undecorated, this is needed for
    // movement.
    public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }

}
