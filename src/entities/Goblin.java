package entities;

public class Goblin extends Entity {
	int hp, attack, defense;

	public Goblin(int x, int y, int hp, int attack, int defense) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}

	public int getDefense() {
		return defense;
	}

	public int getAttack() {
		return attack;
	}

	public int getHp() {
		return hp;
	}

	public void takeDamage(int damage) {
        damage -= defense;
        if (damage < 0) {
            damage = 0; 
        }
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

}
