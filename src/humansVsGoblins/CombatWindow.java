package humansVsGoblins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Random;

import entities.Goblin;
import entities.Player;
import items.Item;
import items.Potion;

public class CombatWindow extends JFrame {
    private final Player player;
    private final Goblin enemy;
    private final JTextArea combatLog;
    private final JProgressBar playerHealthBar;
    private final JProgressBar enemyHealthBar;
    private boolean playerTurn;
    private GamePanel gPanel;

    private Random rand = new Random();

    public CombatWindow(GamePanel gamePanel, Player player, Goblin enemy) {
        this.player = player;
        this.enemy = enemy;
        this.playerTurn = true;
        this.gPanel = gamePanel;


        setTitle("Combat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gPanel.addListeners();
            }
        });


        // Combat log setup
        combatLog = new JTextArea(11, 50);
        combatLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(combatLog);

        // Character panel setup
        JPanel characterPanel = new JPanel(new GridLayout(1, 2));
        JLabel playerImageLabel = new JLabel(new ImageIcon(new ImageIcon("Resources/player.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        JLabel enemyImageLabel = new JLabel(new ImageIcon(new ImageIcon("Resources/goblin.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
     
        
        characterPanel.add(playerImageLabel);
        characterPanel.add(enemyImageLabel);

        // Health bar panel setup
        JPanel healthBarPanel = new JPanel(new GridLayout(1, 2));
        playerHealthBar = new JProgressBar(0, player.getMaxHp());
        playerHealthBar.setValue(player.getHp());
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setForeground(Color.BLUE);
        healthBarPanel.add(playerHealthBar);
        enemyHealthBar = new JProgressBar(0, enemy.getHp());
        enemyHealthBar.setValue(enemy.getHp());
        enemyHealthBar.setStringPainted(true);
        enemyHealthBar.setForeground(Color.GREEN);
        healthBarPanel.add(enemyHealthBar);

        // Button panel setup
        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAttack();
            }
        });


        JButton usePotionButton = new JButton("Use Potion");
        usePotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usePotion();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(attackButton);
        buttonPanel.add(usePotionButton);

        // Panel positions
        add(healthBarPanel, BorderLayout.NORTH);
        add(characterPanel, BorderLayout.CENTER);

        // Create a panel for buttons and combat log
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

        appendToCombatLog("Combat started!");
        appendToCombatLog("--------------------");
        appendToCombatLog("Player: " + player.getClass().getSimpleName() + " (Health: " + player.getHp() + ")");
        appendToCombatLog("Enemy: " + enemy.getClass().getSimpleName() + " (Health: " + enemy.getHp() + ")");
        appendToCombatLog("-------------------");
        appendToCombatLog("");
        appendToCombatLog("Player's turn.");
    }

    private void performAttack() {
        if (playerTurn) {
            int playerDamage = rand.nextInt(player.getAttack()+1);
            int enemyDefense = enemy.getDefense();
            int totalDmg = (playerDamage - enemyDefense > 0) ? playerDamage - enemyDefense : 0;
            enemy.takeDamage(playerDamage);
            appendToCombatLog("*** Player attacks for " + playerDamage + " damage.");
            appendToCombatLog("*** Enemy blocked " + enemyDefense + " damage.");
            appendToCombatLog("*** Enemy took " + totalDmg + " damage.");
            appendToCombatLog("---------------------");
            if (enemy.getHp() <= 0) {
                appendToCombatLog("Enemy defeated!");
                appendToCombatLog("Combat ended.");
                updateHealthBars(player.getHp(), enemy.getHp());
                gPanel.openLootWindow(false);
                dispose();
            } else {
                playerTurn = false;
                appendToCombatLog("Enemy's turn.");
                enemyAttack();
                updateHealthBars(player.getHp(), enemy.getHp());
            }
        }
    }

    private void enemyAttack() {
        int enemyDamage = rand.nextInt(enemy.getAttack()+1);
        player.takeDamage(enemyDamage);
        int playerDefense = player.getDefense();
        int totalDmg = (enemyDamage - playerDefense > 0) ? enemyDamage - playerDefense : 0;
        appendToCombatLog("*** Enemy attacks for " + enemyDamage + " damage.");
        appendToCombatLog("*** Player blocked " + playerDefense + " damage.");
        appendToCombatLog("*** Player took " + totalDmg + " damage.");
        appendToCombatLog("---------------------");
        updateHealthBars(player.getHp(), enemy.getHp());
        if (player.getHp() <= 0) {
            appendToCombatLog("Player defeated!");
            appendToCombatLog("Combat ended.");
            dispose();
        } else {
            playerTurn = true;
            appendToCombatLog("Player's turn.");
        }
    }



    private void usePotion() {
        // Get the list of potions from the player's inventory
        ArrayList<Potion> potions = new ArrayList<>();
        for (Item item : player.getInventory()) {
            if (item instanceof Potion) {
                potions.add((Potion) item);
            }
        }

        if (potions.isEmpty()) {
            appendToCombatLog("No potions available!");
            return;
        }

        // Create a list of potion names for the dialog
        String[] potionNames = new String[potions.size()];
        for (int i = 0; i < potions.size(); i++) {
            potionNames[i] = potions.get(i).getName() + " (" + potions.get(i).healing + " HP)";
        }

        // Show the dialog to select a potion
        String selectedPotion = (String) JOptionPane.showInputDialog(
                this,
                "Select a potion to use:",
                "Use Potion",
                JOptionPane.PLAIN_MESSAGE,
                null,
                potionNames,
                potionNames[0]);

        if (selectedPotion != null) {
            // Find the selected potion
            for (Potion potion : potions) {
                if (selectedPotion.startsWith(potion.getName())) {
                    // Use the selected potion
                    player.usePotion(potion);
                    appendToCombatLog("Player used " + potion.getName() + " to restore " + potion.healing + " HP.");
                    appendToCombatLog("---------------------");
                    appendToCombatLog("Enemy's turn.");
                    updateHealthBars(player.getHp(), enemy.getHp());
                    playerTurn = false;
                    enemyAttack();
                    break;
                }
            }
        }
    }

    private void appendToCombatLog(String message) {
        combatLog.append(message + "\n");
    }

    public void updateHealthBars(int playerHp, int enemyHp) {
        playerHealthBar.setValue(playerHp);
        enemyHealthBar.setValue(enemyHp);
    }

    

}
