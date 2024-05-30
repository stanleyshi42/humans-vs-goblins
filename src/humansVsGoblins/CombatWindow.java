package humansVsGoblins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entities.Goblin;
import entities.Player;

public class CombatWindow extends JFrame {
    private final Player player;
    private final Goblin enemy;
    private final JTextArea combatLog;
    private final JProgressBar playerHealthBar;
    private final JProgressBar enemyHealthBar;
    private boolean playerTurn;
    private GamePanel gPanel;

    public CombatWindow(GamePanel gamePanel, Player player, Goblin enemy) {
        this.player = player;
        this.enemy = enemy;
        this.playerTurn = true;
        this.gPanel = gamePanel;

        gPanel.pauseGameThread();

        setTitle("Combat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Combat log setup
        combatLog = new JTextArea(5, 50);
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
        playerHealthBar = new JProgressBar(0, player.getHp());
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

        JButton changeWeaponButton = new JButton("Change Weapon");
        changeWeaponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeWeapon();
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
        buttonPanel.add(changeWeaponButton);
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
        appendToCombatLog("Player: " + player.getClass().getSimpleName() + " (Health: " + player.getHp() + ")");
        appendToCombatLog("Enemy: " + enemy.getClass().getSimpleName() + " (Health: " + enemy.getHp() + ")");
        appendToCombatLog("");
        appendToCombatLog("Player's turn.");
    }

    private void performAttack() {
        if (playerTurn) {
            int playerDamage = player.getAttack();
            int enemyDefense = enemy.getDefense();
            enemy.takeDamage(playerDamage);
            appendToCombatLog("Player attacks for " + playerDamage + " damage.");
            appendToCombatLog("Enemy blocked " + enemyDefense + " damage.");
            if (enemy.getHp() <= 0) {
                appendToCombatLog("Enemy defeated!");
                appendToCombatLog("Combat ended.");
                gPanel.unPauseGameThread();
                dispose();
            } else {
                playerTurn = false;
                appendToCombatLog("Enemy's turn.");
                enemyAttack();
            }
        }
        updateHealthBars(player.getHp(), enemy.getHp());
    }

    private void enemyAttack() {
        int enemyDamage = enemy.getAttack();
        player.takeDamage(enemyDamage);
        int playerDefense = player.getDefense();
        appendToCombatLog("Enemy attacks for " + enemyDamage + " damage.");
        appendToCombatLog("Player blocked " + playerDefense + " damage.");
        updateHealthBars(player.getHp(), enemy.getHp());
        if (player.getHp() <= 0) {
            appendToCombatLog("Player defeated!");
            appendToCombatLog("Combat ended.");
            gPanel.unPauseGameThread();
            dispose();
        } else {
            playerTurn = true;
            appendToCombatLog("Player's turn.");
        }
    }

    private void changeWeapon() {
        
    }

    private void usePotion() {
       
    }

    private void appendToCombatLog(String message) {
        combatLog.append(message + "\n");
    }

    public void updateHealthBars(int playerHp, int enemyHp) {
        playerHealthBar.setValue(playerHp);
        enemyHealthBar.setValue(enemyHp);
    }

}
