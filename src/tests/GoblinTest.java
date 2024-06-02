package tests;

import entities.Goblin;
import entities.GoblinAttributes;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class GoblinTest {

    @Test
    public void testGoblinConstructor() {
        Goblin goblin = new Goblin(5,5);
        assertEquals(new ImageIcon("Resources/goblin.png").getImage(), goblin.getGoblinImg().getImage());

        int type = 1;
        int [] attributes = GoblinAttributes.getAttributes(type);
        int hp = attributes[0];
        int atk = attributes[1];
        int def = attributes[2];
        Goblin redcap = new Goblin(4,4,hp,atk,def,type);
        assertEquals(new ImageIcon("Resources/redcap-1.png").getImage(), redcap.getGoblinImg().getImage());
    }

    @Test
    public void testDamage(){
        int type = 2;
        int [] attributes = GoblinAttributes.getAttributes(type);
        int hp = attributes[0];
        int atk = attributes[1];
        int def = attributes[2];
        Goblin orc = new Goblin(6,6,hp,atk,def,type);
        orc.takeDamage(16);
        assertEquals(20, orc.getHp());
        orc.takeDamage(100);
        assertEquals(0, orc.getHp());
    }

    @Test
    public void testMove(){
        Goblin goblin = new Goblin(2,2);
        int[][] tile = new int[5][5];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                tile[j][i] = 0;
            }
        }
        tile[2][3] = 1;
        assertTrue(goblin.isOccupied(tile,3,2));
        assertTrue(goblin.moveUp(tile));
        assertTrue(goblin.moveDown(tile));
        assertFalse(goblin.moveRight(tile));
    }

}
